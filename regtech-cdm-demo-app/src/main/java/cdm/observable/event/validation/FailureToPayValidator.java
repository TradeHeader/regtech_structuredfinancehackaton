package cdm.observable.event.validation;

import cdm.observable.asset.Money;
import cdm.observable.event.FailureToPay;
import cdm.observable.event.GracePeriodExtension;
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

public class FailureToPayValidator implements Validator<FailureToPay> {

	private List<ComparisonResult> getComparisonResults(FailureToPay o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("gracePeriodExtension", (GracePeriodExtension) o.getGracePeriodExtension() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentRequirement", (Money) o.getPaymentRequirement() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FailureToPay> validate(RosettaPath path, FailureToPay o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FailureToPay", ValidationType.CARDINALITY, "FailureToPay", path, "", error);
		}
		return success("FailureToPay", ValidationType.CARDINALITY, "FailureToPay", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FailureToPay o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FailureToPay", ValidationType.CARDINALITY, "FailureToPay", path, "", res.getError());
				}
				return success("FailureToPay", ValidationType.CARDINALITY, "FailureToPay", path, "");
			})
			.collect(toList());
	}

}
