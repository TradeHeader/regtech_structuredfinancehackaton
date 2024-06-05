package cdm.legaldocumentation.contract.validation;

import cdm.legaldocumentation.contract.Agreement;
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

public class AgreementTypeFormatValidator implements Validator<Agreement> {

	private List<ComparisonResult> getComparisonResults(Agreement o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Agreement> validate(RosettaPath path, Agreement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Agreement", ValidationType.TYPE_FORMAT, "Agreement", path, "", error);
		}
		return success("Agreement", ValidationType.TYPE_FORMAT, "Agreement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Agreement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Agreement", ValidationType.TYPE_FORMAT, "Agreement", path, "", res.getError());
				}
				return success("Agreement", ValidationType.TYPE_FORMAT, "Agreement", path, "");
			})
			.collect(toList());
	}

}
