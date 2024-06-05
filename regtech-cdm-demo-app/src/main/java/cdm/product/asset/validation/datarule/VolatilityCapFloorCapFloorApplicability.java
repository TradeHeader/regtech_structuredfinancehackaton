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
@RosettaDataRule("VolatilityCapFloorCapFloorApplicability")
@ImplementedBy(VolatilityCapFloorCapFloorApplicability.Default.class)
public interface VolatilityCapFloorCapFloorApplicability extends Validator<VolatilityCapFloor> {
	
	String NAME = "VolatilityCapFloorCapFloorApplicability";
	String DEFINITION = "if applicable = True then totalVolatilityCap exists or volatilityCapFactor exists else totalVolatilityCap is absent and volatilityCapFactor is absent";
	
	ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor volatilityCapFloor);
	
	class Default implements VolatilityCapFloorCapFloorApplicability {
	
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
				if (areEqual(MapperS.of(volatilityCapFloor).<Boolean>map("getApplicable", _volatilityCapFloor -> _volatilityCapFloor.getApplicable()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getTotalVolatilityCap", _volatilityCapFloor -> _volatilityCapFloor.getTotalVolatilityCap())).or(exists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getVolatilityCapFactor", _volatilityCapFloor -> _volatilityCapFloor.getVolatilityCapFactor())));
				}
				return notExists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getTotalVolatilityCap", _volatilityCapFloor -> _volatilityCapFloor.getTotalVolatilityCap())).and(notExists(MapperS.of(volatilityCapFloor).<BigDecimal>map("getVolatilityCapFactor", _volatilityCapFloor -> _volatilityCapFloor.getVolatilityCapFactor())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VolatilityCapFloorCapFloorApplicability {
	
		@Override
		public ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor volatilityCapFloor) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VolatilityCapFloor", path, DEFINITION);
		}
	}
}
