package cdm.regulation.validation;

import cdm.regulation.Othr;
import cdm.regulation.Prsn;
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

public class PrsnValidator implements Validator<Prsn> {

	private List<ComparisonResult> getComparisonResults(Prsn o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("ctryOfBrnch", (String) o.getCtryOfBrnch() != null ? 1 : 0, 1, 1), 
				checkCardinality("othr", (Othr) o.getOthr() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Prsn> validate(RosettaPath path, Prsn o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Prsn", ValidationType.CARDINALITY, "Prsn", path, "", error);
		}
		return success("Prsn", ValidationType.CARDINALITY, "Prsn", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Prsn o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Prsn", ValidationType.CARDINALITY, "Prsn", path, "", res.getError());
				}
				return success("Prsn", ValidationType.CARDINALITY, "Prsn", path, "");
			})
			.collect(toList());
	}

}
