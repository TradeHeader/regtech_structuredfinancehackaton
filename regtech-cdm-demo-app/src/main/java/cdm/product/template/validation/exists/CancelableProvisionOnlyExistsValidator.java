package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.Period;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.event.common.Transfer;
import cdm.product.common.schedule.FinalCalculationPeriodDateAdjustment;
import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.CallingPartyEnum;
import cdm.product.template.CancelableProvision;
import cdm.product.template.CancelableProvisionAdjustedDates;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseNotice;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CancelableProvisionOnlyExistsValidator implements ValidatorWithArg<CancelableProvision, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CancelableProvision> ValidationResult<CancelableProvision> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("buyer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getBuyer()))
				.put("seller", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getSeller()))
				.put("americanExercise", ExistenceChecker.isSet((AmericanExercise) o.getAmericanExercise()))
				.put("bermudaExercise", ExistenceChecker.isSet((BermudaExercise) o.getBermudaExercise()))
				.put("europeanExercise", ExistenceChecker.isSet((EuropeanExercise) o.getEuropeanExercise()))
				.put("exerciseNotice", ExistenceChecker.isSet((ExerciseNotice) o.getExerciseNotice()))
				.put("followUpConfirmation", ExistenceChecker.isSet((Boolean) o.getFollowUpConfirmation()))
				.put("cancelableProvisionAdjustedDates", ExistenceChecker.isSet((CancelableProvisionAdjustedDates) o.getCancelableProvisionAdjustedDates()))
				.put("finalCalculationPeriodDateAdjustment", ExistenceChecker.isSet((List<? extends FinalCalculationPeriodDateAdjustment>) o.getFinalCalculationPeriodDateAdjustment()))
				.put("initialFee", ExistenceChecker.isSet((Transfer) o.getInitialFee()))
				.put("callingParty", ExistenceChecker.isSet((CallingPartyEnum) o.getCallingParty()))
				.put("earliestDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getEarliestDate()))
				.put("expirationDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getExpirationDate()))
				.put("effectiveDate", ExistenceChecker.isSet((AdjustableOrRelativeDates) o.getEffectiveDate()))
				.put("effectivePeriod", ExistenceChecker.isSet((Period) o.getEffectivePeriod()))
				.put("earliestCancellationTime", ExistenceChecker.isSet((BusinessCenterTime) o.getEarliestCancellationTime()))
				.put("latestCancelationTime", ExistenceChecker.isSet((BusinessCenterTime) o.getLatestCancelationTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CancelableProvision", ValidationType.ONLY_EXISTS, "CancelableProvision", path, "");
		}
		return failure("CancelableProvision", ValidationType.ONLY_EXISTS, "CancelableProvision", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
