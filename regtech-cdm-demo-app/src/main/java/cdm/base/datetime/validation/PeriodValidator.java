package cdm.base.datetime.validation;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodEnum;
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

public class PeriodValidator implements Validator<Period> {

	private List<ComparisonResult> getComparisonResults(Period o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("periodMultiplier", (Integer) o.getPeriodMultiplier() != null ? 1 : 0, 1, 1), 
				checkCardinality("period", (PeriodEnum) o.getPeriod() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Period> validate(RosettaPath path, Period o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Period", ValidationType.CARDINALITY, "Period", path, "", error);
		}
		return success("Period", ValidationType.CARDINALITY, "Period", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Period o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Period", ValidationType.CARDINALITY, "Period", path, "", res.getError());
				}
				return success("Period", ValidationType.CARDINALITY, "Period", path, "");
			})
			.collect(toList());
	}

}
