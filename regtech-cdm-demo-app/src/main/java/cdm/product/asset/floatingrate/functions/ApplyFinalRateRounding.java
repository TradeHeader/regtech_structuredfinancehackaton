package cdm.product.asset.floatingrate.functions;

import cdm.base.math.Rounding;
import cdm.base.math.RoundingDirectionEnum;
import cdm.base.math.functions.RoundToPrecision;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ApplyFinalRateRounding.ApplyFinalRateRoundingDefault.class)
public abstract class ApplyFinalRateRounding implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected RoundToPrecision roundToPrecision;

	/**
	* @param baseRate Rate before rounding.
	* @param finalRateRounding type of rounding (precision and direction).
	* @return roundedRate rate after rounding.
	*/
	public BigDecimal evaluate(BigDecimal baseRate, Rounding finalRateRounding) {
		BigDecimal roundedRate = doEvaluate(baseRate, finalRateRounding);
		
		return roundedRate;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal baseRate, Rounding finalRateRounding);

	protected abstract MapperS<Integer> precision(BigDecimal baseRate, Rounding finalRateRounding);

	protected abstract MapperS<RoundingDirectionEnum> direction(BigDecimal baseRate, Rounding finalRateRounding);

	public static class ApplyFinalRateRoundingDefault extends ApplyFinalRateRounding {
		@Override
		protected BigDecimal doEvaluate(BigDecimal baseRate, Rounding finalRateRounding) {
			BigDecimal roundedRate = null;
			return assignOutput(roundedRate, baseRate, finalRateRounding);
		}
		
		protected BigDecimal assignOutput(BigDecimal roundedRate, BigDecimal baseRate, Rounding finalRateRounding) {
			roundedRate = roundToPrecision.evaluate(baseRate, precision(baseRate, finalRateRounding).get(), direction(baseRate, finalRateRounding).get());
			
			return roundedRate;
		}
		
		@Override
		protected MapperS<Integer> precision(BigDecimal baseRate, Rounding finalRateRounding) {
			if (exists(MapperS.of(finalRateRounding).<Integer>map("getPrecision", rounding -> rounding.getPrecision())).getOrDefault(false)) {
				return MapperS.of(finalRateRounding).<Integer>map("getPrecision", rounding -> rounding.getPrecision());
			}
			return MapperS.of(7);
		}
		
		@Override
		protected MapperS<RoundingDirectionEnum> direction(BigDecimal baseRate, Rounding finalRateRounding) {
			if (exists(MapperS.of(finalRateRounding).<RoundingDirectionEnum>map("getRoundingDirection", rounding -> rounding.getRoundingDirection())).getOrDefault(false)) {
				return MapperS.of(finalRateRounding).<RoundingDirectionEnum>map("getRoundingDirection", rounding -> rounding.getRoundingDirection());
			}
			return MapperS.of(RoundingDirectionEnum.NEAREST);
		}
	}
}
