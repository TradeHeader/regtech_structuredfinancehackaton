package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseFee;
import cdm.product.template.ExpirationTimeTypeEnum;
import cdm.product.template.PartialExercise;
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

public class EuropeanExerciseValidator implements Validator<EuropeanExercise> {

	private List<ComparisonResult> getComparisonResults(EuropeanExercise o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("expirationDate", (List<? extends AdjustableOrRelativeDate>) o.getExpirationDate() == null ? 0 : ((List<? extends AdjustableOrRelativeDate>) o.getExpirationDate()).size(), 1, 0), 
				checkCardinality("relevantUnderlyingDate", (AdjustableOrRelativeDates) o.getRelevantUnderlyingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("earliestExerciseTime", (BusinessCenterTime) o.getEarliestExerciseTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("expirationTime", (BusinessCenterTime) o.getExpirationTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("expirationTimeType", (ExpirationTimeTypeEnum) o.getExpirationTimeType() != null ? 1 : 0, 0, 1), 
				checkCardinality("partialExercise", (PartialExercise) o.getPartialExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseFee", (ExerciseFee) o.getExerciseFee() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EuropeanExercise> validate(RosettaPath path, EuropeanExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EuropeanExercise", ValidationType.CARDINALITY, "EuropeanExercise", path, "", error);
		}
		return success("EuropeanExercise", ValidationType.CARDINALITY, "EuropeanExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EuropeanExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EuropeanExercise", ValidationType.CARDINALITY, "EuropeanExercise", path, "", res.getError());
				}
				return success("EuropeanExercise", ValidationType.CARDINALITY, "EuropeanExercise", path, "");
			})
			.collect(toList());
	}

}
