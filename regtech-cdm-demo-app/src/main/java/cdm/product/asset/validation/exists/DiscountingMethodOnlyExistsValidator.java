package cdm.product.asset.validation.exists;

import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.DiscountingTypeEnum;
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

public class DiscountingMethodOnlyExistsValidator implements ValidatorWithArg<DiscountingMethod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DiscountingMethod> ValidationResult<DiscountingMethod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("discountingType", ExistenceChecker.isSet((DiscountingTypeEnum) o.getDiscountingType()))
				.put("discountRate", ExistenceChecker.isSet((BigDecimal) o.getDiscountRate()))
				.put("discountRateDayCountFraction", ExistenceChecker.isSet((FieldWithMetaDayCountFractionEnum) o.getDiscountRateDayCountFraction()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DiscountingMethod", ValidationType.ONLY_EXISTS, "DiscountingMethod", path, "");
		}
		return failure("DiscountingMethod", ValidationType.ONLY_EXISTS, "DiscountingMethod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
