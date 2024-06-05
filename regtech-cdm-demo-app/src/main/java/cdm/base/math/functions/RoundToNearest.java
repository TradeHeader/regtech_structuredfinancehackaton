package cdm.base.math.functions;

import cdm.base.math.RoundingModeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(RoundToNearest.RoundToNearestDefault.class)
public abstract class RoundToNearest implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;

	/**
	* @param value The original (unrounded) number.
	* @param nearest The nearest number to round to.
	* @param roundingMode The method of rounding (up to nearest/down to nearest).
	* @return roundedValue 
	*/
	public BigDecimal evaluate(BigDecimal value, BigDecimal nearest, RoundingModeEnum roundingMode) {
		// pre-conditions
		conditionValidator.validate(() -> greaterThan(MapperS.of(nearest), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All),
			"");
		
		BigDecimal roundedValue = doEvaluate(value, nearest, roundingMode);
		
		return roundedValue;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal value, BigDecimal nearest, RoundingModeEnum roundingMode);

	public static class RoundToNearestDefault extends RoundToNearest {
		@Override
		protected BigDecimal doEvaluate(BigDecimal value, BigDecimal nearest, RoundingModeEnum roundingMode) {
			BigDecimal roundedValue = null;
			return assignOutput(roundedValue, value, nearest, roundingMode);
		}
		
		protected BigDecimal assignOutput(BigDecimal roundedValue, BigDecimal value, BigDecimal nearest, RoundingModeEnum roundingMode) {
			return roundedValue;
		}
	}
}
