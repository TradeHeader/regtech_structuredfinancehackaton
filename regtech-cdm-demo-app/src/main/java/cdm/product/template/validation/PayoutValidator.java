package cdm.product.template.validation;

import cdm.product.asset.CreditDefaultPayout;
import cdm.product.template.Payout;
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

public class PayoutValidator implements Validator<Payout> {

	private List<ComparisonResult> getComparisonResults(Payout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("creditDefaultPayout", (CreditDefaultPayout) o.getCreditDefaultPayout() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Payout> validate(RosettaPath path, Payout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Payout", ValidationType.CARDINALITY, "Payout", path, "", error);
		}
		return success("Payout", ValidationType.CARDINALITY, "Payout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Payout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Payout", ValidationType.CARDINALITY, "Payout", path, "", res.getError());
				}
				return success("Payout", ValidationType.CARDINALITY, "Payout", path, "");
			})
			.collect(toList());
	}

}
