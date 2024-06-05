package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
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

public class ProductTaxonomyOnlyExistsValidator implements ValidatorWithArg<ProductTaxonomy, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ProductTaxonomy> ValidationResult<ProductTaxonomy> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("source", ExistenceChecker.isSet((TaxonomySourceEnum) o.getSource()))
				.put("value", ExistenceChecker.isSet((TaxonomyValue) o.getValue()))
				.put("primaryAssetClass", ExistenceChecker.isSet((FieldWithMetaAssetClassEnum) o.getPrimaryAssetClass()))
				.put("secondaryAssetClass", ExistenceChecker.isSet((List<? extends FieldWithMetaAssetClassEnum>) o.getSecondaryAssetClass()))
				.put("productQualifier", ExistenceChecker.isSet((String) o.getProductQualifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ProductTaxonomy", ValidationType.ONLY_EXISTS, "ProductTaxonomy", path, "");
		}
		return failure("ProductTaxonomy", ValidationType.ONLY_EXISTS, "ProductTaxonomy", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
