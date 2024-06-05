package cdm.product.collateral.validation.exists;

import cdm.product.collateral.CollateralValuationTreatment;
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

public class CollateralValuationTreatmentOnlyExistsValidator implements ValidatorWithArg<CollateralValuationTreatment, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralValuationTreatment> ValidationResult<CollateralValuationTreatment> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("haircutPercentage", ExistenceChecker.isSet((BigDecimal) o.getHaircutPercentage()))
				.put("marginPercentage", ExistenceChecker.isSet((BigDecimal) o.getMarginPercentage()))
				.put("fxHaircutPercentage", ExistenceChecker.isSet((BigDecimal) o.getFxHaircutPercentage()))
				.put("additionalHaircutPercentage", ExistenceChecker.isSet((BigDecimal) o.getAdditionalHaircutPercentage()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralValuationTreatment", ValidationType.ONLY_EXISTS, "CollateralValuationTreatment", path, "");
		}
		return failure("CollateralValuationTreatment", ValidationType.ONLY_EXISTS, "CollateralValuationTreatment", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
