package cdm.regulation.validation;

import cdm.regulation.Nm;
import cdm.regulation.RefRate;
import cdm.regulation.Term;
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

public class NmValidator implements Validator<Nm> {

	private List<ComparisonResult> getComparisonResults(Nm o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("refRate", (RefRate) o.getRefRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("term", (Term) o.getTerm() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Nm> validate(RosettaPath path, Nm o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Nm", ValidationType.CARDINALITY, "Nm", path, "", error);
		}
		return success("Nm", ValidationType.CARDINALITY, "Nm", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Nm o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Nm", ValidationType.CARDINALITY, "Nm", path, "", res.getError());
				}
				return success("Nm", ValidationType.CARDINALITY, "Nm", path, "");
			})
			.collect(toList());
	}

}
