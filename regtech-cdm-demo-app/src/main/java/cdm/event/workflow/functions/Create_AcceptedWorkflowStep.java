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
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_AcceptedWorkflowStep.Create_AcceptedWorkflowStepDefault.class)
public abstract class Create_AcceptedWorkflowStep implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param messageInformation Contains all information pertaining the messaging header.
	* @param timestamp The dateTime and qualifier associated with this event.
	* @param eventIdentifier The identifiers that uniquely identify this lifecycle event.
	* @param party The specification of the parties involved in the WorkflowStep.
	* @param account Optional account information that could be associated to the event.
	* @param proposedWorkflowStep Required previous WorkflowStep that provides lineage to WorkflowStep that precedes it.
	* @param businessEvent Life cycle event for the step
	* @return acceptedWorkflowStep Accepted WorkflowStep populated with the business event and associated details about the message, identifiers, event timestamps, parties and accounts involved in the step.
	*/
	public WorkflowStep evaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep proposedWorkflowStep, BusinessEvent businessEvent) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent())),
			"The previous step being accepted must be a proposed step containing an instruction.");
		
		conditionValidator.validate(() -> notEqual(MapperS.of(proposedWorkflowStep).<ActionEnum>map("getAction", workflowStep -> workflowStep.getAction()), MapperS.of(ActionEnum.CANCEL), CardinalityOperator.Any),
			"You cannot accept a business event on a cancelled previous step.");
		
		conditionValidator.validate(() -> notEqual(MapperS.of(proposedWorkflowStep).<Boolean>map("getRejected", workflowStep -> workflowStep.getRejected()), MapperS.of(true), CardinalityOperator.Any),
			"The previous step cannot be rejected.");
		
		WorkflowStep.WorkflowStepBuilder acceptedWorkflowStepBuilder = doEvaluate(messageInformation, timestamp, eventIdentifier, party, account, proposedWorkflowStep, businessEvent);
		
		final WorkflowStep acceptedWorkflowStep;
		if (acceptedWorkflowStepBuilder == null) {
			acceptedWorkflowStep = null;
		} else {
			acceptedWorkflowStep = acceptedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, acceptedWorkflowStep);
		}
		
		return acceptedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep proposedWorkflowStep, BusinessEvent businessEvent);

	public static class Create_AcceptedWorkflowStepDefault extends Create_AcceptedWorkflowStep {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep proposedWorkflowStep, BusinessEvent businessEvent) {
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
			WorkflowStep.WorkflowStepBuilder acceptedWorkflowStep = WorkflowStep.builder();
			return assignOutput(acceptedWorkflowStep, messageInformation, timestamp, eventIdentifier, party, account, proposedWorkflowStep, businessEvent);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder acceptedWorkflowStep, MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, List<? extends Party> party, List<? extends Account> account, WorkflowStep proposedWorkflowStep, BusinessEvent businessEvent) {
			acceptedWorkflowStep
				.setMessageInformation(messageInformation);
			
			acceptedWorkflowStep
				.addTimestamp(timestamp);
			
			acceptedWorkflowStep
				.addEventIdentifier(eventIdentifier);
			
			acceptedWorkflowStep
				.addParty(party);
			
			acceptedWorkflowStep
				.addAccount(account);
			
			acceptedWorkflowStep
				.setPreviousWorkflowStep(ReferenceWithMetaWorkflowStep.builder()
					.setGlobalReference(Optional.ofNullable(proposedWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(proposedWorkflowStep)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			acceptedWorkflowStep
				.setBusinessEvent(businessEvent);
			
			return Optional.ofNullable(acceptedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
