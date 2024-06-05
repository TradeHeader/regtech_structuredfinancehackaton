package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PayoutBase;
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

public class PayoutBaseTypeFormatValidator implements Validator<PayoutBase> {

	private List<ComparisonResult> getComparisonResults(PayoutBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PayoutBase> validate(RosettaPath path, PayoutBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PayoutBase", ValidationType.TYPE_FORMAT, "PayoutBase", path, "", error);
		}
		return success("PayoutBase", ValidationType.TYPE_FORMAT, "PayoutBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PayoutBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PayoutBase", ValidationType.TYPE_FORMAT, "PayoutBase", path, "", res.getError());
				}
				return success("PayoutBase", ValidationType.TYPE_FORMAT, "PayoutBase", path, "");
			})
			.collect(toList());
	}

}
