package cdm.product.collateral.validation;

import cdm.base.math.NumberBound;
import cdm.product.collateral.AlternativeToInterestAmountEnum;
import cdm.product.collateral.CollateralInterestHandlingEnum;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestNotification;
import cdm.product.collateral.InterestAmountApplication;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CollateralInterestHandlingParametersValidator implements Validator<CollateralInterestHandlingParameters> {

	private List<ComparisonResult> getComparisonResults(CollateralInterestHandlingParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("interestPaymentHandling", (CollateralInterestHandlingEnum) o.getInterestPaymentHandling() != null ? 1 : 0, 1, 1), 
				checkCardinality("netPostedAndHeldInterest", (Boolean) o.getNetPostedAndHeldInterest() != null ? 1 : 0, 1, 1), 
				checkCardinality("netInterestWithMarginCalls", (Boolean) o.getNetInterestWithMarginCalls() != null ? 1 : 0, 1, 1), 
				checkCardinality("includeAccrualInMarginCalc", (Boolean) o.getIncludeAccrualInMarginCalc() != null ? 1 : 0, 1, 1), 
				checkCardinality("accrueInterestOnUnsettledInterest", (Boolean) o.getAccrueInterestOnUnsettledInterest() != null ? 1 : 0, 0, 1), 
				checkCardinality("onFullReturn", (Boolean) o.getOnFullReturn() != null ? 1 : 0, 1, 1), 
				checkCardinality("onPartialReturn", (Boolean) o.getOnPartialReturn() != null ? 1 : 0, 1, 1), 
				checkCardinality("interestAmountApplication", (InterestAmountApplication) o.getInterestAmountApplication() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestRolloverLimit", (NumberBound) o.getInterestRolloverLimit() != null ? 1 : 0, 0, 1), 
				checkCardinality("writeoffLimit", (NumberBound) o.getWriteoffLimit() != null ? 1 : 0, 0, 1), 
				checkCardinality("alternativeToInterestAmount", (AlternativeToInterestAmountEnum) o.getAlternativeToInterestAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("alternativeProvision", (String) o.getAlternativeProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("cutoffTime", (LocalTime) o.getCutoffTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("notification", (CollateralInterestNotification) o.getNotification() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, CollateralInterestHandlingParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralInterestHandlingParameters", ValidationType.CARDINALITY, "CollateralInterestHandlingParameters", path, "", error);
		}
		return success("CollateralInterestHandlingParameters", ValidationType.CARDINALITY, "CollateralInterestHandlingParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralInterestHandlingParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralInterestHandlingParameters", ValidationType.CARDINALITY, "CollateralInterestHandlingParameters", path, "", res.getError());
				}
				return success("CollateralInterestHandlingParameters", ValidationType.CARDINALITY, "CollateralInterestHandlingParameters", path, "");
			})
			.collect(toList());
	}

}
