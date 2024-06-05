package cdm.event.workflow.validation;

import cdm.event.workflow.WorkflowStep;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WorkflowStepTypeFormatValidator implements Validator<WorkflowStep> {

	private List<ComparisonResult> getComparisonResults(WorkflowStep o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("WorkflowStep", ValidationType.TYPE_FORMAT, "WorkflowStep", path, "", error);
		}
		return success("WorkflowStep", ValidationType.TYPE_FORMAT, "WorkflowStep", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, WorkflowStep o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("WorkflowStep", ValidationType.TYPE_FORMAT, "WorkflowStep", path, "", res.getError());
				}
				return success("WorkflowStep", ValidationType.TYPE_FORMAT, "WorkflowStep", path, "");
			})
			.collect(toList());
	}

}
