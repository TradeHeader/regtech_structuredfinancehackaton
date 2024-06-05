package cdm.observable.asset.validation;

import cdm.observable.asset.FxRate;
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

public class FxRateTypeFormatValidator implements Validator<FxRate> {

	private List<ComparisonResult> getComparisonResults(FxRate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxRate> validate(RosettaPath path, FxRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxRate", ValidationType.TYPE_FORMAT, "FxRate", path, "", error);
		}
		return success("FxRate", ValidationType.TYPE_FORMAT, "FxRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxRate", ValidationType.TYPE_FORMAT, "FxRate", path, "", res.getError());
				}
				return success("FxRate", ValidationType.TYPE_FORMAT, "FxRate", path, "");
			})
			.collect(toList());
	}

}
