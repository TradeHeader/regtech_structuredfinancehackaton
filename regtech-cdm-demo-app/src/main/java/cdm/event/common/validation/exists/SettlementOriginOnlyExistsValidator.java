package cdm.event.common.validation.exists;

import cdm.event.common.SettlementOrigin;
import cdm.product.asset.metafields.ReferenceWithMetaCommodityPayout;
import cdm.product.asset.metafields.ReferenceWithMetaCreditDefaultPayout;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout;
import cdm.product.common.settlement.metafields.ReferenceWithMetaSettlementTerms;
import cdm.product.template.metafields.ReferenceWithMetaAssetPayout;
import cdm.product.template.metafields.ReferenceWithMetaFixedPricePayout;
import cdm.product.template.metafields.ReferenceWithMetaForwardPayout;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
import cdm.product.template.metafields.ReferenceWithMetaPerformancePayout;
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

public class SettlementOriginOnlyExistsValidator implements ValidatorWithArg<SettlementOrigin, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SettlementOrigin> ValidationResult<SettlementOrigin> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("commodityPayout", ExistenceChecker.isSet((ReferenceWithMetaCommodityPayout) o.getCommodityPayout()))
				.put("creditDefaultPayout", ExistenceChecker.isSet((ReferenceWithMetaCreditDefaultPayout) o.getCreditDefaultPayout()))
				.put("forwardPayout", ExistenceChecker.isSet((ReferenceWithMetaForwardPayout) o.getForwardPayout()))
				.put("interestRatePayout", ExistenceChecker.isSet((ReferenceWithMetaInterestRatePayout) o.getInterestRatePayout()))
				.put("optionPayout", ExistenceChecker.isSet((ReferenceWithMetaOptionPayout) o.getOptionPayout()))
				.put("assetPayout", ExistenceChecker.isSet((ReferenceWithMetaAssetPayout) o.getAssetPayout()))
				.put("settlementTerms", ExistenceChecker.isSet((ReferenceWithMetaSettlementTerms) o.getSettlementTerms()))
				.put("performancePayout", ExistenceChecker.isSet((ReferenceWithMetaPerformancePayout) o.getPerformancePayout()))
				.put("fixedPricePayout", ExistenceChecker.isSet((ReferenceWithMetaFixedPricePayout) o.getFixedPricePayout()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SettlementOrigin", ValidationType.ONLY_EXISTS, "SettlementOrigin", path, "");
		}
		return failure("SettlementOrigin", ValidationType.ONLY_EXISTS, "SettlementOrigin", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
