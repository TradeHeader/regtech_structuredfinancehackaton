package cdm.product.asset.floatingrate.validation;

import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
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

public class FloatingAmountCalculationDetailsTypeFormatValidator implements Validator<FloatingAmountCalculationDetails> {

	private List<ComparisonResult> getComparisonResults(FloatingAmountCalculationDetails o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingAmountCalculationDetails> validate(RosettaPath path, FloatingAmountCalculationDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FloatingAmountCalculationDetails", path, "", error);
		}
		return success("FloatingAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FloatingAmountCalculationDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingAmountCalculationDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FloatingAmountCalculationDetails", path, "", res.getError());
				}
				return success("FloatingAmountCalculationDetails", ValidationType.TYPE_FORMAT, "FloatingAmountCalculationDetails", path, "");
			})
			.collect(toList());
	}

}
