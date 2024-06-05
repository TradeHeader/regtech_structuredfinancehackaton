package cdm.base.math.validation;

import cdm.base.math.Rounding;
import cdm.base.math.RoundingDirectionEnum;
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

public class RoundingValidator implements Validator<Rounding> {

	private List<ComparisonResult> getComparisonResults(Rounding o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("roundingDirection", (RoundingDirectionEnum) o.getRoundingDirection() != null ? 1 : 0, 1, 1), 
				checkCardinality("precision", (Integer) o.getPrecision() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Rounding> validate(RosettaPath path, Rounding o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Rounding", ValidationType.CARDINALITY, "Rounding", path, "", error);
		}
		return success("Rounding", ValidationType.CARDINALITY, "Rounding", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Rounding o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Rounding", ValidationType.CARDINALITY, "Rounding", path, "", res.getError());
				}
				return success("Rounding", ValidationType.CARDINALITY, "Rounding", path, "");
			})
			.collect(toList());
	}

}
