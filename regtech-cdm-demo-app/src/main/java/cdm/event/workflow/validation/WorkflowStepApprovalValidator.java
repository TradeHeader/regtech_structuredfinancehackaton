package cdm.event.workflow.validation;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.WorkflowStepApproval;
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

public class WorkflowStepApprovalValidator implements Validator<WorkflowStepApproval> {

	private List<ComparisonResult> getComparisonResults(WorkflowStepApproval o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("approved", (Boolean) o.getApproved() != null ? 1 : 0, 1, 1), 
				checkCardinality("party", (ReferenceWithMetaParty) o.getParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("rejectedReason", (String) o.getRejectedReason() != null ? 1 : 0, 0, 1), 
				checkCardinality("timestamp", (EventTimestamp) o.getTimestamp() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<WorkflowStepApproval> validate(RosettaPath path, WorkflowStepApproval o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("WorkflowStepApproval", ValidationType.CARDINALITY, "WorkflowStepApproval", path, "", error);
		}
		return success("WorkflowStepApproval", ValidationType.CARDINALITY, "WorkflowStepApproval", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, WorkflowStepApproval o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("WorkflowStepApproval", ValidationType.CARDINALITY, "WorkflowStepApproval", path, "", res.getError());
				}
				return success("WorkflowStepApproval", ValidationType.CARDINALITY, "WorkflowStepApproval", path, "");
			})
			.collect(toList());
	}

}
