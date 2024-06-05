package cdm.product.asset.validation;

import cdm.product.asset.InflationRateSpecification;
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

public class InflationRateSpecificationTypeFormatValidator implements Validator<InflationRateSpecification> {

	private List<ComparisonResult> getComparisonResults(InflationRateSpecification o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InflationRateSpecification> validate(RosettaPath path, InflationRateSpecification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InflationRateSpecification", ValidationType.TYPE_FORMAT, "InflationRateSpecification", path, "", error);
		}
		return success("InflationRateSpecification", ValidationType.TYPE_FORMAT, "InflationRateSpecification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InflationRateSpecification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InflationRateSpecification", ValidationType.TYPE_FORMAT, "InflationRateSpecification", path, "", res.getError());
				}
				return success("InflationRateSpecification", ValidationType.TYPE_FORMAT, "InflationRateSpecification", path, "");
			})
			.collect(toList());
	}

}
