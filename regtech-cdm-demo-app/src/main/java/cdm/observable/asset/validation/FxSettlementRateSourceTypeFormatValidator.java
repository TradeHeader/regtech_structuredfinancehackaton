package cdm.observable.asset.validation;

import cdm.observable.asset.FxSettlementRateSource;
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

public class FxSettlementRateSourceTypeFormatValidator implements Validator<FxSettlementRateSource> {

	private List<ComparisonResult> getComparisonResults(FxSettlementRateSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxSettlementRateSource> validate(RosettaPath path, FxSettlementRateSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxSettlementRateSource", ValidationType.TYPE_FORMAT, "FxSettlementRateSource", path, "", error);
		}
		return success("FxSettlementRateSource", ValidationType.TYPE_FORMAT, "FxSettlementRateSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxSettlementRateSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxSettlementRateSource", ValidationType.TYPE_FORMAT, "FxSettlementRateSource", path, "", res.getError());
				}
				return success("FxSettlementRateSource", ValidationType.TYPE_FORMAT, "FxSettlementRateSource", path, "");
			})
			.collect(toList());
	}

}
