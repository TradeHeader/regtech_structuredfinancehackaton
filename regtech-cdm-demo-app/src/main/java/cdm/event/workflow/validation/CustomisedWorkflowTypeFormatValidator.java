package cdm.event.workflow.validation;

import cdm.event.workflow.CustomisedWorkflow;
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

public class CustomisedWorkflowTypeFormatValidator implements Validator<CustomisedWorkflow> {

	private List<ComparisonResult> getComparisonResults(CustomisedWorkflow o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CustomisedWorkflow> validate(RosettaPath path, CustomisedWorkflow o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CustomisedWorkflow", ValidationType.TYPE_FORMAT, "CustomisedWorkflow", path, "", error);
		}
		return success("CustomisedWorkflow", ValidationType.TYPE_FORMAT, "CustomisedWorkflow", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CustomisedWorkflow o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CustomisedWorkflow", ValidationType.TYPE_FORMAT, "CustomisedWorkflow", path, "", res.getError());
				}
				return success("CustomisedWorkflow", ValidationType.TYPE_FORMAT, "CustomisedWorkflow", path, "");
			})
			.collect(toList());
	}

}
