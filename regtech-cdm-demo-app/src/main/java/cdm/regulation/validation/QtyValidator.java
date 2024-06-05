package cdm.regulation.validation;

import cdm.regulation.Qty;
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

public class QtyValidator implements Validator<Qty> {

	private List<ComparisonResult> getComparisonResults(Qty o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("unit", (String) o.getUnit() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Qty> validate(RosettaPath path, Qty o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Qty", ValidationType.CARDINALITY, "Qty", path, "", error);
		}
		return success("Qty", ValidationType.CARDINALITY, "Qty", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Qty o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Qty", ValidationType.CARDINALITY, "Qty", path, "", res.getError());
				}
				return success("Qty", ValidationType.CARDINALITY, "Qty", path, "");
			})
			.collect(toList());
	}

}
