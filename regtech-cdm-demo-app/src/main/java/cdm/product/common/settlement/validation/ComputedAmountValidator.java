package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.ComputedAmount;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ComputedAmountValidator implements Validator<ComputedAmount> {

	private List<ComparisonResult> getComparisonResults(ComputedAmount o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("callFunction", (String) o.getCallFunction() != null ? 1 : 0, 1, 1), 
				checkCardinality("amount", (BigDecimal) o.getAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ComputedAmount> validate(RosettaPath path, ComputedAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ComputedAmount", ValidationType.CARDINALITY, "ComputedAmount", path, "", error);
		}
		return success("ComputedAmount", ValidationType.CARDINALITY, "ComputedAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ComputedAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ComputedAmount", ValidationType.CARDINALITY, "ComputedAmount", path, "", res.getError());
				}
				return success("ComputedAmount", ValidationType.CARDINALITY, "ComputedAmount", path, "");
			})
			.collect(toList());
	}

}
