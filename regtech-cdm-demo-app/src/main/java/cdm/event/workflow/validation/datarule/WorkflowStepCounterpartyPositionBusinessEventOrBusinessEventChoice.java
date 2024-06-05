package cdm.event.workflow.validation.datarule;

import cdm.event.workflow.WorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice")
@ImplementedBy(WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice.Default.class)
public interface WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice extends Validator<WorkflowStep> {
	
	String NAME = "WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice";
	String DEFINITION = "required choice counterpartyPositionBusinessEvent, businessEvent";
	
	ValidationResult<WorkflowStep> validate(RosettaPath path, WorkflowStep workflowStep);
	
	class Default implements WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice {
	
		@Override
		public ValidationResult<WorkflowStep> validate(RosettaPath path, WorkflowStep workflowStep) {
			ComparisonResult result = executeDataRule(workflowStep);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "WorkflowStep", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "WorkflowStep", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(WorkflowStep workflowStep) {
			try {
				return choice(MapperS.of(workflowStep), Arrays.asList("counterpartyPositionBusinessEvent", "businessEvent"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements WorkflowStepCounterpartyPositionBusinessEventOrBusinessEventChoice {
	
		@Override
		public ValidationResult<WorkflowStep> validate(RosettaPath path, WorkflowStep workflowStep) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "WorkflowStep", path, DEFINITION);
		}
	}
}
