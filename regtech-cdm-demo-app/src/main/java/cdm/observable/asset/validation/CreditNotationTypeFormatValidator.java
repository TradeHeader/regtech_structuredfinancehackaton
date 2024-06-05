package cdm.observable.asset.validation;

import cdm.observable.asset.CreditNotation;
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

public class CreditNotationTypeFormatValidator implements Validator<CreditNotation> {

	private List<ComparisonResult> getComparisonResults(CreditNotation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CreditNotation> validate(RosettaPath path, CreditNotation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditNotation", ValidationType.TYPE_FORMAT, "CreditNotation", path, "", error);
		}
		return success("CreditNotation", ValidationType.TYPE_FORMAT, "CreditNotation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditNotation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditNotation", ValidationType.TYPE_FORMAT, "CreditNotation", path, "", res.getError());
				}
				return success("CreditNotation", ValidationType.TYPE_FORMAT, "CreditNotation", path, "");
			})
			.collect(toList());
	}

}
