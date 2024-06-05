package cdm.product.template.validation.exists;

import cdm.product.template.Composite;
import cdm.product.template.FxFeature;
import cdm.product.template.Quanto;
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

public class FxFeatureOnlyExistsValidator implements ValidatorWithArg<FxFeature, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxFeature> ValidationResult<FxFeature> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("referenceCurrency", ExistenceChecker.isSet((FieldWithMetaString) o.getReferenceCurrency()))
				.put("composite", ExistenceChecker.isSet((Composite) o.getComposite()))
				.put("quanto", ExistenceChecker.isSet((Quanto) o.getQuanto()))
				.put("crossCurrency", ExistenceChecker.isSet((Composite) o.getCrossCurrency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxFeature", ValidationType.ONLY_EXISTS, "FxFeature", path, "");
		}
		return failure("FxFeature", ValidationType.ONLY_EXISTS, "FxFeature", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
