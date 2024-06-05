package cdm.event.common.validation;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.BusinessCenterTime;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
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

public class ExerciseInstructionValidator implements Validator<ExerciseInstruction> {

	private List<ComparisonResult> getComparisonResults(ExerciseInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("exerciseQuantity", (PrimitiveInstruction) o.getExerciseQuantity() != null ? 1 : 0, 1, 1), 
				checkCardinality("exerciseOption", (ReferenceWithMetaOptionPayout) o.getExerciseOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseDate", (AdjustableOrAdjustedDate) o.getExerciseDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseTime", (BusinessCenterTime) o.getExerciseTime() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ExerciseInstruction> validate(RosettaPath path, ExerciseInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExerciseInstruction", ValidationType.CARDINALITY, "ExerciseInstruction", path, "", error);
		}
		return success("ExerciseInstruction", ValidationType.CARDINALITY, "ExerciseInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExerciseInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExerciseInstruction", ValidationType.CARDINALITY, "ExerciseInstruction", path, "", res.getError());
				}
				return success("ExerciseInstruction", ValidationType.CARDINALITY, "ExerciseInstruction", path, "");
			})
			.collect(toList());
	}

}
