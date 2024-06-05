package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaAccount;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
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

public class PartyReferencePayerReceiverOnlyExistsValidator implements ValidatorWithArg<PartyReferencePayerReceiver, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PartyReferencePayerReceiver> ValidationResult<PartyReferencePayerReceiver> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerPartyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getPayerPartyReference()))
				.put("payerAccountReference", ExistenceChecker.isSet((ReferenceWithMetaAccount) o.getPayerAccountReference()))
				.put("receiverPartyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getReceiverPartyReference()))
				.put("receiverAccountReference", ExistenceChecker.isSet((ReferenceWithMetaAccount) o.getReceiverAccountReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PartyReferencePayerReceiver", ValidationType.ONLY_EXISTS, "PartyReferencePayerReceiver", path, "");
		}
		return failure("PartyReferencePayerReceiver", ValidationType.ONLY_EXISTS, "PartyReferencePayerReceiver", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
