package cdm.product.asset.validation.datarule;

import cdm.product.asset.VolatilityCapFloor;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("VolatilityCapFloorPositiveCaps")
@ImplementedBy(VolatilityCapFloorPositiveCaps.Default.class)
public interface VolatilityCapFloorPositiveCaps extends Validator<VolatilityCapFloor> {
	
	String NAME = "VolatilityCapFloorPositiveCaps";
	String DEFINITION = "if totalVolatilityCap exists then totalVolatilityCap >= 0 and if volatilityCapFactor exists then volatilityCapFactor >= 0";
	
	ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor volatilityCapFloor);
	
	class Default implements VolatilityCapFloorPositiveCaps {
	
		@Override
		public ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor volatilityCapFloor) {
			ComparisonResult result = executeDataRule(volatilityCapFloor);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VolatilityCapFloor", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VolatilityCapFloor", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VolatilityCapFloor volatilityCapFloor) {
			try {
				if (exists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getTotalVolatilityCap", _volatilityCapFloor -> _volatilityCapFloor.getTotalVolatilityCap())).getOrDefault(false)) {
					final ComparisonResult ifThenElseResult;
					if (exists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getVolatilityCapFactor", _volatilityCapFloor -> _volatilityCapFloor.getVolatilityCapFactor())).getOrDefault(false)) {
						ifThenElseResult = greaterThanEquals(MapperS.of(volatilityCapFloor).<BigDecimal>map("getVolatilityCapFactor", _volatilityCapFloor -> _volatilityCapFloor.getVolatilityCapFactor()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
					} else {
						ifThenElseResult = ComparisonResult.successEmptyOperand("");
					}
					return greaterThanEquals(MapperS.of(volatilityCapFloor).<BigDecimal>map("getTotalVolatilityCap", _volatilityCapFloor -> _volatilityCapFloor.getTotalVolatilityCap()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).and(ifThenElseResult);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VolatilityCapFloorPositiveCaps {
	
		@Override
		public ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor volatilityCapFloor) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VolatilityCapFloor", path, DEFINITION);
		}
	}
}
