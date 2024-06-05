package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PaymentDiscounting;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PaymentDiscountingTypeFormatValidator implements Validator<PaymentDiscounting> {

	private List<ComparisonResult> getComparisonResults(PaymentDiscounting o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PaymentDiscounting> validate(RosettaPath path, PaymentDiscounting o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentDiscounting", ValidationType.TYPE_FORMAT, "PaymentDiscounting", path, "", error);
		}
		return success("PaymentDiscounting", ValidationType.TYPE_FORMAT, "PaymentDiscounting", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentDiscounting o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentDiscounting", ValidationType.TYPE_FORMAT, "PaymentDiscounting", path, "", res.getError());
				}
				return success("PaymentDiscounting", ValidationType.TYPE_FORMAT, "PaymentDiscounting", path, "");
			})
			.collect(toList());
	}

}
