package cdm.product.common.settlement.validation.exists;

import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.product.common.settlement.PercentageRule;
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

public class PercentageRuleOnlyExistsValidator implements ValidatorWithArg<PercentageRule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PercentageRule> ValidationResult<PercentageRule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("paymentPercent", ExistenceChecker.isSet((BigDecimal) o.getPaymentPercent()))
				.put("notionalAmountReference", ExistenceChecker.isSet((ReferenceWithMetaMoney) o.getNotionalAmountReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PercentageRule", ValidationType.ONLY_EXISTS, "PercentageRule", path, "");
		}
		return failure("PercentageRule", ValidationType.ONLY_EXISTS, "PercentageRule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
