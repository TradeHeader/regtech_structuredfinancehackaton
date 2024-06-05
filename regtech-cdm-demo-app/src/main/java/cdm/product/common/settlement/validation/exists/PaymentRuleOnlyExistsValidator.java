package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.PaymentRule;
import cdm.product.common.settlement.PercentageRule;
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

public class PaymentRuleOnlyExistsValidator implements ValidatorWithArg<PaymentRule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PaymentRule> ValidationResult<PaymentRule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("percentageRule", ExistenceChecker.isSet((PercentageRule) o.getPercentageRule()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PaymentRule", ValidationType.ONLY_EXISTS, "PaymentRule", path, "");
		}
		return failure("PaymentRule", ValidationType.ONLY_EXISTS, "PaymentRule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
