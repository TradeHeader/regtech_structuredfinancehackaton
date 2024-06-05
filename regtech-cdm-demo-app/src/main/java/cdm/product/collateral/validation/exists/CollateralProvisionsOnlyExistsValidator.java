package cdm.product.collateral.validation.exists;

import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.CollateralTypeEnum;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.SubstitutionProvisions;
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

public class CollateralProvisionsOnlyExistsValidator implements ValidatorWithArg<CollateralProvisions, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralProvisions> ValidationResult<CollateralProvisions> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("collateralType", ExistenceChecker.isSet((CollateralTypeEnum) o.getCollateralType()))
				.put("eligibleCollateral", ExistenceChecker.isSet((List<? extends EligibleCollateralCriteria>) o.getEligibleCollateral()))
				.put("substitutionProvisions", ExistenceChecker.isSet((SubstitutionProvisions) o.getSubstitutionProvisions()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralProvisions", ValidationType.ONLY_EXISTS, "CollateralProvisions", path, "");
		}
		return failure("CollateralProvisions", ValidationType.ONLY_EXISTS, "CollateralProvisions", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
