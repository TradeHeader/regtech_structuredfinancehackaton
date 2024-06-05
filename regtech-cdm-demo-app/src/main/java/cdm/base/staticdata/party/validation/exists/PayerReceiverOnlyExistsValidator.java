package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PayerReceiver;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PayerReceiverOnlyExistsValidator implements ValidatorWithArg<PayerReceiver, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PayerReceiver> ValidationResult<PayerReceiver> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getPayer()))
				.put("receiver", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getReceiver()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PayerReceiver", ValidationType.ONLY_EXISTS, "PayerReceiver", path, "");
		}
		return failure("PayerReceiver", ValidationType.ONLY_EXISTS, "PayerReceiver", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
