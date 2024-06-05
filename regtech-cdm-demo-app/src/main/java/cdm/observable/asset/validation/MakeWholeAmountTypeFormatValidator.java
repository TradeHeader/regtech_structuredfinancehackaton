package cdm.observable.asset.validation;

import cdm.observable.asset.MakeWholeAmount;
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

public class MakeWholeAmountTypeFormatValidator implements Validator<MakeWholeAmount> {

	private List<ComparisonResult> getComparisonResults(MakeWholeAmount o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MakeWholeAmount> validate(RosettaPath path, MakeWholeAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MakeWholeAmount", ValidationType.TYPE_FORMAT, "MakeWholeAmount", path, "", error);
		}
		return success("MakeWholeAmount", ValidationType.TYPE_FORMAT, "MakeWholeAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MakeWholeAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MakeWholeAmount", ValidationType.TYPE_FORMAT, "MakeWholeAmount", path, "", res.getError());
				}
				return success("MakeWholeAmount", ValidationType.TYPE_FORMAT, "MakeWholeAmount", path, "");
			})
			.collect(toList());
	}

}
