package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.Period;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.event.common.Transfer;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.CallingPartyEnum;
import cdm.product.template.CancelableProvision;
import cdm.product.template.CancelableProvisionAdjustedDates;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseNotice;
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

public class CancelableProvisionValidator implements Validator<CancelableProvision> {

	private List<ComparisonResult> getComparisonResults(CancelableProvision o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("buyer", (CounterpartyRoleEnum) o.getBuyer() != null ? 1 : 0, 1, 1), 
				checkCardinality("seller", (CounterpartyRoleEnum) o.getSeller() != null ? 1 : 0, 1, 1), 
				checkCardinality("americanExercise", (AmericanExercise) o.getAmericanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("bermudaExercise", (BermudaExercise) o.getBermudaExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("europeanExercise", (EuropeanExercise) o.getEuropeanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseNotice", (ExerciseNotice) o.getExerciseNotice() != null ? 1 : 0, 0, 1), 
				checkCardinality("followUpConfirmation", (Boolean) o.getFollowUpConfirmation() != null ? 1 : 0, 1, 1), 
				checkCardinality("cancelableProvisionAdjustedDates", (CancelableProvisionAdjustedDates) o.getCancelableProvisionAdjustedDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialFee", (Transfer) o.getInitialFee() != null ? 1 : 0, 0, 1), 
				checkCardinality("callingParty", (CallingPartyEnum) o.getCallingParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("earliestDate", (AdjustableOrRelativeDate) o.getEarliestDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("expirationDate", (AdjustableOrRelativeDate) o.getExpirationDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectiveDate", (AdjustableOrRelativeDates) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectivePeriod", (Period) o.getEffectivePeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("earliestCancellationTime", (BusinessCenterTime) o.getEarliestCancellationTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("latestCancelationTime", (BusinessCenterTime) o.getLatestCancelationTime() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CancelableProvision", ValidationType.CARDINALITY, "CancelableProvision", path, "", error);
		}
		return success("CancelableProvision", ValidationType.CARDINALITY, "CancelableProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CancelableProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CancelableProvision", ValidationType.CARDINALITY, "CancelableProvision", path, "", res.getError());
				}
				return success("CancelableProvision", ValidationType.CARDINALITY, "CancelableProvision", path, "");
			})
			.collect(toList());
	}

}
