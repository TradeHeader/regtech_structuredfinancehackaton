package cdm.regulation.validation;

import cdm.regulation.Prsn;
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

public class PrsnTypeFormatValidator implements Validator<Prsn> {

	private List<ComparisonResult> getComparisonResults(Prsn o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Prsn> validate(RosettaPath path, Prsn o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Prsn", ValidationType.TYPE_FORMAT, "Prsn", path, "", error);
		}
		return success("Prsn", ValidationType.TYPE_FORMAT, "Prsn", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Prsn o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Prsn", ValidationType.TYPE_FORMAT, "Prsn", path, "", res.getError());
				}
				return success("Prsn", ValidationType.TYPE_FORMAT, "Prsn", path, "");
			})
			.collect(toList());
	}

}
