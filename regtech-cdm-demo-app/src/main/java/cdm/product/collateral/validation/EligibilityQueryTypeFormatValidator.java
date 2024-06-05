package cdm.product.collateral.validation;

import cdm.product.collateral.EligibilityQuery;
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

public class EligibilityQueryTypeFormatValidator implements Validator<EligibilityQuery> {

	private List<ComparisonResult> getComparisonResults(EligibilityQuery o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EligibilityQuery> validate(RosettaPath path, EligibilityQuery o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EligibilityQuery", ValidationType.TYPE_FORMAT, "EligibilityQuery", path, "", error);
		}
		return success("EligibilityQuery", ValidationType.TYPE_FORMAT, "EligibilityQuery", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EligibilityQuery o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EligibilityQuery", ValidationType.TYPE_FORMAT, "EligibilityQuery", path, "", res.getError());
				}
				return success("EligibilityQuery", ValidationType.TYPE_FORMAT, "EligibilityQuery", path, "");
			})
			.collect(toList());
	}

}
