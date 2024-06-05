package cdm.product.asset.validation.exists;

import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaLegalEntity;
import cdm.product.asset.ReferenceObligation;
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

public class ReferenceObligationOnlyExistsValidator implements ValidatorWithArg<ReferenceObligation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ReferenceObligation> ValidationResult<ReferenceObligation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("security", ExistenceChecker.isSet((Security) o.getSecurity()))
				.put("loan", ExistenceChecker.isSet((Loan) o.getLoan()))
				.put("primaryObligor", ExistenceChecker.isSet((LegalEntity) o.getPrimaryObligor()))
				.put("primaryObligorReference", ExistenceChecker.isSet((ReferenceWithMetaLegalEntity) o.getPrimaryObligorReference()))
				.put("guarantor", ExistenceChecker.isSet((LegalEntity) o.getGuarantor()))
				.put("guarantorReference", ExistenceChecker.isSet((String) o.getGuarantorReference()))
				.put("standardReferenceObligation", ExistenceChecker.isSet((Boolean) o.getStandardReferenceObligation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ReferenceObligation", ValidationType.ONLY_EXISTS, "ReferenceObligation", path, "");
		}
		return failure("ReferenceObligation", ValidationType.ONLY_EXISTS, "ReferenceObligation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
