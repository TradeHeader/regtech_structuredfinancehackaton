package cdm.product.asset.floatingrate.functions;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.calculatedrate.CalculationMethodEnum;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder;
import cdm.product.common.schedule.ResetDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(GetCalculatedFROCalculationParameters.GetCalculatedFROCalculationParametersDefault.class)
public abstract class GetCalculatedFROCalculationParameters implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param resetDates The reset dates for the interest rate payout for which the calculated rate is being computed.
	* @param calcMethod Whether the rate is a compound (OIS) or daily average rate.
	* @return calcParams A calculation parameters block.
	*/
	public FloatingRateCalculationParameters evaluate(ResetDates resetDates, CalculationMethodEnum calcMethod) {
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder calcParamsBuilder = doEvaluate(resetDates, calcMethod);
		
		final FloatingRateCalculationParameters calcParams;
		if (calcParamsBuilder == null) {
			calcParams = null;
		} else {
			calcParams = calcParamsBuilder.build();
			objectValidator.validate(FloatingRateCalculationParameters.class, calcParams);
		}
		
		return calcParams;
	}

	protected abstract FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder doEvaluate(ResetDates resetDates, CalculationMethodEnum calcMethod);

	public static class GetCalculatedFROCalculationParametersDefault extends GetCalculatedFROCalculationParameters {
		@Override
		protected FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder doEvaluate(ResetDates resetDates, CalculationMethodEnum calcMethod) {
			FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder calcParams = FloatingRateCalculationParameters.builder();
			return assignOutput(calcParams, resetDates, calcMethod);
		}
		
		protected FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder assignOutput(FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder calcParams, ResetDates resetDates, CalculationMethodEnum calcMethod) {
			calcParams
				.setCalculationMethod(calcMethod);
			
			calcParams
				.setApplicableBusinessDays(MapperS.of(resetDates).<RelativeDateOffset>map("getFixingDates", _resetDates -> _resetDates.getFixingDates()).<BusinessCenters>map("getBusinessCenters", relativeDateOffset -> relativeDateOffset.getBusinessCenters()).get());
			
			return Optional.ofNullable(calcParams)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
