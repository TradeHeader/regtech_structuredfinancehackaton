package cdm.product.asset.validation;

import cdm.product.asset.FixedAmountCalculationDetails;
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

public class FixedAmountCalculationDetailsTypeFormatValidator implements Validator<FixedAmountCalculationDetails> {

	private List<ComparisonResult> getComparisonResults(FixedAmountCalculationDetails o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FixedAmountCalculationDetails> validate(RosettaPath path, FixedAmountCalculationDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FixedAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FixedAmountCalculationDetails", path, "", error);
		}
		return success("FixedAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FixedAmountCalculationDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FixedAmountCalculationDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FixedAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FixedAmountCalculationDetails", path, "", res.getError());
				}
				return success("FixedAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FixedAmountCalculationDetails", path, "");
			})
			.collect(toList());
	}

}
