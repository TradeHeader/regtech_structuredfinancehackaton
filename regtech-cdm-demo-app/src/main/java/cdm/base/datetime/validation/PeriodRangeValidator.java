package cdm.base.datetime.validation;

import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.PeriodRange;
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

public class PeriodRangeValidator implements Validator<PeriodRange> {

	private List<ComparisonResult> getComparisonResults(PeriodRange o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("lowerBound", (PeriodBound) o.getLowerBound() != null ? 1 : 0, 0, 1), 
				checkCardinality("upperBound", (PeriodBound) o.getUpperBound() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PeriodRange> validate(RosettaPath path, PeriodRange o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PeriodRange", ValidationType.CARDINALITY, "PeriodRange", path, "", error);
		}
		return success("PeriodRange", ValidationType.CARDINALITY, "PeriodRange", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PeriodRange o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PeriodRange", ValidationType.CARDINALITY, "PeriodRange", path, "", res.getError());
				}
				return success("PeriodRange", ValidationType.CARDINALITY, "PeriodRange", path, "");
			})
			.collect(toList());
	}

}
