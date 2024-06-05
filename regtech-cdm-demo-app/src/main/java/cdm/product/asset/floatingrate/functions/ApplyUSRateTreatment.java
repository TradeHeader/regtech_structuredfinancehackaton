package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.RateTreatmentEnum;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;


@ImplementedBy(ApplyUSRateTreatment.ApplyUSRateTreatmentDefault.class)
public abstract class ApplyUSRateTreatment implements RosettaFunction {

	/**
	* @param baseRate Rate before treatment.
	* @param rateTreatment type of treatment.
	* @param calculationPeriod The calculation period over which the rate is computed.
	* @return treatedRate rate after treatment.
	*/
	public BigDecimal evaluate(BigDecimal baseRate, RateTreatmentEnum rateTreatment, CalculationPeriodBase calculationPeriod) {
		BigDecimal treatedRate = doEvaluate(baseRate, rateTreatment, calculationPeriod);
		
		return treatedRate;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal baseRate, RateTreatmentEnum rateTreatment, CalculationPeriodBase calculationPeriod);

	public static class ApplyUSRateTreatmentDefault extends ApplyUSRateTreatment {
		@Override
		protected BigDecimal doEvaluate(BigDecimal baseRate, RateTreatmentEnum rateTreatment, CalculationPeriodBase calculationPeriod) {
			BigDecimal treatedRate = null;
			return assignOutput(treatedRate, baseRate, rateTreatment, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal treatedRate, BigDecimal baseRate, RateTreatmentEnum rateTreatment, CalculationPeriodBase calculationPeriod) {
			treatedRate = baseRate;
			
			return treatedRate;
		}
	}
}
