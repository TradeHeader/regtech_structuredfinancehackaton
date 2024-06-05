package cdm.event.common.functions;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodEnum;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.PriceQuantity.PriceQuantityBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FindMatchingIndexTransitionInstruction.FindMatchingIndexTransitionInstructionDefault.class)
public abstract class FindMatchingIndexTransitionInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instructions 
	* @param priceQuantity 
	* @return matchingInstruction 
	*/
	public PriceQuantity evaluate(List<? extends PriceQuantity> instructions, PriceQuantity priceQuantity) {
		PriceQuantity.PriceQuantityBuilder matchingInstructionBuilder = doEvaluate(instructions, priceQuantity);
		
		final PriceQuantity matchingInstruction;
		if (matchingInstructionBuilder == null) {
			matchingInstruction = null;
		} else {
			matchingInstruction = matchingInstructionBuilder.build();
			objectValidator.validate(PriceQuantity.class, matchingInstruction);
		}
		
		return matchingInstruction;
	}

	protected abstract PriceQuantity.PriceQuantityBuilder doEvaluate(List<? extends PriceQuantity> instructions, PriceQuantity priceQuantity);

	public static class FindMatchingIndexTransitionInstructionDefault extends FindMatchingIndexTransitionInstruction {
		@Override
		protected PriceQuantity.PriceQuantityBuilder doEvaluate(List<? extends PriceQuantity> instructions, PriceQuantity priceQuantity) {
			if (instructions == null) {
				instructions = Collections.emptyList();
			}
			PriceQuantity.PriceQuantityBuilder matchingInstruction = PriceQuantity.builder();
			return assignOutput(matchingInstruction, instructions, priceQuantity);
		}
		
		protected PriceQuantity.PriceQuantityBuilder assignOutput(PriceQuantity.PriceQuantityBuilder matchingInstruction, List<? extends PriceQuantity> instructions, PriceQuantity priceQuantity) {
			final MapperC<PriceQuantity> thenResult = MapperC.<PriceQuantity>of(instructions)
				.filterItemNullSafe(item -> areEqual(item.<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<Period>map("getIndexTenor", floatingRateOption -> floatingRateOption.getIndexTenor()).<PeriodEnum>map("getPeriod", period -> period.getPeriod()), MapperS.of(priceQuantity).<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<Period>map("getIndexTenor", floatingRateOption -> floatingRateOption.getIndexTenor()).<PeriodEnum>map("getPeriod", period -> period.getPeriod()), CardinalityOperator.All).and(areEqual(item.<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<Period>map("getIndexTenor", floatingRateOption -> floatingRateOption.getIndexTenor()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()), MapperS.of(priceQuantity).<Observable>map("getObservable", _priceQuantity -> _priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<Period>map("getIndexTenor", floatingRateOption -> floatingRateOption.getIndexTenor()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()), CardinalityOperator.All)).and(areEqual(item.<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", _priceQuantity -> _priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(priceQuantity).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", _priceQuantity -> _priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All).or(areEqual(item.<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(priceQuantity).<FieldWithMetaPriceSchedule>mapC("getPrice", _priceQuantity -> _priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All))).get());
			matchingInstruction = toBuilder(thenResult
				.first().get());
			
			return Optional.ofNullable(matchingInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
