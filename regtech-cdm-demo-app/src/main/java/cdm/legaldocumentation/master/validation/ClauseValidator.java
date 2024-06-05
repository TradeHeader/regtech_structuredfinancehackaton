package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.Clause;
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

public class ClauseValidator implements Validator<Clause> {

	private List<ComparisonResult> getComparisonResults(Clause o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (String) o.getIdentifier() != null ? 1 : 0, 0, 1), 
				checkCardinality("terms", (String) o.getTerms() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Clause> validate(RosettaPath path, Clause o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Clause", ValidationType.CARDINALITY, "Clause", path, "", error);
		}
		return success("Clause", ValidationType.CARDINALITY, "Clause", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Clause o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Clause", ValidationType.CARDINALITY, "Clause", path, "", res.getError());
				}
				return success("Clause", ValidationType.CARDINALITY, "Clause", path, "");
			})
			.collect(toList());
	}

}
