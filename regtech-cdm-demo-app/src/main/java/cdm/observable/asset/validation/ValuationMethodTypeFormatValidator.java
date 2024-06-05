package cdm.observable.asset.validation;

import cdm.observable.asset.ValuationMethod;
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

public class ValuationMethodTypeFormatValidator implements Validator<ValuationMethod> {

	private List<ComparisonResult> getComparisonResults(ValuationMethod o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ValuationMethod> validate(RosettaPath path, ValuationMethod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationMethod", ValidationType.TYPE_FORMAT, "ValuationMethod", path, "", error);
		}
		return success("ValuationMethod", ValidationType.TYPE_FORMAT, "ValuationMethod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationMethod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationMethod", ValidationType.TYPE_FORMAT, "ValuationMethod", path, "", res.getError());
				}
				return success("ValuationMethod", ValidationType.TYPE_FORMAT, "ValuationMethod", path, "");
			})
			.collect(toList());
	}

}
