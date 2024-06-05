package cdm.regulation.validation;

import cdm.regulation.Pric;
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

public class PricValidator implements Validator<Pric> {

	private List<ComparisonResult> getComparisonResults(Pric o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("pric", (Pric) o.getPric() != null ? 1 : 0, 1, 1), 
				checkCardinality("bsisPts", (String) o.getBsisPts() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Pric> validate(RosettaPath path, Pric o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Pric", ValidationType.CARDINALITY, "Pric", path, "", error);
		}
		return success("Pric", ValidationType.CARDINALITY, "Pric", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Pric o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Pric", ValidationType.CARDINALITY, "Pric", path, "", res.getError());
				}
				return success("Pric", ValidationType.CARDINALITY, "Pric", path, "");
			})
			.collect(toList());
	}

}
