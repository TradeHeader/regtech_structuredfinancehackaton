package cdm.event.workflow.validation;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.common.CounterpartyPositionBusinessEvent;
import cdm.event.common.Lineage;
import cdm.event.workflow.CreditLimitInformation;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WorkflowStepValidator implements Validator<WorkflowStep> {

	private List<ComparisonResult> getComparisonResults(WorkflowStep o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("businessEvent", (BusinessEvent) o.getBusinessEvent() != null ? 1 : 0, 0, 1), 
				checkCardinality("counterpartyPositionBusinessEvent", (CounterpartyPositionBusinessEvent) o.getCounterpartyPositionBusinessEvent() != null ? 1 : 0, 0, 1), 
				checkCardinality("proposedEvent", (EventInstruction) o.getProposedEvent() != null ? 1 : 0, 0, 1), 
				checkCardinality("rejected", (Boolean) o.getRejected() != null ? 1 : 0, 0, 1), 
				checkCardinality("previousWorkflowStep", (ReferenceWithMetaWorkflowStep) o.getPreviousWorkflowStep() != null ? 1 : 0, 0, 1), 
				checkCardinality("nextEvent", (EventInstruction) o.getNextEvent() != null ? 1 : 0, 0, 1), 
				checkCardinality("messageInformation", (MessageInformation) o.getMessageInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("timestamp", (List<? extends EventTimestamp>) o.getTimestamp() == null ? 0 : ((List<? extends EventTimestamp>) o.getTimestamp()).size(), 1, 0), 
				checkCardinality("eventIdentifier", (List<? extends Identifier>) o.getEventIdentifier() == null ? 0 : ((List<? extends Identifier>) o.getEventIdentifier()).size(), 1, 0), 
				checkCardinality("action", (ActionEnum) o.getAction() != null ? 1 : 0, 0, 1), 
				checkCardinality("lineage", (Lineage) o.getLineage() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditLimitInformation", (CreditLimitInformation) o.getCreditLimitInformation() != null ? 1 : 0, 0, 1), 
				checkCardinality("workflowState", (WorkflowState) o.getWorkflowState() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<WorkflowStep> validate(RosettaPath path, WorkflowStep o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("WorkflowStep", ValidationType.CARDINALITY, "WorkflowStep", path, "", error);
		}
		return success("WorkflowStep", ValidationType.CARDINALITY, "WorkflowStep", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, WorkflowStep o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("WorkflowStep", ValidationType.CARDINALITY, "WorkflowStep", path, "", res.getError());
				}
				return success("WorkflowStep", ValidationType.CARDINALITY, "WorkflowStep", path, "");
			})
			.collect(toList());
	}

}
