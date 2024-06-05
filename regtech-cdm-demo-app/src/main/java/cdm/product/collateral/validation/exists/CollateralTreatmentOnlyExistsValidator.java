package cdm.product.collateral.validation.exists;

import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.ConcentrationLimit;
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

public class CollateralTreatmentOnlyExistsValidator implements ValidatorWithArg<CollateralTreatment, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralTreatment> ValidationResult<CollateralTreatment> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("valuationTreatment", ExistenceChecker.isSet((CollateralValuationTreatment) o.getValuationTreatment()))
				.put("concentrationLimit", ExistenceChecker.isSet((List<? extends ConcentrationLimit>) o.getConcentrationLimit()))
				.put("isIncluded", ExistenceChecker.isSet((Boolean) o.getIsIncluded()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralTreatment", ValidationType.ONLY_EXISTS, "CollateralTreatment", path, "");
		}
		return failure("CollateralTreatment", ValidationType.ONLY_EXISTS, "CollateralTreatment", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
