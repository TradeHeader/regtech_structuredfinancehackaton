package cdm.base.math.validation.datarule;

import cdm.base.math.Measure;
import cdm.base.math.QuantitySchedule;
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
@RosettaDataRule("QuantityScheduleQuantity_multiplier")
@ImplementedBy(QuantityScheduleQuantityMultiplier.Default.class)
public interface QuantityScheduleQuantityMultiplier extends Validator<QuantitySchedule> {
	
	String NAME = "QuantityScheduleQuantity_multiplier";
	String DEFINITION = "if multiplier exists then multiplier -> value >= 0.0";
	
	ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule quantitySchedule);
	
	class Default implements QuantityScheduleQuantityMultiplier {
	
		@Override
		public ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule quantitySchedule) {
			ComparisonResult result = executeDataRule(quantitySchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "QuantitySchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "QuantitySchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(QuantitySchedule quantitySchedule) {
			try {
				if (exists(MapperS.of(quantitySchedule).<Measure>map("getMultiplier", _quantitySchedule -> _quantitySchedule.getMultiplier())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(quantitySchedule).<Measure>map("getMultiplier", _quantitySchedule -> _quantitySchedule.getMultiplier()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements QuantityScheduleQuantityMultiplier {
	
		@Override
		public ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule quantitySchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "QuantitySchedule", path, DEFINITION);
		}
	}
}
