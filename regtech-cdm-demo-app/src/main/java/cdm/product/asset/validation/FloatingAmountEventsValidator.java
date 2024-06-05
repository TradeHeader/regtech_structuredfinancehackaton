package cdm.product.asset.validation;

import cdm.product.asset.AdditionalFixedPayments;
import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.FloatingAmountProvisions;
import cdm.product.asset.InterestShortFall;
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

public class FloatingAmountEventsValidator implements Validator<FloatingAmountEvents> {

	private List<ComparisonResult> getComparisonResults(FloatingAmountEvents o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("failureToPayPrincipal", (Boolean) o.getFailureToPayPrincipal() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestShortfall", (InterestShortFall) o.getInterestShortfall() != null ? 1 : 0, 0, 1), 
				checkCardinality("writedown", (Boolean) o.getWritedown() != null ? 1 : 0, 0, 1), 
				checkCardinality("impliedWritedown", (Boolean) o.getImpliedWritedown() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingAmountProvisions", (FloatingAmountProvisions) o.getFloatingAmountProvisions() != null ? 1 : 0, 0, 1), 
				checkCardinality("additionalFixedPayments", (AdditionalFixedPayments) o.getAdditionalFixedPayments() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingAmountEvents> validate(RosettaPath path, FloatingAmountEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingAmountEvents", ValidationType.CARDINALITY, "FloatingAmountEvents", path, "", error);
		}
		return success("FloatingAmountEvents", ValidationType.CARDINALITY, "FloatingAmountEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingAmountEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingAmountEvents", ValidationType.CARDINALITY, "FloatingAmountEvents", path, "", res.getError());
				}
				return success("FloatingAmountEvents", ValidationType.CARDINALITY, "FloatingAmountEvents", path, "");
			})
			.collect(toList());
	}

}
