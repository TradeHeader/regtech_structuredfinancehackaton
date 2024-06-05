package cdm.event.workflow.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.functions.Create_BusinessEvent;
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
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_AcceptedWorkflowStepFromInstruction.Create_AcceptedWorkflowStepFromInstructionDefault.class)
public abstract class Create_AcceptedWorkflowStepFromInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_BusinessEvent create_BusinessEvent;

	/**
	* @param proposedWorkflowStep WorkflowStep as instruction.
	* @return acceptedWorkflowStep Accepted WorkflowStep populated with the business event and associated details about the message, identifiers, event timestamps, parties and accounts involved in the step.
	*/
	public WorkflowStep evaluate(WorkflowStep proposedWorkflowStep) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent())),
			"The previous step being accepted must be a proposed step containing an instruction.");
		
		conditionValidator.validate(() -> notEqual(MapperS.of(proposedWorkflowStep).<ActionEnum>map("getAction", workflowStep -> workflowStep.getAction()), MapperS.of(ActionEnum.CANCEL), CardinalityOperator.Any),
			"You cannot accept a business event on a cancelled previous step.");
		
		conditionValidator.validate(() -> notEqual(MapperS.of(proposedWorkflowStep).<Boolean>map("getRejected", workflowStep -> workflowStep.getRejected()), MapperS.of(true), CardinalityOperator.Any),
			"The previous step cannot be rejected.");
		
		WorkflowStep.WorkflowStepBuilder acceptedWorkflowStepBuilder = doEvaluate(proposedWorkflowStep);
		
		final WorkflowStep acceptedWorkflowStep;
		if (acceptedWorkflowStepBuilder == null) {
			acceptedWorkflowStep = null;
		} else {
			acceptedWorkflowStep = acceptedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, acceptedWorkflowStep);
		}
		
		return acceptedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(WorkflowStep proposedWorkflowStep);

	public static class Create_AcceptedWorkflowStepFromInstructionDefault extends Create_AcceptedWorkflowStepFromInstruction {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(WorkflowStep proposedWorkflowStep) {
			WorkflowStep.WorkflowStepBuilder acceptedWorkflowStep = WorkflowStep.builder();
			return assignOutput(acceptedWorkflowStep, proposedWorkflowStep);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder acceptedWorkflowStep, WorkflowStep proposedWorkflowStep) {
			acceptedWorkflowStep
				.setAction(MapperS.of(proposedWorkflowStep).<ActionEnum>map("getAction", workflowStep -> workflowStep.getAction()).get());
			
			acceptedWorkflowStep
				.setMessageInformation(MapperS.of(proposedWorkflowStep).<MessageInformation>map("getMessageInformation", workflowStep -> workflowStep.getMessageInformation()).get());
			
			acceptedWorkflowStep
				.addTimestamp(MapperS.of(proposedWorkflowStep).<EventTimestamp>mapC("getTimestamp", workflowStep -> workflowStep.getTimestamp()).getMulti());
			
			acceptedWorkflowStep
				.addEventIdentifier(MapperS.of(proposedWorkflowStep).<Identifier>mapC("getEventIdentifier", workflowStep -> workflowStep.getEventIdentifier()).getMulti());
			
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
				.setNextEvent(MapperS.of(proposedWorkflowStep).<EventInstruction>map("getNextEvent", workflowStep -> workflowStep.getNextEvent()).get());
			
			acceptedWorkflowStep
				.setBusinessEvent(create_BusinessEvent.evaluate(MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent()).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).getMulti(), MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent()).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent()).get(), MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent()).<Date>map("getEventDate", eventInstruction -> eventInstruction.getEventDate()).get(), MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent()).<Date>map("getEffectiveDate", eventInstruction -> eventInstruction.getEffectiveDate()).get()));
			
			acceptedWorkflowStep
				.addParty(distinct(MapperS.of(acceptedWorkflowStep).<BusinessEvent>map("getBusinessEvent", workflowStep -> workflowStep.getBusinessEvent()).<TradeState>mapC("getAfter", businessEvent -> businessEvent.getAfter()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Party>mapC("getParty", trade -> trade.getParty())).getMulti());
			
			acceptedWorkflowStep
				.addAccount(distinct(MapperS.of(acceptedWorkflowStep).<BusinessEvent>map("getBusinessEvent", workflowStep -> workflowStep.getBusinessEvent()).<TradeState>mapC("getAfter", businessEvent -> businessEvent.getAfter()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Account>mapC("getAccount", trade -> trade.getAccount())).getMulti());
			
			return Optional.ofNullable(acceptedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
