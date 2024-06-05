package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.DebtClassEnum;
import cdm.base.staticdata.asset.common.DebtType;
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

public class DebtTypeValidator implements Validator<DebtType> {

	private List<ComparisonResult> getComparisonResults(DebtType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("debtClass", (DebtClassEnum) o.getDebtClass() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DebtType> validate(RosettaPath path, DebtType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DebtType", ValidationType.CARDINALITY, "DebtType", path, "", error);
		}
		return success("DebtType", ValidationType.CARDINALITY, "DebtType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DebtType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DebtType", ValidationType.CARDINALITY, "DebtType", path, "", res.getError());
				}
				return success("DebtType", ValidationType.CARDINALITY, "DebtType", path, "");
			})
			.collect(toList());
	}

}
