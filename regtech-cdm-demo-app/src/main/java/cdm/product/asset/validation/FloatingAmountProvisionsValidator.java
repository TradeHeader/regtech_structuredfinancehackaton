package cdm.product.asset.validation;

import cdm.product.asset.FloatingAmountProvisions;
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

public class FloatingAmountProvisionsValidator implements Validator<FloatingAmountProvisions> {

	private List<ComparisonResult> getComparisonResults(FloatingAmountProvisions o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("wacCapInterestProvision", (Boolean) o.getWacCapInterestProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("stepUpProvision", (Boolean) o.getStepUpProvision() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingAmountProvisions> validate(RosettaPath path, FloatingAmountProvisions o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingAmountProvisions", ValidationType.CARDINALITY, "FloatingAmountProvisions", path, "", error);
		}
		return success("FloatingAmountProvisions", ValidationType.CARDINALITY, "FloatingAmountProvisions", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingAmountProvisions o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingAmountProvisions", ValidationType.CARDINALITY, "FloatingAmountProvisions", path, "", res.getError());
				}
				return success("FloatingAmountProvisions", ValidationType.CARDINALITY, "FloatingAmountProvisions", path, "");
			})
			.collect(toList());
	}

}
