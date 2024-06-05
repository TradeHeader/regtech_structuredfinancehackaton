package cdm.observable.event.validation.exists;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.PubliclyAvailableInformation;
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

public class CreditEventNoticeOnlyExistsValidator implements ValidatorWithArg<CreditEventNotice, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditEventNotice> ValidationResult<CreditEventNotice> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("notifyingParty", ExistenceChecker.isSet((List<CounterpartyRoleEnum>) o.getNotifyingParty()))
				.put("businessCenter", ExistenceChecker.isSet((BusinessCenterEnum) o.getBusinessCenter()))
				.put("publiclyAvailableInformation", ExistenceChecker.isSet((PubliclyAvailableInformation) o.getPubliclyAvailableInformation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditEventNotice", ValidationType.ONLY_EXISTS, "CreditEventNotice", path, "");
		}
		return failure("CreditEventNotice", ValidationType.ONLY_EXISTS, "CreditEventNotice", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
