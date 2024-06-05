package cdm.product.asset.validation;

import cdm.product.asset.FloatingRateSpecification;
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

public class FloatingRateSpecificationTypeFormatValidator implements Validator<FloatingRateSpecification> {

	private List<ComparisonResult> getComparisonResults(FloatingRateSpecification o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateSpecification> validate(RosettaPath path, FloatingRateSpecification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateSpecification", ValidationType.TYPE_FORMAT, "FloatingRateSpecification", path, "", error);
		}
		return success("FloatingRateSpecification", ValidationType.TYPE_FORMAT, "FloatingRateSpecification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateSpecification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateSpecification", ValidationType.TYPE_FORMAT, "FloatingRateSpecification", path, "", res.getError());
				}
				return success("FloatingRateSpecification", ValidationType.TYPE_FORMAT, "FloatingRateSpecification", path, "");
			})
			.collect(toList());
	}

}
