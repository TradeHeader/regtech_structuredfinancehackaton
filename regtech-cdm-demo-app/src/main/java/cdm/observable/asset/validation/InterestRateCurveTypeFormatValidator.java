package cdm.observable.asset.validation;

import cdm.observable.asset.InterestRateCurve;
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

public class InterestRateCurveTypeFormatValidator implements Validator<InterestRateCurve> {

	private List<ComparisonResult> getComparisonResults(InterestRateCurve o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InterestRateCurve> validate(RosettaPath path, InterestRateCurve o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InterestRateCurve", ValidationType.TYPE_FORMAT, "InterestRateCurve", path, "", error);
		}
		return success("InterestRateCurve", ValidationType.TYPE_FORMAT, "InterestRateCurve", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InterestRateCurve o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InterestRateCurve", ValidationType.TYPE_FORMAT, "InterestRateCurve", path, "", res.getError());
				}
				return success("InterestRateCurve", ValidationType.TYPE_FORMAT, "InterestRateCurve", path, "");
			})
			.collect(toList());
	}

}
