package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
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

public class IndexOnlyExistsValidator implements ValidatorWithArg<Index, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Index> ValidationResult<Index> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("productTaxonomy", ExistenceChecker.isSet((List<? extends ProductTaxonomy>) o.getProductTaxonomy()))
				.put("productIdentifier", ExistenceChecker.isSet((List<? extends ReferenceWithMetaProductIdentifier>) o.getProductIdentifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Index", ValidationType.ONLY_EXISTS, "Index", path, "");
		}
		return failure("Index", ValidationType.ONLY_EXISTS, "Index", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
