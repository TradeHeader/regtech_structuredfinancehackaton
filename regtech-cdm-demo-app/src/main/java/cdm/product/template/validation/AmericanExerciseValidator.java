package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.product.template.AmericanExercise;
import cdm.product.template.ExerciseFeeSchedule;
import cdm.product.template.ExpirationTimeTypeEnum;
import cdm.product.template.MultipleExercise;
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

public class AmericanExerciseValidator implements Validator<AmericanExercise> {

	private List<ComparisonResult> getComparisonResults(AmericanExercise o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("commencementDate", (AdjustableOrRelativeDate) o.getCommencementDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("expirationDate", (AdjustableOrRelativeDate) o.getExpirationDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("relevantUnderlyingDate", (AdjustableOrRelativeDates) o.getRelevantUnderlyingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("earliestExerciseTime", (BusinessCenterTime) o.getEarliestExerciseTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("latestExerciseTime", (BusinessCenterTime) o.getLatestExerciseTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("expirationTime", (BusinessCenterTime) o.getExpirationTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("expirationTimeType", (ExpirationTimeTypeEnum) o.getExpirationTimeType() != null ? 1 : 0, 0, 1), 
				checkCardinality("multipleExercise", (MultipleExercise) o.getMultipleExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseFeeSchedule", (ExerciseFeeSchedule) o.getExerciseFeeSchedule() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AmericanExercise> validate(RosettaPath path, AmericanExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AmericanExercise", ValidationType.CARDINALITY, "AmericanExercise", path, "", error);
		}
		return success("AmericanExercise", ValidationType.CARDINALITY, "AmericanExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AmericanExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AmericanExercise", ValidationType.CARDINALITY, "AmericanExercise", path, "", res.getError());
				}
				return success("AmericanExercise", ValidationType.CARDINALITY, "AmericanExercise", path, "");
			})
			.collect(toList());
	}

}
