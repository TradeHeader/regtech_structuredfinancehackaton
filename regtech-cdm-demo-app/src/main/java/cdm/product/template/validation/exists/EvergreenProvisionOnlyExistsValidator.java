package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.staticdata.party.PartyRole;
import cdm.observable.asset.Price;
import cdm.product.template.EvergreenProvision;
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

public class EvergreenProvisionOnlyExistsValidator implements ValidatorWithArg<EvergreenProvision, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EvergreenProvision> ValidationResult<EvergreenProvision> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("singlePartyOption", ExistenceChecker.isSet((PartyRole) o.getSinglePartyOption()))
				.put("noticePeriod", ExistenceChecker.isSet((RelativeDateOffset) o.getNoticePeriod()))
				.put("noticeDeadlinePeriod", ExistenceChecker.isSet((RelativeDateOffset) o.getNoticeDeadlinePeriod()))
				.put("noticeDeadlineDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getNoticeDeadlineDateTime()))
				.put("extensionFrequency", ExistenceChecker.isSet((AdjustableRelativeOrPeriodicDates) o.getExtensionFrequency()))
				.put("finalPeriodFeeAdjustment", ExistenceChecker.isSet((Price) o.getFinalPeriodFeeAdjustment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EvergreenProvision", ValidationType.ONLY_EXISTS, "EvergreenProvision", path, "");
		}
		return failure("EvergreenProvision", ValidationType.ONLY_EXISTS, "EvergreenProvision", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
