package cdm.observable.asset.validation;

import cdm.base.datetime.AdjustableDate;
import cdm.observable.asset.FxRateSourceFixing;
import cdm.observable.asset.FxSettlementRateSource;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FxRateSourceFixingValidator implements Validator<FxRateSourceFixing> {

	private List<ComparisonResult> getComparisonResults(FxRateSourceFixing o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("settlementRateSource", (FxSettlementRateSource) o.getSettlementRateSource() != null ? 1 : 0, 1, 1), 
				checkCardinality("fixingDate", (AdjustableDate) o.getFixingDate() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FxRateSourceFixing> validate(RosettaPath path, FxRateSourceFixing o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxRateSourceFixing", ValidationType.CARDINALITY, "FxRateSourceFixing", path, "", error);
		}
		return success("FxRateSourceFixing", ValidationType.CARDINALITY, "FxRateSourceFixing", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxRateSourceFixing o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxRateSourceFixing", ValidationType.CARDINALITY, "FxRateSourceFixing", path, "", res.getError());
				}
				return success("FxRateSourceFixing", ValidationType.CARDINALITY, "FxRateSourceFixing", path, "");
			})
			.collect(toList());
	}

}
