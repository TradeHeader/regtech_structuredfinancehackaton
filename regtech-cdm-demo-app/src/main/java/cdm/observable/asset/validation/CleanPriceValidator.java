package cdm.observable.asset.validation;

import cdm.observable.asset.CleanPrice;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CleanPriceValidator implements Validator<CleanPrice> {

	private List<ComparisonResult> getComparisonResults(CleanPrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cleanPrice", (BigDecimal) o.getCleanPrice() != null ? 1 : 0, 1, 1), 
				checkCardinality("accruals", (BigDecimal) o.getAccruals() != null ? 1 : 0, 0, 1), 
				checkCardinality("dirtyPrice", (String) o.getDirtyPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CleanPrice> validate(RosettaPath path, CleanPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CleanPrice", ValidationType.CARDINALITY, "CleanPrice", path, "", error);
		}
		return success("CleanPrice", ValidationType.CARDINALITY, "CleanPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CleanPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CleanPrice", ValidationType.CARDINALITY, "CleanPrice", path, "", res.getError());
				}
				return success("CleanPrice", ValidationType.CARDINALITY, "CleanPrice", path, "");
			})
			.collect(toList());
	}

}
