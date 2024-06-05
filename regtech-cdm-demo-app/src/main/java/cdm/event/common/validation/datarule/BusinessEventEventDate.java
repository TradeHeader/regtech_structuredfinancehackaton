package cdm.event.common.validation.datarule;

import cdm.event.common.BusinessEvent;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("BusinessEventEventDate")
@ImplementedBy(BusinessEventEventDate.Default.class)
public interface BusinessEventEventDate extends Validator<BusinessEvent> {
	
	String NAME = "BusinessEventEventDate";
	String DEFINITION = "eventDate exists";
	
	ValidationResult<BusinessEvent> validate(RosettaPath path, BusinessEvent businessEvent);
	
	class Default implements BusinessEventEventDate {
	
		@Override
		public ValidationResult<BusinessEvent> validate(RosettaPath path, BusinessEvent businessEvent) {
			ComparisonResult result = executeDataRule(businessEvent);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BusinessEvent", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "BusinessEvent", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(BusinessEvent businessEvent) {
			try {
				return exists(MapperS.of(businessEvent).<Date>map("getEventDate", eventInstruction -> eventInstruction.getEventDate()));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements BusinessEventEventDate {
	
		@Override
		public ValidationResult<BusinessEvent> validate(RosettaPath path, BusinessEvent businessEvent) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BusinessEvent", path, DEFINITION);
		}
	}
}
