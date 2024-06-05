package cdm.event.position.validation.datarule;

import cdm.event.position.AvailableInventoryRecord;
import cdm.event.position.SecurityLocate;
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
@RosettaDataRule("SecurityLocateRequestOneSecurityMinimum")
@ImplementedBy(SecurityLocateRequestOneSecurityMinimum.Default.class)
public interface SecurityLocateRequestOneSecurityMinimum extends Validator<SecurityLocate> {
	
	String NAME = "SecurityLocateRequestOneSecurityMinimum";
	String DEFINITION = "availableInventoryRecord exists";
	
	ValidationResult<SecurityLocate> validate(RosettaPath path, SecurityLocate securityLocate);
	
	class Default implements SecurityLocateRequestOneSecurityMinimum {
	
		@Override
		public ValidationResult<SecurityLocate> validate(RosettaPath path, SecurityLocate securityLocate) {
			ComparisonResult result = executeDataRule(securityLocate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityLocate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SecurityLocate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SecurityLocate securityLocate) {
			try {
				return exists(MapperS.of(securityLocate).<AvailableInventoryRecord>mapC("getAvailableInventoryRecord", availableInventory -> availableInventory.getAvailableInventoryRecord()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SecurityLocateRequestOneSecurityMinimum {
	
		@Override
		public ValidationResult<SecurityLocate> validate(RosettaPath path, SecurityLocate securityLocate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityLocate", path, DEFINITION);
		}
	}
}
