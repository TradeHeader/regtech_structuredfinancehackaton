package cdm.observable.asset.validation;

import cdm.observable.asset.CreditNotations;
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

public class CreditNotationsTypeFormatValidator implements Validator<CreditNotations> {

	private List<ComparisonResult> getComparisonResults(CreditNotations o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CreditNotations> validate(RosettaPath path, CreditNotations o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditNotations", ValidationType.TYPE_FORMAT, "CreditNotations", path, "", error);
		}
		return success("CreditNotations", ValidationType.TYPE_FORMAT, "CreditNotations", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditNotations o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditNotations", ValidationType.TYPE_FORMAT, "CreditNotations", path, "", res.getError());
				}
				return success("CreditNotations", ValidationType.TYPE_FORMAT, "CreditNotations", path, "");
			})
			.collect(toList());
	}

}
