package cdm.product.asset.validation.exists;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.product.asset.BondReference;
import cdm.product.asset.FixedRateSpecification;
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

public class BondReferenceOnlyExistsValidator implements ValidatorWithArg<BondReference, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BondReference> ValidationResult<BondReference> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("bond", ExistenceChecker.isSet((ProductIdentifier) o.getBond()))
				.put("conditionPrecedentBond", ExistenceChecker.isSet((Boolean) o.getConditionPrecedentBond()))
				.put("discrepancyClause", ExistenceChecker.isSet((Boolean) o.getDiscrepancyClause()))
				.put("couponRate", ExistenceChecker.isSet((FixedRateSpecification) o.getCouponRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BondReference", ValidationType.ONLY_EXISTS, "BondReference", path, "");
		}
		return failure("BondReference", ValidationType.ONLY_EXISTS, "BondReference", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
