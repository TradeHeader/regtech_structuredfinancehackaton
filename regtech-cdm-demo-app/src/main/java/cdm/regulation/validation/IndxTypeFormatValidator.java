package cdm.regulation.validation;

import cdm.regulation.Indx;
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

public class IndxTypeFormatValidator implements Validator<Indx> {

	private List<ComparisonResult> getComparisonResults(Indx o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Indx> validate(RosettaPath path, Indx o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Indx", ValidationType.TYPE_FORMAT, "Indx", path, "", error);
		}
		return success("Indx", ValidationType.TYPE_FORMAT, "Indx", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Indx o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Indx", ValidationType.TYPE_FORMAT, "Indx", path, "", res.getError());
				}
				return success("Indx", ValidationType.TYPE_FORMAT, "Indx", path, "");
			})
			.collect(toList());
	}

}
