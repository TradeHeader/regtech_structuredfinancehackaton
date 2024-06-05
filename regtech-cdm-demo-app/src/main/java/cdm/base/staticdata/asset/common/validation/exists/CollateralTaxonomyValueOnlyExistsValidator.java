package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
import cdm.base.staticdata.asset.common.EU_EMIR_EligibleCollateralEnum;
import cdm.base.staticdata.asset.common.UK_EMIR_EligibleCollateralEnum;
import cdm.base.staticdata.asset.common.US_CFTC_PR_EligibleCollateralEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CollateralTaxonomyValueOnlyExistsValidator implements ValidatorWithArg<CollateralTaxonomyValue, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralTaxonomyValue> ValidationResult<CollateralTaxonomyValue> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("eu_EMIR_EligibleCollateral", ExistenceChecker.isSet((List<EU_EMIR_EligibleCollateralEnum>) o.getEu_EMIR_EligibleCollateral()))
				.put("uk_EMIR_EligibleCollateral", ExistenceChecker.isSet((List<UK_EMIR_EligibleCollateralEnum>) o.getUk_EMIR_EligibleCollateral()))
				.put("us_CFTC_PR_EligibleCollateral", ExistenceChecker.isSet((List<US_CFTC_PR_EligibleCollateralEnum>) o.getUs_CFTC_PR_EligibleCollateral()))
				.put("nonEnumeratedTaxonomyValue", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getNonEnumeratedTaxonomyValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralTaxonomyValue", ValidationType.ONLY_EXISTS, "CollateralTaxonomyValue", path, "");
		}
		return failure("CollateralTaxonomyValue", ValidationType.ONLY_EXISTS, "CollateralTaxonomyValue", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
