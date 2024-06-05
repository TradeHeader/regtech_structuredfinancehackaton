package cdm.observable.asset.validation;

import cdm.observable.asset.FxRateObservable;
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

public class FxRateObservableTypeFormatValidator implements Validator<FxRateObservable> {

	private List<ComparisonResult> getComparisonResults(FxRateObservable o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxRateObservable> validate(RosettaPath path, FxRateObservable o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxRateObservable", ValidationType.TYPE_FORMAT, "FxRateObservable", path, "", error);
		}
		return success("FxRateObservable", ValidationType.TYPE_FORMAT, "FxRateObservable", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxRateObservable o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxRateObservable", ValidationType.TYPE_FORMAT, "FxRateObservable", path, "", res.getError());
				}
				return success("FxRateObservable", ValidationType.TYPE_FORMAT, "FxRateObservable", path, "");
			})
			.collect(toList());
	}

}
