package cdm.observable.asset.validation;

import cdm.observable.asset.SwapCurveValuation;
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

public class SwapCurveValuationTypeFormatValidator implements Validator<SwapCurveValuation> {

	private List<ComparisonResult> getComparisonResults(SwapCurveValuation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SwapCurveValuation> validate(RosettaPath path, SwapCurveValuation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SwapCurveValuation", ValidationType.TYPE_FORMAT, "SwapCurveValuation", path, "", error);
		}
		return success("SwapCurveValuation", ValidationType.TYPE_FORMAT, "SwapCurveValuation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SwapCurveValuation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SwapCurveValuation", ValidationType.TYPE_FORMAT, "SwapCurveValuation", path, "", res.getError());
				}
				return success("SwapCurveValuation", ValidationType.TYPE_FORMAT, "SwapCurveValuation", path, "");
			})
			.collect(toList());
	}

}
