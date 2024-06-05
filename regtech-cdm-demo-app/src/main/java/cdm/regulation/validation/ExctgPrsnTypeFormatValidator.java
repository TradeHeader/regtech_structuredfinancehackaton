package cdm.regulation.validation;

import cdm.regulation.ExctgPrsn;
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

public class ExctgPrsnTypeFormatValidator implements Validator<ExctgPrsn> {

	private List<ComparisonResult> getComparisonResults(ExctgPrsn o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExctgPrsn> validate(RosettaPath path, ExctgPrsn o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExctgPrsn", ValidationType.TYPE_FORMAT, "ExctgPrsn", path, "", error);
		}
		return success("ExctgPrsn", ValidationType.TYPE_FORMAT, "ExctgPrsn", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExctgPrsn o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExctgPrsn", ValidationType.TYPE_FORMAT, "ExctgPrsn", path, "", res.getError());
				}
				return success("ExctgPrsn", ValidationType.TYPE_FORMAT, "ExctgPrsn", path, "");
			})
			.collect(toList());
	}

}
