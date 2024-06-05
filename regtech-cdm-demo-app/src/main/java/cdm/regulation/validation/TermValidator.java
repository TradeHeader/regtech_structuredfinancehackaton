package cdm.regulation.validation;

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

public class TermValidator implements Validator<Term> {

	private List<ComparisonResult> getComparisonResults(Term o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("unit", (String) o.getUnit() != null ? 1 : 0, 1, 1), 
				checkCardinality("val", (String) o.getVal() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Term> validate(RosettaPath path, Term o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Term", ValidationType.CARDINALITY, "Term", path, "", error);
		}
		return success("Term", ValidationType.CARDINALITY, "Term", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Term o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Term", ValidationType.CARDINALITY, "Term", path, "", res.getError());
				}
				return success("Term", ValidationType.CARDINALITY, "Term", path, "");
			})
			.collect(toList());
	}

}
