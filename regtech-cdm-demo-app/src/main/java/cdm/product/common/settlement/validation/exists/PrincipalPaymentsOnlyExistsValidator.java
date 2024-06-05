package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.PrincipalPaymentSchedule;
import cdm.product.common.settlement.PrincipalPayments;
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

public class PrincipalPaymentsOnlyExistsValidator implements ValidatorWithArg<PrincipalPayments, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PrincipalPayments> ValidationResult<PrincipalPayments> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("initialPayment", ExistenceChecker.isSet((Boolean) o.getInitialPayment()))
				.put("finalPayment", ExistenceChecker.isSet((Boolean) o.getFinalPayment()))
				.put("intermediatePayment", ExistenceChecker.isSet((Boolean) o.getIntermediatePayment()))
				.put("varyingLegNotionalCurrency", ExistenceChecker.isSet((List<String>) o.getVaryingLegNotionalCurrency()))
				.put("principalPaymentSchedule", ExistenceChecker.isSet((PrincipalPaymentSchedule) o.getPrincipalPaymentSchedule()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PrincipalPayments", ValidationType.ONLY_EXISTS, "PrincipalPayments", path, "");
		}
		return failure("PrincipalPayments", ValidationType.ONLY_EXISTS, "PrincipalPayments", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
