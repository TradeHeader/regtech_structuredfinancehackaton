package cdm.event.workflow.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStep.WorkflowStepBuilder;
import cdm.event.workflow.WorkflowStepApproval;
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

@ImplementedBy(Create_ProposedWorkflowStep.Create_ProposedWorkflowStepDefault.class)
public abstract class Create_ProposedWorkflowStep implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param messageInformation Contains all information pertaining the messaging header
	* @param timestamp The dateTime and qualifier associated with this event.
	* @param eventIdentifier The identifiers that uniquely identify this lifecycle event.
	* @param party The specification of the parties involved in the WorkflowStep.
	* @param account Optional account information that could be associated to the event.
	* @param previousWorkflowStep Optional previous WorkflowStep that provides lineage to WorkflowStep that precedes it.
	* @param action Specifies whether the event is new or a correction. The action cannot be a cancellation or new if the previous step is also new.
	* @param proposedEvent The proposed instruction for the step to initiate a workflow e.g. Clearing Instruction or Allocation Instruction
	* @param approval The approval status of all parties on the proposed event.
	* @return proposedWorkflowStep Proposed WorkflowStep populated with the proposed instruction
	*/
	public WorkflowStep evaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, EventInstruction proposedEvent, List<? extends WorkflowStepApproval> approval) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(previousWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent())).and(areEqual(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", workflowStep -> workflowStep.getAction()), MapperS.of(ActionEnum.NEW), CardinalityOperator.All).or(areEqual(MapperS.of(previousWorkflowStep).<ActionEnum>map("getAction", workflowStep -> workflowStep.getAction()), MapperS.of(ActionEnum.CORRECT), CardinalityOperator.All))).getOrDefault(false)) {
				return areEqual(MapperS.of(action), MapperS.of(ActionEnum.CORRECT), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"When the previous step is new or corrected and contains an instruction (proposed), the following action can only be correct.");
		
		conditionValidator.validate(() -> {
			if (notExists(MapperS.of(previousWorkflowStep)).or(exists(MapperS.of(previousWorkflowStep).<BusinessEvent>map("getBusinessEvent", workflowStep -> workflowStep.getBusinessEvent()))).getOrDefault(false)) {
				return areEqual(MapperS.of(action), MapperS.of(ActionEnum.NEW), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"When the previous step contains a business event, the following action can only be new.");
		
		WorkflowStep.WorkflowStepBuilder proposedWorkflowStepBuilder = doEvaluate(messageInformation, timestamp, eventIdentifier, party, account, previousWorkflowStep, action, proposedEvent, approval);
		
		final WorkflowStep proposedWorkflowStep;
		if (proposedWorkflowStepBuilder == null) {
			proposedWorkflowStep = null;
		} else {
			proposedWorkflowStep = proposedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, proposedWorkflowStep);
		}
		
		return proposedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, EventInstruction proposedEvent, List<? extends WorkflowStepApproval> approval);

	public static class Create_ProposedWorkflowStepDefault extends Create_ProposedWorkflowStep {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, EventInstruction proposedEvent, List<? extends WorkflowStepApproval> approval) {
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
			if (approval == null) {
				approval = Collections.emptyList();
			}
			WorkflowStep.WorkflowStepBuilder proposedWorkflowStep = WorkflowStep.builder();
			return assignOutput(proposedWorkflowStep, messageInformation, timestamp, eventIdentifier, party, account, previousWorkflowStep, action, proposedEvent, approval);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder proposedWorkflowStep, MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep previousWorkflowStep, ActionEnum action, EventInstruction proposedEvent, List<? extends WorkflowStepApproval> approval) {
			proposedWorkflowStep
				.setMessageInformation(messageInformation);
			
			proposedWorkflowStep
				.addTimestamp(timestamp);
			
			proposedWorkflowStep
				.addEventIdentifier(eventIdentifier);
			
			proposedWorkflowStep
				.addParty(party);
			
			proposedWorkflowStep
				.addAccount(account);
			
			proposedWorkflowStep
				.setPreviousWorkflowStep(ReferenceWithMetaWorkflowStep.builder()
					.setGlobalReference(Optional.ofNullable(previousWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(previousWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			proposedWorkflowStep
				.setProposedEvent(proposedEvent);
			
			proposedWorkflowStep
				.addApproval(approval);
			
			return Optional.ofNullable(proposedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
