package cdm.regulation.validation;

import cdm.regulation.Id;
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

public class IdValidator implements Validator<Id> {

	private List<ComparisonResult> getComparisonResults(Id o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("lei", (String) o.getLei() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Id> validate(RosettaPath path, Id o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Id", ValidationType.CARDINALITY, "Id", path, "", error);
		}
		return success("Id", ValidationType.CARDINALITY, "Id", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Id o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Id", ValidationType.CARDINALITY, "Id", path, "", res.getError());
				}
				return success("Id", ValidationType.CARDINALITY, "Id", path, "");
			})
			.collect(toList());
	}

}
