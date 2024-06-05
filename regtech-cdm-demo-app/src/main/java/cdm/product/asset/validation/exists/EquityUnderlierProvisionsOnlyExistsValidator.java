package cdm.product.asset.validation.exists;

import cdm.product.asset.EquityUnderlierProvisions;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class EquityUnderlierProvisionsOnlyExistsValidator implements ValidatorWithArg<EquityUnderlierProvisions, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EquityUnderlierProvisions> ValidationResult<EquityUnderlierProvisions> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("multipleExchangeIndexAnnexFallback", ExistenceChecker.isSet((Boolean) o.getMultipleExchangeIndexAnnexFallback()))
				.put("componentSecurityIndexAnnexFallback", ExistenceChecker.isSet((Boolean) o.getComponentSecurityIndexAnnexFallback()))
				.put("localJurisdiction", ExistenceChecker.isSet((FieldWithMetaString) o.getLocalJurisdiction()))
				.put("relevantJurisdiction", ExistenceChecker.isSet((FieldWithMetaString) o.getRelevantJurisdiction()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EquityUnderlierProvisions", ValidationType.ONLY_EXISTS, "EquityUnderlierProvisions", path, "");
		}
		return failure("EquityUnderlierProvisions", ValidationType.ONLY_EXISTS, "EquityUnderlierProvisions", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
