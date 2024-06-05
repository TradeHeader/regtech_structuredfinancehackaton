package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.Clause;
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

public class ClauseTypeFormatValidator implements Validator<Clause> {

	private List<ComparisonResult> getComparisonResults(Clause o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Clause> validate(RosettaPath path, Clause o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Clause", ValidationType.TYPE_FORMAT, "Clause", path, "", error);
		}
		return success("Clause", ValidationType.TYPE_FORMAT, "Clause", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Clause o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Clause", ValidationType.TYPE_FORMAT, "Clause", path, "", res.getError());
				}
				return success("Clause", ValidationType.TYPE_FORMAT, "Clause", path, "");
			})
			.collect(toList());
	}

}
