package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.CommodityInformationPublisherEnum;
import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import cdm.base.staticdata.asset.common.PriceSource;
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

public class CommodityProductDefinitionOnlyExistsValidator implements ValidatorWithArg<CommodityProductDefinition, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CommodityProductDefinition> ValidationResult<CommodityProductDefinition> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("referenceFramework", ExistenceChecker.isSet((CommodityReferenceFramework) o.getReferenceFramework()))
				.put("priceSource", ExistenceChecker.isSet((PriceSource) o.getPriceSource()))
				.put("commodityInfoPublisher", ExistenceChecker.isSet((CommodityInformationPublisherEnum) o.getCommodityInfoPublisher()))
				.put("exchangeId", ExistenceChecker.isSet((FieldWithMetaString) o.getExchangeId()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CommodityProductDefinition", ValidationType.ONLY_EXISTS, "CommodityProductDefinition", path, "");
		}
		return failure("CommodityProductDefinition", ValidationType.ONLY_EXISTS, "CommodityProductDefinition", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
