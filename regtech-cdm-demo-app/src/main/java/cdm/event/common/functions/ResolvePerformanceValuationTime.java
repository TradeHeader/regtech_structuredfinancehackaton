package cdm.event.common.functions;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.TimeZone;
import cdm.base.datetime.TimeZone.TimeZoneBuilder;
import cdm.base.datetime.functions.TimeZoneFromBusinessCenterTime;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
import cdm.observable.common.functions.ResolveTimeZoneFromTimeType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolvePerformanceValuationTime.ResolvePerformanceValuationTimeDefault.class)
public abstract class ResolvePerformanceValuationTime implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ResolveTimeZoneFromTimeType resolveTimeZoneFromTimeType;
	@Inject protected TimeZoneFromBusinessCenterTime timeZoneFromBusinessCenterTime;

	/**
	* @param valuationTime Represents the Equity Valuation terms from the Equity product definition.
	* @param valuationTimeType The time of day at which the calculation agent values the underlying, for example the official closing time of the exchange.
	* @param productIdentifier Specifies the product identifier, along with the source, which should be used to determine the correct valuation time i.e. close times are different across exchanges.
	* @param determinationMethod Specifies the method according to which an amount or a date is determined.
	* @return time 
	*/
	public TimeZone evaluate(BusinessCenterTime valuationTime, TimeTypeEnum valuationTimeType, ProductIdentifier productIdentifier, DeterminationMethodEnum determinationMethod) {
		TimeZone.TimeZoneBuilder timeBuilder = doEvaluate(valuationTime, valuationTimeType, productIdentifier, determinationMethod);
		
		final TimeZone time;
		if (timeBuilder == null) {
			time = null;
		} else {
			time = timeBuilder.build();
			objectValidator.validate(TimeZone.class, time);
		}
		
		return time;
	}

	protected abstract TimeZone.TimeZoneBuilder doEvaluate(BusinessCenterTime valuationTime, TimeTypeEnum valuationTimeType, ProductIdentifier productIdentifier, DeterminationMethodEnum determinationMethod);

	public static class ResolvePerformanceValuationTimeDefault extends ResolvePerformanceValuationTime {
		@Override
		protected TimeZone.TimeZoneBuilder doEvaluate(BusinessCenterTime valuationTime, TimeTypeEnum valuationTimeType, ProductIdentifier productIdentifier, DeterminationMethodEnum determinationMethod) {
			TimeZone.TimeZoneBuilder time = TimeZone.builder();
			return assignOutput(time, valuationTime, valuationTimeType, productIdentifier, determinationMethod);
		}
		
		protected TimeZone.TimeZoneBuilder assignOutput(TimeZone.TimeZoneBuilder time, BusinessCenterTime valuationTime, TimeTypeEnum valuationTimeType, ProductIdentifier productIdentifier, DeterminationMethodEnum determinationMethod) {
			if (exists(MapperS.of(valuationTime)).getOrDefault(false)) {
				time = toBuilder(timeZoneFromBusinessCenterTime.evaluate(valuationTime));
			} else {
				time = null;
			}
			
			if (exists(MapperS.of(valuationTimeType)).getOrDefault(false)) {
				time = toBuilder(resolveTimeZoneFromTimeType.evaluate(productIdentifier, valuationTimeType, determinationMethod));
			} else {
				time = null;
			}
			
			return Optional.ofNullable(time)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
