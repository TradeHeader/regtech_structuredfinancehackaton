package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.CalculationPeriodBase;
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

public class CalculationPeriodBaseTypeFormatValidator implements Validator<CalculationPeriodBase> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriodBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CalculationPeriodBase> validate(RosettaPath path, CalculationPeriodBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriodBase", ValidationType.TYPE_FORMAT, "CalculationPeriodBase", path, "", error);
		}
		return success("CalculationPeriodBase", ValidationType.TYPE_FORMAT, "CalculationPeriodBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriodBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriodBase", ValidationType.TYPE_FORMAT, "CalculationPeriodBase", path, "", res.getError());
				}
				return success("CalculationPeriodBase", ValidationType.TYPE_FORMAT, "CalculationPeriodBase", path, "");
			})
			.collect(toList());
	}

}
