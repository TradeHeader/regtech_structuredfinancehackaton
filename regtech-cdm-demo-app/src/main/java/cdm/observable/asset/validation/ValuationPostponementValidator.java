package cdm.observable.asset.validation;

import cdm.observable.asset.ValuationPostponement;
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

public class ValuationPostponementValidator implements Validator<ValuationPostponement> {

	private List<ComparisonResult> getComparisonResults(ValuationPostponement o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("maximumDaysOfPostponement", (Integer) o.getMaximumDaysOfPostponement() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ValuationPostponement> validate(RosettaPath path, ValuationPostponement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationPostponement", ValidationType.CARDINALITY, "ValuationPostponement", path, "", error);
		}
		return success("ValuationPostponement", ValidationType.CARDINALITY, "ValuationPostponement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationPostponement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationPostponement", ValidationType.CARDINALITY, "ValuationPostponement", path, "", res.getError());
				}
				return success("ValuationPostponement", ValidationType.CARDINALITY, "ValuationPostponement", path, "");
			})
			.collect(toList());
	}

}
