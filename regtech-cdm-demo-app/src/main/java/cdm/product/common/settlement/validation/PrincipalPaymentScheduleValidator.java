package cdm.product.common.settlement.validation;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.common.settlement.PrincipalPayment;
import cdm.product.common.settlement.PrincipalPaymentSchedule;
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

public class PrincipalPaymentScheduleValidator implements Validator<PrincipalPaymentSchedule> {

	private List<ComparisonResult> getComparisonResults(PrincipalPaymentSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("initialPrincipalPayment", (PrincipalPayment) o.getInitialPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("intermediatePrincipalPayment", (AdjustableRelativeOrPeriodicDates) o.getIntermediatePrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalPrincipalPayment", (PrincipalPayment) o.getFinalPrincipalPayment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PrincipalPaymentSchedule> validate(RosettaPath path, PrincipalPaymentSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PrincipalPaymentSchedule", ValidationType.CARDINALITY, "PrincipalPaymentSchedule", path, "", error);
		}
		return success("PrincipalPaymentSchedule", ValidationType.CARDINALITY, "PrincipalPaymentSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PrincipalPaymentSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PrincipalPaymentSchedule", ValidationType.CARDINALITY, "PrincipalPaymentSchedule", path, "", res.getError());
				}
				return success("PrincipalPaymentSchedule", ValidationType.CARDINALITY, "PrincipalPaymentSchedule", path, "");
			})
			.collect(toList());
	}

}