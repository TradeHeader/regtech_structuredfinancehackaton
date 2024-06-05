package cdm.product.template.validation;

import cdm.product.common.schedule.AveragingPeriod;
import cdm.product.template.Asian;
import cdm.product.template.AveragingInOutEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AsianValidator implements Validator<Asian> {

	private List<ComparisonResult> getComparisonResults(Asian o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("averagingInOut", (AveragingInOutEnum) o.getAveragingInOut() != null ? 1 : 0, 1, 1), 
				checkCardinality("strikeFactor", (BigDecimal) o.getStrikeFactor() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingPeriodIn", (AveragingPeriod) o.getAveragingPeriodIn() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingPeriodOut", (AveragingPeriod) o.getAveragingPeriodOut() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Asian> validate(RosettaPath path, Asian o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Asian", ValidationType.CARDINALITY, "Asian", path, "", error);
		}
		return success("Asian", ValidationType.CARDINALITY, "Asian", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Asian o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Asian", ValidationType.CARDINALITY, "Asian", path, "", res.getError());
				}
				return success("Asian", ValidationType.CARDINALITY, "Asian", path, "");
			})
			.collect(toList());
	}

}
