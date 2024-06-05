package cdm.event.workflow.validation;

import cdm.event.workflow.Workflow;
import cdm.event.workflow.WorkflowStep;
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

public class WorkflowValidator implements Validator<Workflow> {

	private List<ComparisonResult> getComparisonResults(Workflow o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("steps", (List<? extends WorkflowStep>) o.getSteps() == null ? 0 : ((List<? extends WorkflowStep>) o.getSteps()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<Workflow> validate(RosettaPath path, Workflow o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Workflow", ValidationType.CARDINALITY, "Workflow", path, "", error);
		}
		return success("Workflow", ValidationType.CARDINALITY, "Workflow", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Workflow o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Workflow", ValidationType.CARDINALITY, "Workflow", path, "", res.getError());
				}
				return success("Workflow", ValidationType.CARDINALITY, "Workflow", path, "");
			})
			.collect(toList());
	}

}
