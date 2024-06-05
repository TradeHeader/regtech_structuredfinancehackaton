package cdm.base.datetime.validation;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodBound;
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

public class PeriodBoundValidator implements Validator<PeriodBound> {

	private List<ComparisonResult> getComparisonResults(PeriodBound o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("period", (Period) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("inclusive", (Boolean) o.getInclusive() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<PeriodBound> validate(RosettaPath path, PeriodBound o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PeriodBound", ValidationType.CARDINALITY, "PeriodBound", path, "", error);
		}
		return success("PeriodBound", ValidationType.CARDINALITY, "PeriodBound", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PeriodBound o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PeriodBound", ValidationType.CARDINALITY, "PeriodBound", path, "", res.getError());
				}
				return success("PeriodBound", ValidationType.CARDINALITY, "PeriodBound", path, "");
			})
			.collect(toList());
	}

}
