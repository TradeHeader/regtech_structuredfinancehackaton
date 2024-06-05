package cdm.event.workflow.validation;

import cdm.event.workflow.PartyCustomisedWorkflow;
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

public class PartyCustomisedWorkflowTypeFormatValidator implements Validator<PartyCustomisedWorkflow> {

	private List<ComparisonResult> getComparisonResults(PartyCustomisedWorkflow o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PartyCustomisedWorkflow> validate(RosettaPath path, PartyCustomisedWorkflow o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyCustomisedWorkflow", ValidationType.TYPE_FORMAT, "PartyCustomisedWorkflow", path, "", error);
		}
		return success("PartyCustomisedWorkflow", ValidationType.TYPE_FORMAT, "PartyCustomisedWorkflow", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyCustomisedWorkflow o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyCustomisedWorkflow", ValidationType.TYPE_FORMAT, "PartyCustomisedWorkflow", path, "", res.getError());
				}
				return success("PartyCustomisedWorkflow", ValidationType.TYPE_FORMAT, "PartyCustomisedWorkflow", path, "");
			})
			.collect(toList());
	}

}
