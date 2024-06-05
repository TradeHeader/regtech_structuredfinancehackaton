package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.TaxonomyValue;
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

public class TaxonomyOnlyExistsValidator implements ValidatorWithArg<Taxonomy, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Taxonomy> ValidationResult<Taxonomy> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("source", ExistenceChecker.isSet((TaxonomySourceEnum) o.getSource()))
				.put("value", ExistenceChecker.isSet((TaxonomyValue) o.getValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Taxonomy", ValidationType.ONLY_EXISTS, "Taxonomy", path, "");
		}
		return failure("Taxonomy", ValidationType.ONLY_EXISTS, "Taxonomy", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
