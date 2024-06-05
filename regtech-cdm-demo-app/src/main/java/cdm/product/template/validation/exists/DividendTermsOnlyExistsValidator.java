package cdm.product.template.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPayoutRatio;
import cdm.product.template.DividendTerms;
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

public class DividendTermsOnlyExistsValidator implements ValidatorWithArg<DividendTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendTerms> ValidationResult<DividendTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("manufacturedIncomeRequirement", ExistenceChecker.isSet((DividendPayoutRatio) o.getManufacturedIncomeRequirement()))
				.put("dividendEntitlement", ExistenceChecker.isSet((DividendEntitlementEnum) o.getDividendEntitlement()))
				.put("minimumBillingAmount", ExistenceChecker.isSet((Money) o.getMinimumBillingAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendTerms", ValidationType.ONLY_EXISTS, "DividendTerms", path, "");
		}
		return failure("DividendTerms", ValidationType.ONLY_EXISTS, "DividendTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
