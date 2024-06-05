package cdm.regulation.validation;

import cdm.regulation.AcctOwnr;
import cdm.regulation.Sellr;
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

public class SellrValidator implements Validator<Sellr> {

	private List<ComparisonResult> getComparisonResults(Sellr o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("acctOwnr", (AcctOwnr) o.getAcctOwnr() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Sellr> validate(RosettaPath path, Sellr o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Sellr", ValidationType.CARDINALITY, "Sellr", path, "", error);
		}
		return success("Sellr", ValidationType.CARDINALITY, "Sellr", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Sellr o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Sellr", ValidationType.CARDINALITY, "Sellr", path, "", res.getError());
				}
				return success("Sellr", ValidationType.CARDINALITY, "Sellr", path, "");
			})
			.collect(toList());
	}

}
