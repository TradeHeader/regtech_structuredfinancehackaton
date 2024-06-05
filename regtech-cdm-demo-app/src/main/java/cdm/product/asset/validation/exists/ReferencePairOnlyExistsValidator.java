package cdm.product.asset.validation.exists;

import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.metafields.FieldWithMetaEntityTypeEnum;
import cdm.product.asset.ReferenceObligation;
import cdm.product.asset.ReferencePair;
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

public class ReferencePairOnlyExistsValidator implements ValidatorWithArg<ReferencePair, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ReferencePair> ValidationResult<ReferencePair> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("referenceEntity", ExistenceChecker.isSet((LegalEntity) o.getReferenceEntity()))
				.put("referenceObligation", ExistenceChecker.isSet((ReferenceObligation) o.getReferenceObligation()))
				.put("noReferenceObligation", ExistenceChecker.isSet((Boolean) o.getNoReferenceObligation()))
				.put("entityType", ExistenceChecker.isSet((FieldWithMetaEntityTypeEnum) o.getEntityType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ReferencePair", ValidationType.ONLY_EXISTS, "ReferencePair", path, "");
		}
		return failure("ReferencePair", ValidationType.ONLY_EXISTS, "ReferencePair", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
