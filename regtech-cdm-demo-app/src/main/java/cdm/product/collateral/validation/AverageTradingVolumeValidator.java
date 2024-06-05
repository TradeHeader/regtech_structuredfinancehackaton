package cdm.product.collateral.validation;

import cdm.base.datetime.Period;
import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.AverageTradingVolumeMethodologyEnum;
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

public class AverageTradingVolumeValidator implements Validator<AverageTradingVolume> {

	private List<ComparisonResult> getComparisonResults(AverageTradingVolume o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("period", (Period) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("methodology", (AverageTradingVolumeMethodologyEnum) o.getMethodology() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AverageTradingVolume> validate(RosettaPath path, AverageTradingVolume o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AverageTradingVolume", ValidationType.CARDINALITY, "AverageTradingVolume", path, "", error);
		}
		return success("AverageTradingVolume", ValidationType.CARDINALITY, "AverageTradingVolume", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AverageTradingVolume o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AverageTradingVolume", ValidationType.CARDINALITY, "AverageTradingVolume", path, "", res.getError());
				}
				return success("AverageTradingVolume", ValidationType.CARDINALITY, "AverageTradingVolume", path, "");
			})
			.collect(toList());
	}

}
