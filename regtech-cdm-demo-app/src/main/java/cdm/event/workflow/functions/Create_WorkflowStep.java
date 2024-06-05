package cdm.event.workflow.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStep.WorkflowStepBuilder;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_WorkflowStep.Create_WorkflowStepDefault.class)
public abstract class Create_WorkflowStep implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param messageInformation Contains all information pertaining the messaging header.
	* @param timestamp The dateTime and qualifier associated with this event.
	* @param eventIdentifier The identifiers that uniquely identify this lifecycle event.
	* @param party The specification of the parties involved in the WorkflowStep.
	* @param account Optional account information that could be associated to the event.
	* @param previousWorkflowStep Optional previous WorkflowStep that provides lineage to WorkflowStep that precedes it. If specified, the previous action is used to constrain the actions allows to the resulting workflow step.
	* @param action Specifies whether the event is a new, a correction or a cancellation. When a previous workflow step is specified, the allowed actions are as follows; New -&gt; New, New -&gt; Correct, New -&gt; Cancel, Correct -&gt; Correct and Correct -&gt; Cancel. When a previous workflow is not specified, the action must be New. Two consecutive workflow steps with action New, is valid when you have multiple steps e.g. new execution -&gt; new contract formation
	* @param businessEvent Life cycle event for the step. The business event must be specified if the action is new or corrected, and must be absent in the case of a cancel where the previous step would provide the lineage to the business event.
	* @return workflowStep Workflow step with a business event (in the event of action being new or correct) and associated details about the message, identifiers, event timestamps, parties and accounts involved in the step.
	*/
	public WorkflowStep evaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, BusinessEvent businessEvent) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(previousWorkflowStep)).getOrDefault(false)) {
				return exists(MapperS.of(previousWorkflowStep).<BusinessEvent>map("getBusinessEvent", _workflowStep -> _workflowStep.getBusinessEvent()));
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"The previous workflow step must contain a business event. Use Create_AcceptedWorkflowStep when the previous workflow step is a proposal.");
		
		conditionValidator.validate(() -> {
			if (areEqual(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", _workflowStep -> _workflowStep.getAction()), MapperS.of(ActionEnum.NEW), CardinalityOperator.All).or(areEqual(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", _workflowStep -> _workflowStep.getAction()), MapperS.of(ActionEnum.CORRECT), CardinalityOperator.All)).getOrDefault(false)) {
				return areEqual(MapperS.of(action), MapperS.of(ActionEnum.NEW), CardinalityOperator.All).or(areEqual(MapperS.of(action), MapperS.of(ActionEnum.CORRECT), CardinalityOperator.All)).or(areEqual(MapperS.of(action), MapperS.of(ActionEnum.CANCEL), CardinalityOperator.All));
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"Valid action transitions are: New -> New, New -> Correct, New -> Cancel, Correct -> New, Correct -> Correct and Correct -> Cancel");
		
		conditionValidator.validate(() -> notEqual(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", _workflowStep -> _workflowStep.getAction()), MapperS.of(ActionEnum.CANCEL), CardinalityOperator.Any),
			"You cannot create a business event on a cancelled previous step");
		
		conditionValidator.validate(() -> {
			if (notExists(MapperS.of(previousWorkflowStep)).or(notExists(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", _workflowStep -> _workflowStep.getAction()))).getOrDefault(false)) {
				return areEqual(MapperS.of(action), MapperS.of(ActionEnum.NEW), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"Action must be New if there is no previous step");
		
		WorkflowStep.WorkflowStepBuilder workflowStepBuilder = doEvaluate(messageInformation, timestamp, eventIdentifier, party, account, previousWorkflowStep, action, businessEvent);
		
		final WorkflowStep workflowStep;
		if (workflowStepBuilder == null) {
			workflowStep = null;
		} else {
			workflowStep = workflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, workflowStep);
		}
		
		return workflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, BusinessEvent businessEvent);

	public static class Create_WorkflowStepDefault extends Create_WorkflowStep {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, BusinessEvent businessEvent) {
			if (timestamp == null) {
				timestamp = Collections.emptyList();
			}
			if (eventIdentifier == null) {
				eventIdentifier = Collections.emptyList();
			}
			if (party == null) {
				party = Collections.emptyList();
			}
			if (account == null) {
				account = Collections.emptyList();
			}
			WorkflowStep.WorkflowStepBuilder workflowStep = WorkflowStep.builder();
			return assignOutput(workflowStep, messageInformation, timestamp, eventIdentifier, party, account, previousWorkflowStep, action, businessEvent);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder workflowStep, MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, BusinessEvent businessEvent) {
			workflowStep
				.setAction(action);
			
			workflowStep
				.setMessageInformation(messageInformation);
			
			workflowStep
				.addTimestamp(timestamp);
			
			workflowStep
				.addEventIdentifier(eventIdentifier);
			
			workflowStep
				.addParty(party);
			
			workflowStep
				.addAccount(account);
			
			WorkflowStep workflowStepPreviousWorkflowStep = null;
			if (exists(MapperS.of(previousWorkflowStep)).getOrDefault(false)) {
				workflowStepPreviousWorkflowStep = previousWorkflowStep;
			}
			workflowStep
				.setPreviousWorkflowStep(ReferenceWithMetaWorkflowStep.builder()
					.setGlobalReference(Optional.ofNullable(workflowStepPreviousWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(workflowStepPreviousWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			workflowStep
				.setBusinessEvent(businessEvent);
			
			return Optional.ofNullable(workflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
