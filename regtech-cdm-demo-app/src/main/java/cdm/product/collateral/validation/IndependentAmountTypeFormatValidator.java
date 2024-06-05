package cdm.product.collateral.validation;

import cdm.product.collateral.IndependentAmount;
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

public class IndependentAmountTypeFormatValidator implements Validator<IndependentAmount> {

	private List<ComparisonResult> getComparisonResults(IndependentAmount o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<IndependentAmount> validate(RosettaPath path, IndependentAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IndependentAmount", ValidationType.TYPE_FORMAT, "IndependentAmount", path, "", error);
		}
		return success("IndependentAmount", ValidationType.TYPE_FORMAT, "IndependentAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IndependentAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IndependentAmount", ValidationType.TYPE_FORMAT, "IndependentAmount", path, "", res.getError());
				}
				return success("IndependentAmount", ValidationType.TYPE_FORMAT, "IndependentAmount", path, "");
			})
			.collect(toList());
	}

}
