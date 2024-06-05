package cdm.event.workflow.validation;

import cdm.event.workflow.WarehouseIdentityEnum;
import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.WorkflowStatusEnum;
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

public class WorkflowStateValidator implements Validator<WorkflowState> {

	private List<ComparisonResult> getComparisonResults(WorkflowState o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("workflowStatus", (WorkflowStatusEnum) o.getWorkflowStatus() != null ? 1 : 0, 1, 1), 
				checkCardinality("comment", (String) o.getComment() != null ? 1 : 0, 0, 1), 
				checkCardinality("warehouseIdentity", (WarehouseIdentityEnum) o.getWarehouseIdentity() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<WorkflowState> validate(RosettaPath path, WorkflowState o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("WorkflowState", ValidationType.CARDINALITY, "WorkflowState", path, "", error);
		}
		return success("WorkflowState", ValidationType.CARDINALITY, "WorkflowState", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, WorkflowState o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("WorkflowState", ValidationType.CARDINALITY, "WorkflowState", path, "", res.getError());
				}
				return success("WorkflowState", ValidationType.CARDINALITY, "WorkflowState", path, "");
			})
			.collect(toList());
	}

}
