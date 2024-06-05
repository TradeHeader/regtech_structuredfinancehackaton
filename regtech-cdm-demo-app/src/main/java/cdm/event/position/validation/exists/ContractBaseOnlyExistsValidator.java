package cdm.event.position.validation.exists;

import cdm.event.common.metafields.ReferenceWithMetaContractDetails;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails;
import cdm.event.position.ContractBase;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral;
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

public class ContractBaseOnlyExistsValidator implements ValidatorWithArg<ContractBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ContractBase> ValidationResult<ContractBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("contractDetails", ExistenceChecker.isSet((ReferenceWithMetaContractDetails) o.getContractDetails()))
				.put("executionDetails", ExistenceChecker.isSet((ReferenceWithMetaExecutionDetails) o.getExecutionDetails()))
				.put("collateral", ExistenceChecker.isSet((ReferenceWithMetaCollateral) o.getCollateral()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ContractBase", ValidationType.ONLY_EXISTS, "ContractBase", path, "");
		}
		return failure("ContractBase", ValidationType.ONLY_EXISTS, "ContractBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
