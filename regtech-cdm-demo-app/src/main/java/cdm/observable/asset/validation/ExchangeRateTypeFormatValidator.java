package cdm.observable.asset.validation;

import cdm.observable.asset.ExchangeRate;
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

public class ExchangeRateTypeFormatValidator implements Validator<ExchangeRate> {

	private List<ComparisonResult> getComparisonResults(ExchangeRate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExchangeRate> validate(RosettaPath path, ExchangeRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExchangeRate", ValidationType.TYPE_FORMAT, "ExchangeRate", path, "", error);
		}
		return success("ExchangeRate", ValidationType.TYPE_FORMAT, "ExchangeRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExchangeRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExchangeRate", ValidationType.TYPE_FORMAT, "ExchangeRate", path, "", res.getError());
				}
				return success("ExchangeRate", ValidationType.TYPE_FORMAT, "ExchangeRate", path, "");
			})
			.collect(toList());
	}

}
