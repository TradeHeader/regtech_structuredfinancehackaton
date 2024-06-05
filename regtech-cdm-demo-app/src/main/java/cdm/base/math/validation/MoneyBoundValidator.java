package cdm.base.math.validation;

import cdm.base.math.MoneyBound;
import cdm.observable.asset.Money;
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

public class MoneyBoundValidator implements Validator<MoneyBound> {

	private List<ComparisonResult> getComparisonResults(MoneyBound o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("money", (Money) o.getMoney() != null ? 1 : 0, 1, 1), 
				checkCardinality("inclusive", (Boolean) o.getInclusive() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<MoneyBound> validate(RosettaPath path, MoneyBound o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MoneyBound", ValidationType.CARDINALITY, "MoneyBound", path, "", error);
		}
		return success("MoneyBound", ValidationType.CARDINALITY, "MoneyBound", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MoneyBound o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MoneyBound", ValidationType.CARDINALITY, "MoneyBound", path, "", res.getError());
				}
				return success("MoneyBound", ValidationType.CARDINALITY, "MoneyBound", path, "");
			})
			.collect(toList());
	}

}
