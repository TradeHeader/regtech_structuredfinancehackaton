package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.CollateralTaxonomy;
import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
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

public class CollateralTaxonomyOnlyExistsValidator implements ValidatorWithArg<CollateralTaxonomy, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralTaxonomy> ValidationResult<CollateralTaxonomy> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("taxonomyValue", ExistenceChecker.isSet((CollateralTaxonomyValue) o.getTaxonomyValue()))
				.put("taxonomySource", ExistenceChecker.isSet((TaxonomySourceEnum) o.getTaxonomySource()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralTaxonomy", ValidationType.ONLY_EXISTS, "CollateralTaxonomy", path, "");
		}
		return failure("CollateralTaxonomy", ValidationType.ONLY_EXISTS, "CollateralTaxonomy", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
