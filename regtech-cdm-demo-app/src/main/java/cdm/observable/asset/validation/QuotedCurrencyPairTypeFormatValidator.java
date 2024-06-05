package cdm.observable.asset.validation;

import cdm.observable.asset.QuotedCurrencyPair;
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

public class QuotedCurrencyPairTypeFormatValidator implements Validator<QuotedCurrencyPair> {

	private List<ComparisonResult> getComparisonResults(QuotedCurrencyPair o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<QuotedCurrencyPair> validate(RosettaPath path, QuotedCurrencyPair o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("QuotedCurrencyPair", ValidationType.TYPE_FORMAT, "QuotedCurrencyPair", path, "", error);
		}
		return success("QuotedCurrencyPair", ValidationType.TYPE_FORMAT, "QuotedCurrencyPair", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, QuotedCurrencyPair o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("QuotedCurrencyPair", ValidationType.TYPE_FORMAT, "QuotedCurrencyPair", path, "", res.getError());
				}
				return success("QuotedCurrencyPair", ValidationType.TYPE_FORMAT, "QuotedCurrencyPair", path, "");
			})
			.collect(toList());
	}

}
