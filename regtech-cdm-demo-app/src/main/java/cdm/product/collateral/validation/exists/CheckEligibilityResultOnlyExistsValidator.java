package cdm.product.collateral.validation.exists;

import cdm.product.collateral.CheckEligibilityResult;
import cdm.product.collateral.EligibilityQuery;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecification;
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

public class CheckEligibilityResultOnlyExistsValidator implements ValidatorWithArg<CheckEligibilityResult, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CheckEligibilityResult> ValidationResult<CheckEligibilityResult> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("isEligible", ExistenceChecker.isSet((Boolean) o.getIsEligible()))
				.put("matchingEligibleCriteria", ExistenceChecker.isSet((List<? extends EligibleCollateralCriteria>) o.getMatchingEligibleCriteria()))
				.put("eligibilityQuery", ExistenceChecker.isSet((EligibilityQuery) o.getEligibilityQuery()))
				.put("specification", ExistenceChecker.isSet((EligibleCollateralSpecification) o.getSpecification()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CheckEligibilityResult", ValidationType.ONLY_EXISTS, "CheckEligibilityResult", path, "");
		}
		return failure("CheckEligibilityResult", ValidationType.ONLY_EXISTS, "CheckEligibilityResult", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
