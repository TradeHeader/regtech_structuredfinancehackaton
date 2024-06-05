package cdm.product.template.validation;

import cdm.product.template.FixedPricePayout;
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

public class FixedPricePayoutTypeFormatValidator implements Validator<FixedPricePayout> {

	private List<ComparisonResult> getComparisonResults(FixedPricePayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FixedPricePayout> validate(RosettaPath path, FixedPricePayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FixedPricePayout", ValidationType.TYPE_FORMAT, "FixedPricePayout", path, "", error);
		}
		return success("FixedPricePayout", ValidationType.TYPE_FORMAT, "FixedPricePayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FixedPricePayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FixedPricePayout", ValidationType.TYPE_FORMAT, "FixedPricePayout", path, "", res.getError());
				}
				return success("FixedPricePayout", ValidationType.TYPE_FORMAT, "FixedPricePayout", path, "");
			})
			.collect(toList());
	}

}
