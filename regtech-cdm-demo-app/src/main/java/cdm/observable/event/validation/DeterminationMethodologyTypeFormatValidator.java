package cdm.observable.event.validation;

import cdm.observable.event.DeterminationMethodology;
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

public class DeterminationMethodologyTypeFormatValidator implements Validator<DeterminationMethodology> {

	private List<ComparisonResult> getComparisonResults(DeterminationMethodology o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DeterminationMethodology> validate(RosettaPath path, DeterminationMethodology o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeterminationMethodology", ValidationType.TYPE_FORMAT, "DeterminationMethodology", path, "", error);
		}
		return success("DeterminationMethodology", ValidationType.TYPE_FORMAT, "DeterminationMethodology", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeterminationMethodology o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeterminationMethodology", ValidationType.TYPE_FORMAT, "DeterminationMethodology", path, "", res.getError());
				}
				return success("DeterminationMethodology", ValidationType.TYPE_FORMAT, "DeterminationMethodology", path, "");
			})
			.collect(toList());
	}

}
