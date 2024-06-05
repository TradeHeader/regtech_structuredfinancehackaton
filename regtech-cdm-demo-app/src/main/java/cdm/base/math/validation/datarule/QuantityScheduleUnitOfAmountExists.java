package cdm.base.math.validation.datarule;

import cdm.base.math.QuantitySchedule;
import cdm.base.math.UnitType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("QuantityScheduleUnitOfAmountExists")
@ImplementedBy(QuantityScheduleUnitOfAmountExists.Default.class)
public interface QuantityScheduleUnitOfAmountExists extends Validator<QuantitySchedule> {
	
	String NAME = "QuantityScheduleUnitOfAmountExists";
	String DEFINITION = "unit exists";
	
	ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule quantitySchedule);
	
	class Default implements QuantityScheduleUnitOfAmountExists {
	
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
				return exists(MapperS.of(quantitySchedule).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements QuantityScheduleUnitOfAmountExists {
	
		@Override
		public ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule quantitySchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "QuantitySchedule", path, DEFINITION);
		}
	}
}
