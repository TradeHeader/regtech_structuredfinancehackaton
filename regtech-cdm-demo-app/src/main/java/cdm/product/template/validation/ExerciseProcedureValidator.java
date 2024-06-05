package cdm.product.template.validation;

import cdm.product.template.AutomaticExercise;
import cdm.product.template.ExerciseProcedure;
import cdm.product.template.ManualExercise;
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

public class ExerciseProcedureValidator implements Validator<ExerciseProcedure> {

	private List<ComparisonResult> getComparisonResults(ExerciseProcedure o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("manualExercise", (ManualExercise) o.getManualExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("automaticExercise", (AutomaticExercise) o.getAutomaticExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("followUpConfirmation", (Boolean) o.getFollowUpConfirmation() != null ? 1 : 0, 1, 1), 
				checkCardinality("limitedRightToConfirm", (Boolean) o.getLimitedRightToConfirm() != null ? 1 : 0, 0, 1), 
				checkCardinality("splitTicket", (Boolean) o.getSplitTicket() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ExerciseProcedure> validate(RosettaPath path, ExerciseProcedure o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExerciseProcedure", ValidationType.CARDINALITY, "ExerciseProcedure", path, "", error);
		}
		return success("ExerciseProcedure", ValidationType.CARDINALITY, "ExerciseProcedure", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExerciseProcedure o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExerciseProcedure", ValidationType.CARDINALITY, "ExerciseProcedure", path, "", res.getError());
				}
				return success("ExerciseProcedure", ValidationType.CARDINALITY, "ExerciseProcedure", path, "");
			})
			.collect(toList());
	}

}
