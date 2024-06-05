package cdm.product.common.settlement.validation.exists;

import cdm.base.math.Rounding;
import cdm.product.asset.SpreadSchedule;
import cdm.product.common.settlement.CommodityPriceReturnTerms;
import cdm.product.common.settlement.RollFeature;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CommodityPriceReturnTermsOnlyExistsValidator implements ValidatorWithArg<CommodityPriceReturnTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CommodityPriceReturnTerms> ValidationResult<CommodityPriceReturnTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rounding", ExistenceChecker.isSet((Rounding) o.getRounding()))
				.put("spread", ExistenceChecker.isSet((SpreadSchedule) o.getSpread()))
				.put("rollFeature", ExistenceChecker.isSet((RollFeature) o.getRollFeature()))
				.put("conversionFactor", ExistenceChecker.isSet((BigDecimal) o.getConversionFactor()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CommodityPriceReturnTerms", ValidationType.ONLY_EXISTS, "CommodityPriceReturnTerms", path, "");
		}
		return failure("CommodityPriceReturnTerms", ValidationType.ONLY_EXISTS, "CommodityPriceReturnTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
