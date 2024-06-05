package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PaymentRule;
import cdm.product.common.settlement.PercentageRule;
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

public class PaymentRuleValidator implements Validator<PaymentRule> {

	private List<ComparisonResult> getComparisonResults(PaymentRule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("percentageRule", (PercentageRule) o.getPercentageRule() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PaymentRule> validate(RosettaPath path, PaymentRule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentRule", ValidationType.CARDINALITY, "PaymentRule", path, "", error);
		}
		return success("PaymentRule", ValidationType.CARDINALITY, "PaymentRule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentRule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentRule", ValidationType.CARDINALITY, "PaymentRule", path, "", res.getError());
				}
				return success("PaymentRule", ValidationType.CARDINALITY, "PaymentRule", path, "");
			})
			.collect(toList());
	}

}
