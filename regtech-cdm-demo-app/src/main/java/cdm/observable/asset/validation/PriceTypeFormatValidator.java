package cdm.observable.asset.validation;

import cdm.observable.asset.Price;
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

public class PriceTypeFormatValidator implements Validator<Price> {

	private List<ComparisonResult> getComparisonResults(Price o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Price> validate(RosettaPath path, Price o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Price", ValidationType.TYPE_FORMAT, "Price", path, "", error);
		}
		return success("Price", ValidationType.TYPE_FORMAT, "Price", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Price o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Price", ValidationType.TYPE_FORMAT, "Price", path, "", res.getError());
				}
				return success("Price", ValidationType.TYPE_FORMAT, "Price", path, "");
			})
			.collect(toList());
	}

}
