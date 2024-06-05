package cdm.observable.asset.validation;

import cdm.observable.asset.RelativePrice;
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

public class RelativePriceTypeFormatValidator implements Validator<RelativePrice> {

	private List<ComparisonResult> getComparisonResults(RelativePrice o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<RelativePrice> validate(RosettaPath path, RelativePrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RelativePrice", ValidationType.TYPE_FORMAT, "RelativePrice", path, "", error);
		}
		return success("RelativePrice", ValidationType.TYPE_FORMAT, "RelativePrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RelativePrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RelativePrice", ValidationType.TYPE_FORMAT, "RelativePrice", path, "", res.getError());
				}
				return success("RelativePrice", ValidationType.TYPE_FORMAT, "RelativePrice", path, "");
			})
			.collect(toList());
	}

}
