package cdm.event.workflow.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStep.WorkflowStepBuilder;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_RejectedWorkflowStep.Create_RejectedWorkflowStepDefault.class)
public abstract class Create_RejectedWorkflowStep implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param messageInformation Contains all information pertaining the messaging header
	* @param timestamp The dateTime and qualifier associated with this event.
	* @param eventIdentifier The identifiers that uniquely identify this lifecycle event.
	* @param proposedWorkflowStep Required previous WorkflowStep that provides lineage to WorkflowStep that precedes it.
	* @return rejectedWorkflowStep Rejected WorkflowStep with lineage to the proposed step that preceded it.
	*/
	public WorkflowStep evaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, WorkflowStep proposedWorkflowStep) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(proposedWorkflowStep).<EventInstruction>map("getProposedEvent", workflowStep -> workflowStep.getProposedEvent())),
			"The previous proposed step being rejected must exist");
		
		WorkflowStep.WorkflowStepBuilder rejectedWorkflowStepBuilder = doEvaluate(messageInformation, timestamp, eventIdentifier, proposedWorkflowStep);
		
		final WorkflowStep rejectedWorkflowStep;
		if (rejectedWorkflowStepBuilder == null) {
			rejectedWorkflowStep = null;
		} else {
			rejectedWorkflowStep = rejectedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, rejectedWorkflowStep);
		}
		
		return rejectedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, WorkflowStep proposedWorkflowStep);

	public static class Create_RejectedWorkflowStepDefault extends Create_RejectedWorkflowStep {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, WorkflowStep proposedWorkflowStep) {
			if (timestamp == null) {
				timestamp = Collections.emptyList();
			}
			if (eventIdentifier == null) {
				eventIdentifier = Collections.emptyList();
			}
			WorkflowStep.WorkflowStepBuilder rejectedWorkflowStep = WorkflowStep.builder();
			return assignOutput(rejectedWorkflowStep, messageInformation, timestamp, eventIdentifier, proposedWorkflowStep);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder rejectedWorkflowStep, MessageInformation messageInformation, List<? extends EventTimestamp> timestamp, List<? extends Identifier> eventIdentifier, WorkflowStep proposedWorkflowStep) {
			rejectedWorkflowStep
				.setMessageInformation(messageInformation);
			
			rejectedWorkflowStep
				.addTimestamp(timestamp);
			
			rejectedWorkflowStep
				.addEventIdentifier(eventIdentifier);
			
			rejectedWorkflowStep
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
			
			rejectedWorkflowStep
				.setRejected(true);
			
			return Optional.ofNullable(rejectedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
