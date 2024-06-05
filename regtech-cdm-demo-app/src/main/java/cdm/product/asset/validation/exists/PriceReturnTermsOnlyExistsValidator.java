package cdm.product.asset.validation.exists;

import cdm.observable.asset.PriceSchedule;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.ReturnTypeEnum;
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

public class PriceReturnTermsOnlyExistsValidator implements ValidatorWithArg<PriceReturnTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PriceReturnTerms> ValidationResult<PriceReturnTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("valuationPriceInitial", ExistenceChecker.isSet((PriceSchedule) o.getValuationPriceInitial()))
				.put("valuationPriceFinal", ExistenceChecker.isSet((PriceSchedule) o.getValuationPriceFinal()))
				.put("returnType", ExistenceChecker.isSet((ReturnTypeEnum) o.getReturnType()))
				.put("conversionFactor", ExistenceChecker.isSet((BigDecimal) o.getConversionFactor()))
				.put("performance", ExistenceChecker.isSet((String) o.getPerformance()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PriceReturnTerms", ValidationType.ONLY_EXISTS, "PriceReturnTerms", path, "");
		}
		return failure("PriceReturnTerms", ValidationType.ONLY_EXISTS, "PriceReturnTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
