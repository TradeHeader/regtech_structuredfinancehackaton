package cdm.product.template.validation;

import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.OptionStyle;
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

public class OptionStyleValidator implements Validator<OptionStyle> {

	private List<ComparisonResult> getComparisonResults(OptionStyle o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("americanExercise", (AmericanExercise) o.getAmericanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("bermudaExercise", (BermudaExercise) o.getBermudaExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("europeanExercise", (EuropeanExercise) o.getEuropeanExercise() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionStyle> validate(RosettaPath path, OptionStyle o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionStyle", ValidationType.CARDINALITY, "OptionStyle", path, "", error);
		}
		return success("OptionStyle", ValidationType.CARDINALITY, "OptionStyle", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionStyle o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionStyle", ValidationType.CARDINALITY, "OptionStyle", path, "", res.getError());
				}
				return success("OptionStyle", ValidationType.CARDINALITY, "OptionStyle", path, "");
			})
			.collect(toList());
	}

}
