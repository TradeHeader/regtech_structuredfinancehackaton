package cdm.base.math.validation;

import cdm.base.math.AveragingCalculationMethod;
import cdm.base.math.AveragingCalculationMethodEnum;
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

public class AveragingCalculationMethodValidator implements Validator<AveragingCalculationMethod> {

	private List<ComparisonResult> getComparisonResults(AveragingCalculationMethod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("isWeighted", (Boolean) o.getIsWeighted() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationMethod", (AveragingCalculationMethodEnum) o.getCalculationMethod() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AveragingCalculationMethod> validate(RosettaPath path, AveragingCalculationMethod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingCalculationMethod", ValidationType.CARDINALITY, "AveragingCalculationMethod", path, "", error);
		}
		return success("AveragingCalculationMethod", ValidationType.CARDINALITY, "AveragingCalculationMethod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingCalculationMethod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingCalculationMethod", ValidationType.CARDINALITY, "AveragingCalculationMethod", path, "", res.getError());
				}
				return success("AveragingCalculationMethod", ValidationType.CARDINALITY, "AveragingCalculationMethod", path, "");
			})
			.collect(toList());
	}

}
