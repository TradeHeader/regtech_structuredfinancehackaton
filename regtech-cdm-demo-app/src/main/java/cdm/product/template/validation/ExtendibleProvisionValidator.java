package cdm.product.template.validation;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PartyRole;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.CallingPartyEnum;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseNotice;
import cdm.product.template.ExtendibleProvision;
import cdm.product.template.ExtendibleProvisionAdjustedDates;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ExtendibleProvisionValidator implements Validator<ExtendibleProvision> {

	private List<ComparisonResult> getComparisonResults(ExtendibleProvision o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("buyer", (CounterpartyRoleEnum) o.getBuyer() != null ? 1 : 0, 1, 1), 
				checkCardinality("seller", (CounterpartyRoleEnum) o.getSeller() != null ? 1 : 0, 1, 1), 
				checkCardinality("americanExercise", (AmericanExercise) o.getAmericanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("bermudaExercise", (BermudaExercise) o.getBermudaExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("europeanExercise", (EuropeanExercise) o.getEuropeanExercise() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseNotice", (ExerciseNotice) o.getExerciseNotice() != null ? 1 : 0, 0, 1), 
				checkCardinality("followUpConfirmation", (Boolean) o.getFollowUpConfirmation() != null ? 1 : 0, 0, 1), 
				checkCardinality("extendibleProvisionAdjustedDates", (ExtendibleProvisionAdjustedDates) o.getExtendibleProvisionAdjustedDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("callingParty", (CallingPartyEnum) o.getCallingParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("singlePartyOption", (PartyRole) o.getSinglePartyOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticeDeadlinePeriod", (RelativeDateOffset) o.getNoticeDeadlinePeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("noticeDeadlineDateTime", (ZonedDateTime) o.getNoticeDeadlineDateTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("extensionTerm", (RelativeDateOffset) o.getExtensionTerm() != null ? 1 : 0, 0, 1), 
				checkCardinality("extensionPeriod", (AdjustableRelativeOrPeriodicDates) o.getExtensionPeriod() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ExtendibleProvision> validate(RosettaPath path, ExtendibleProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExtendibleProvision", ValidationType.CARDINALITY, "ExtendibleProvision", path, "", error);
		}
		return success("ExtendibleProvision", ValidationType.CARDINALITY, "ExtendibleProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExtendibleProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExtendibleProvision", ValidationType.CARDINALITY, "ExtendibleProvision", path, "", res.getError());
				}
				return success("ExtendibleProvision", ValidationType.CARDINALITY, "ExtendibleProvision", path, "");
			})
			.collect(toList());
	}

}
