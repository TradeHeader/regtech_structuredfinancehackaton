package cdm.product.asset.validation;

import cdm.product.asset.CashflowRepresentation;
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

public class CashflowRepresentationValidator implements Validator<CashflowRepresentation> {

	private List<ComparisonResult> getComparisonResults(CashflowRepresentation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cashflowsMatchParameters", (Boolean) o.getCashflowsMatchParameters() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CashflowRepresentation> validate(RosettaPath path, CashflowRepresentation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CashflowRepresentation", ValidationType.CARDINALITY, "CashflowRepresentation", path, "", error);
		}
		return success("CashflowRepresentation", ValidationType.CARDINALITY, "CashflowRepresentation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CashflowRepresentation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CashflowRepresentation", ValidationType.CARDINALITY, "CashflowRepresentation", path, "", res.getError());
				}
				return success("CashflowRepresentation", ValidationType.CARDINALITY, "CashflowRepresentation", path, "");
			})
			.collect(toList());
	}

}
