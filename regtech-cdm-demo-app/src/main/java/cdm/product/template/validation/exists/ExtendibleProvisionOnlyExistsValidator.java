package cdm.product.template.validation.exists;

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
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ExtendibleProvisionOnlyExistsValidator implements ValidatorWithArg<ExtendibleProvision, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExtendibleProvision> ValidationResult<ExtendibleProvision> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("buyer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getBuyer()))
				.put("seller", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getSeller()))
				.put("americanExercise", ExistenceChecker.isSet((AmericanExercise) o.getAmericanExercise()))
				.put("bermudaExercise", ExistenceChecker.isSet((BermudaExercise) o.getBermudaExercise()))
				.put("europeanExercise", ExistenceChecker.isSet((EuropeanExercise) o.getEuropeanExercise()))
				.put("exerciseNotice", ExistenceChecker.isSet((ExerciseNotice) o.getExerciseNotice()))
				.put("followUpConfirmation", ExistenceChecker.isSet((Boolean) o.getFollowUpConfirmation()))
				.put("extendibleProvisionAdjustedDates", ExistenceChecker.isSet((ExtendibleProvisionAdjustedDates) o.getExtendibleProvisionAdjustedDates()))
				.put("callingParty", ExistenceChecker.isSet((CallingPartyEnum) o.getCallingParty()))
				.put("singlePartyOption", ExistenceChecker.isSet((PartyRole) o.getSinglePartyOption()))
				.put("noticeDeadlinePeriod", ExistenceChecker.isSet((RelativeDateOffset) o.getNoticeDeadlinePeriod()))
				.put("noticeDeadlineDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getNoticeDeadlineDateTime()))
				.put("extensionTerm", ExistenceChecker.isSet((RelativeDateOffset) o.getExtensionTerm()))
				.put("extensionPeriod", ExistenceChecker.isSet((AdjustableRelativeOrPeriodicDates) o.getExtensionPeriod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExtendibleProvision", ValidationType.ONLY_EXISTS, "ExtendibleProvision", path, "");
		}
		return failure("ExtendibleProvision", ValidationType.ONLY_EXISTS, "ExtendibleProvision", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
