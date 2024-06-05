package cdm.observable.asset.validation;

import cdm.observable.asset.FxSpotRateSource;
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

public class FxSpotRateSourceTypeFormatValidator implements Validator<FxSpotRateSource> {

	private List<ComparisonResult> getComparisonResults(FxSpotRateSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxSpotRateSource> validate(RosettaPath path, FxSpotRateSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxSpotRateSource", ValidationType.TYPE_FORMAT, "FxSpotRateSource", path, "", error);
		}
		return success("FxSpotRateSource", ValidationType.TYPE_FORMAT, "FxSpotRateSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxSpotRateSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxSpotRateSource", ValidationType.TYPE_FORMAT, "FxSpotRateSource", path, "", res.getError());
				}
				return success("FxSpotRateSource", ValidationType.TYPE_FORMAT, "FxSpotRateSource", path, "");
			})
			.collect(toList());
	}

}
