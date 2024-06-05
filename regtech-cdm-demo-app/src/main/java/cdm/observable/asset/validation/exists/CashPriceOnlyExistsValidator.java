package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.CashPriceTypeEnum;
import cdm.observable.asset.FeeTypeEnum;
import cdm.observable.asset.PremiumExpression;
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

public class CashPriceOnlyExistsValidator implements ValidatorWithArg<CashPrice, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CashPrice> ValidationResult<CashPrice> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cashPriceType", ExistenceChecker.isSet((CashPriceTypeEnum) o.getCashPriceType()))
				.put("premiumExpression", ExistenceChecker.isSet((PremiumExpression) o.getPremiumExpression()))
				.put("feeType", ExistenceChecker.isSet((FeeTypeEnum) o.getFeeType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CashPrice", ValidationType.ONLY_EXISTS, "CashPrice", path, "");
		}
		return failure("CashPrice", ValidationType.ONLY_EXISTS, "CashPrice", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
