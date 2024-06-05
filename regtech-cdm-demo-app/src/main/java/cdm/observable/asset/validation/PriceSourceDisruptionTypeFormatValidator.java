package cdm.observable.asset.validation;

import cdm.observable.asset.PriceSourceDisruption;
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

public class PriceSourceDisruptionTypeFormatValidator implements Validator<PriceSourceDisruption> {

	private List<ComparisonResult> getComparisonResults(PriceSourceDisruption o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PriceSourceDisruption> validate(RosettaPath path, PriceSourceDisruption o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceSourceDisruption", ValidationType.TYPE_FORMAT, "PriceSourceDisruption", path, "", error);
		}
		return success("PriceSourceDisruption", ValidationType.TYPE_FORMAT, "PriceSourceDisruption", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceSourceDisruption o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceSourceDisruption", ValidationType.TYPE_FORMAT, "PriceSourceDisruption", path, "", res.getError());
				}
				return success("PriceSourceDisruption", ValidationType.TYPE_FORMAT, "PriceSourceDisruption", path, "");
			})
			.collect(toList());
	}

}
