package cdm.product.template.validation;

import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.product.template.PartialExercise;
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

public class PartialExerciseValidator implements Validator<PartialExercise> {

	private List<ComparisonResult> getComparisonResults(PartialExercise o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("notionaReference", (ReferenceWithMetaMoney) o.getNotionaReference() != null ? 1 : 0, 1, 1), 
				checkCardinality("integralMultipleAmount", (BigDecimal) o.getIntegralMultipleAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("minimumNotionalAmount", (BigDecimal) o.getMinimumNotionalAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("minimumNumberOfOptions", (Integer) o.getMinimumNumberOfOptions() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PartialExercise> validate(RosettaPath path, PartialExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartialExercise", ValidationType.CARDINALITY, "PartialExercise", path, "", error);
		}
		return success("PartialExercise", ValidationType.CARDINALITY, "PartialExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartialExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartialExercise", ValidationType.CARDINALITY, "PartialExercise", path, "", res.getError());
				}
				return success("PartialExercise", ValidationType.CARDINALITY, "PartialExercise", path, "");
			})
			.collect(toList());
	}

}
