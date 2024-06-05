package cdm.regulation.validation;

import cdm.regulation.AcctOwnr;
import cdm.regulation.Buyr;
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

public class BuyrValidator implements Validator<Buyr> {

	private List<ComparisonResult> getComparisonResults(Buyr o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("acctOwnr", (AcctOwnr) o.getAcctOwnr() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Buyr> validate(RosettaPath path, Buyr o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Buyr", ValidationType.CARDINALITY, "Buyr", path, "", error);
		}
		return success("Buyr", ValidationType.CARDINALITY, "Buyr", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Buyr o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Buyr", ValidationType.CARDINALITY, "Buyr", path, "", res.getError());
				}
				return success("Buyr", ValidationType.CARDINALITY, "Buyr", path, "");
			})
			.collect(toList());
	}

}
