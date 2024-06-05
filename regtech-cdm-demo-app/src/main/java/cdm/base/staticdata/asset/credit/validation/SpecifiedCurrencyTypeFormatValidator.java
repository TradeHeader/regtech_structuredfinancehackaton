package cdm.base.staticdata.asset.credit.validation;

import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
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

public class SpecifiedCurrencyTypeFormatValidator implements Validator<SpecifiedCurrency> {

	private List<ComparisonResult> getComparisonResults(SpecifiedCurrency o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SpecifiedCurrency> validate(RosettaPath path, SpecifiedCurrency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SpecifiedCurrency", ValidationType.TYPE_FORMAT, "SpecifiedCurrency", path, "", error);
		}
		return success("SpecifiedCurrency", ValidationType.TYPE_FORMAT, "SpecifiedCurrency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SpecifiedCurrency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SpecifiedCurrency", ValidationType.TYPE_FORMAT, "SpecifiedCurrency", path, "", res.getError());
				}
				return success("SpecifiedCurrency", ValidationType.TYPE_FORMAT, "SpecifiedCurrency", path, "");
			})
			.collect(toList());
	}

}
