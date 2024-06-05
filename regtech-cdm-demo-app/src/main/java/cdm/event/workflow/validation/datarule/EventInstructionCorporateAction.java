package cdm.event.workflow.validation.datarule;

import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import cdm.event.workflow.EventInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("EventInstructionCorporateAction")
@ImplementedBy(EventInstructionCorporateAction.Default.class)
public interface EventInstructionCorporateAction extends Validator<EventInstruction> {
	
	String NAME = "EventInstructionCorporateAction";
	String DEFINITION = "if corporateActionIntent exists then intent = EventIntentEnum -> CorporateActionAdjustment";
	
	ValidationResult<EventInstruction> validate(RosettaPath path, EventInstruction eventInstruction);
	
	class Default implements EventInstructionCorporateAction {
	
		@Override
		public ValidationResult<EventInstruction> validate(RosettaPath path, EventInstruction eventInstruction) {
			ComparisonResult result = executeDataRule(eventInstruction);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EventInstruction", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "EventInstruction", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(EventInstruction eventInstruction) {
			try {
				if (exists(MapperS.of(eventInstruction).<CorporateActionTypeEnum>map("getCorporateActionIntent", _eventInstruction -> _eventInstruction.getCorporateActionIntent())).getOrDefault(false)) {
					return areEqual(MapperS.of(eventInstruction).<EventIntentEnum>map("getIntent", _eventInstruction -> _eventInstruction.getIntent()), MapperS.of(EventIntentEnum.CORPORATE_ACTION_ADJUSTMENT), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements EventInstructionCorporateAction {
	
		@Override
		public ValidationResult<EventInstruction> validate(RosettaPath path, EventInstruction eventInstruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EventInstruction", path, DEFINITION);
		}
	}
}
