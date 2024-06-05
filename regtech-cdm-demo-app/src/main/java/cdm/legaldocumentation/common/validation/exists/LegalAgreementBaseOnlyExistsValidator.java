package cdm.legaldocumentation.common.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.legaldocumentation.common.LegalAgreementBase;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.Resource;
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

public class LegalAgreementBaseOnlyExistsValidator implements ValidatorWithArg<LegalAgreementBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends LegalAgreementBase> ValidationResult<LegalAgreementBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("agreementDate", ExistenceChecker.isSet((Date) o.getAgreementDate()))
				.put("effectiveDate", ExistenceChecker.isSet((Date) o.getEffectiveDate()))
				.put("identifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getIdentifier()))
				.put("legalAgreementIdentification", ExistenceChecker.isSet((LegalAgreementIdentification) o.getLegalAgreementIdentification()))
				.put("contractualParty", ExistenceChecker.isSet((List<? extends ReferenceWithMetaParty>) o.getContractualParty()))
				.put("otherParty", ExistenceChecker.isSet((List<? extends PartyRole>) o.getOtherParty()))
				.put("attachment", ExistenceChecker.isSet((List<? extends Resource>) o.getAttachment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("LegalAgreementBase", ValidationType.ONLY_EXISTS, "LegalAgreementBase", path, "");
		}
		return failure("LegalAgreementBase", ValidationType.ONLY_EXISTS, "LegalAgreementBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
