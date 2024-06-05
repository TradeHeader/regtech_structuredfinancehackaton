package cdm.observable.asset.validation;

import cdm.observable.asset.MultipleDebtTypes;
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

public class MultipleDebtTypesTypeFormatValidator implements Validator<MultipleDebtTypes> {

	private List<ComparisonResult> getComparisonResults(MultipleDebtTypes o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MultipleDebtTypes> validate(RosettaPath path, MultipleDebtTypes o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MultipleDebtTypes", ValidationType.TYPE_FORMAT, "MultipleDebtTypes", path, "", error);
		}
		return success("MultipleDebtTypes", ValidationType.TYPE_FORMAT, "MultipleDebtTypes", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MultipleDebtTypes o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MultipleDebtTypes", ValidationType.TYPE_FORMAT, "MultipleDebtTypes", path, "", res.getError());
				}
				return success("MultipleDebtTypes", ValidationType.TYPE_FORMAT, "MultipleDebtTypes", path, "");
			})
			.collect(toList());
	}

}
