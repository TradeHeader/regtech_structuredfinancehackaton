package cdm.regulation.validation;

import cdm.regulation.RefRate;
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

public class RefRateValidator implements Validator<RefRate> {

	private List<ComparisonResult> getComparisonResults(RefRate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("indx", (String) o.getIndx() != null ? 1 : 0, 1, 1), 
				checkCardinality("nm", (String) o.getNm() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<RefRate> validate(RosettaPath path, RefRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RefRate", ValidationType.CARDINALITY, "RefRate", path, "", error);
		}
		return success("RefRate", ValidationType.CARDINALITY, "RefRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RefRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RefRate", ValidationType.CARDINALITY, "RefRate", path, "", res.getError());
				}
				return success("RefRate", ValidationType.CARDINALITY, "RefRate", path, "");
			})
			.collect(toList());
	}

}
