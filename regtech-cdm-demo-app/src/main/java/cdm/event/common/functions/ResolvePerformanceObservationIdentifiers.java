package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.functions.ResolveAdjustableDate;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.ValuationDates;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
import cdm.observable.event.ObservationIdentifier;
import cdm.observable.event.ObservationIdentifier.ObservationIdentifierBuilder;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolvePerformanceObservationIdentifiers.ResolvePerformanceObservationIdentifiersDefault.class)
public abstract class ResolvePerformanceObservationIdentifiers implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected AdjustedValuationDates adjustedValuationDates;
	@Inject protected ResolveAdjustableDate resolveAdjustableDate;
	@Inject protected ResolvePerformanceValuationTime resolvePerformanceValuationTime;

	/**
	* @param payout 
	* @param adjustedDate 
	* @return identifiers 
	*/
	public ObservationIdentifier evaluate(PerformancePayout payout, Date adjustedDate) {
		ObservationIdentifier.ObservationIdentifierBuilder identifiersBuilder = doEvaluate(payout, adjustedDate);
		
		final ObservationIdentifier identifiers;
		if (identifiersBuilder == null) {
			identifiers = null;
		} else {
			identifiers = identifiersBuilder.build();
			objectValidator.validate(ObservationIdentifier.class, identifiers);
		}
		
		return identifiers;
	}

	protected abstract ObservationIdentifier.ObservationIdentifierBuilder doEvaluate(PerformancePayout payout, Date adjustedDate);

	protected abstract MapperS<Date> adjustedFinalValuationDate(PerformancePayout payout, Date adjustedDate);

	protected abstract MapperS<? extends PerformanceValuationDates> valuationDates(PerformancePayout payout, Date adjustedDate);

	public static class ResolvePerformanceObservationIdentifiersDefault extends ResolvePerformanceObservationIdentifiers {
		@Override
		protected ObservationIdentifier.ObservationIdentifierBuilder doEvaluate(PerformancePayout payout, Date adjustedDate) {
			ObservationIdentifier.ObservationIdentifierBuilder identifiers = ObservationIdentifier.builder();
			return assignOutput(identifiers, payout, adjustedDate);
		}
		
		protected ObservationIdentifier.ObservationIdentifierBuilder assignOutput(ObservationIdentifier.ObservationIdentifierBuilder identifiers, PerformancePayout payout, Date adjustedDate) {
			identifiers
				.getOrCreateObservable()
				.addProductIdentifierValue(MapperS.of(payout).<Product>map("getUnderlier", performancePayout -> performancePayout.getUnderlier()).<Security>map("getSecurity", product -> product.getSecurity()).<ReferenceWithMetaProductIdentifier>mapC("getProductIdentifier", productBase -> productBase.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()).getMulti());
			
			final MapperC<Date> thenResult = MapperC.<Date>of(adjustedValuationDates.evaluate(MapperS.of(payout).<ValuationDates>map("getValuationDates", performancePayout -> performancePayout.getValuationDates()).get()))
				.filterItemNullSafe(item -> lessThanEquals(item, MapperS.of(adjustedDate), CardinalityOperator.All).get());
			identifiers
				.setObservationDate(thenResult
					.last().get());
			
			identifiers
				.setObservationTime(resolvePerformanceValuationTime.evaluate(valuationDates(payout, adjustedDate).<BusinessCenterTime>map("getValuationTime", performanceValuationDates -> performanceValuationDates.getValuationTime()).get(), valuationDates(payout, adjustedDate).<TimeTypeEnum>map("getValuationTimeType", performanceValuationDates -> performanceValuationDates.getValuationTimeType()).get(), MapperS.of(identifiers).<Observable>map("getObservable", observationIdentifier -> observationIdentifier.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()).get(), valuationDates(payout, adjustedDate).<DeterminationMethodEnum>map("getDeterminationMethod", performanceValuationDates -> performanceValuationDates.getDeterminationMethod()).get()));
			
			identifiers
				.getOrCreateDeterminationMethodology()
				.setDeterminationMethod(valuationDates(payout, adjustedDate).<DeterminationMethodEnum>map("getDeterminationMethod", performanceValuationDates -> performanceValuationDates.getDeterminationMethod()).get());
			
			return Optional.ofNullable(identifiers)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<Date> adjustedFinalValuationDate(PerformancePayout payout, Date adjustedDate) {
			return MapperS.of(resolveAdjustableDate.evaluate(MapperS.of(payout).<ValuationDates>map("getValuationDates", performancePayout -> performancePayout.getValuationDates()).<PerformanceValuationDates>map("getValuationDatesFinal", _valuationDates -> _valuationDates.getValuationDatesFinal()).<AdjustableOrRelativeDate>map("getValuationDate", performanceValuationDates -> performanceValuationDates.getValuationDate()).get()));
		}
		
		@Override
		protected MapperS<? extends PerformanceValuationDates> valuationDates(PerformancePayout payout, Date adjustedDate) {
			if (lessThan(MapperS.of(adjustedDate), adjustedFinalValuationDate(payout, adjustedDate), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(payout).<ValuationDates>map("getValuationDates", performancePayout -> performancePayout.getValuationDates()).<PerformanceValuationDates>map("getValuationDatesInterim", _valuationDates -> _valuationDates.getValuationDatesInterim());
			}
			return MapperS.of(payout).<ValuationDates>map("getValuationDates", performancePayout -> performancePayout.getValuationDates()).<PerformanceValuationDates>map("getValuationDatesFinal", _valuationDates -> _valuationDates.getValuationDatesFinal());
		}
	}
}
