package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.event.common.BusinessEvent;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.TradeState;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class BusinessEventOnlyExistsValidator implements ValidatorWithArg<BusinessEvent, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BusinessEvent> ValidationResult<BusinessEvent> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("intent", ExistenceChecker.isSet((EventIntentEnum) o.getIntent()))
				.put("corporateActionIntent", ExistenceChecker.isSet((CorporateActionTypeEnum) o.getCorporateActionIntent()))
				.put("eventDate", ExistenceChecker.isSet((Date) o.getEventDate()))
				.put("effectiveDate", ExistenceChecker.isSet((Date) o.getEffectiveDate()))
				.put("packageInformation", ExistenceChecker.isSet((IdentifiedList) o.getPackageInformation()))
				.put("instruction", ExistenceChecker.isSet((List<? extends Instruction>) o.getInstruction()))
				.put("eventQualifier", ExistenceChecker.isSet((String) o.getEventQualifier()))
				.put("after", ExistenceChecker.isSet((List<? extends TradeState>) o.getAfter()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BusinessEvent", ValidationType.ONLY_EXISTS, "BusinessEvent", path, "");
		}
		return failure("BusinessEvent", ValidationType.ONLY_EXISTS, "BusinessEvent", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
