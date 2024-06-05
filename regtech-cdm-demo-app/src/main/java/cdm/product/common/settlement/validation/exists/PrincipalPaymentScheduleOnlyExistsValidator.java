package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.common.settlement.PrincipalPayment;
import cdm.product.common.settlement.PrincipalPaymentSchedule;
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

public class PrincipalPaymentScheduleOnlyExistsValidator implements ValidatorWithArg<PrincipalPaymentSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PrincipalPaymentSchedule> ValidationResult<PrincipalPaymentSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("initialPrincipalPayment", ExistenceChecker.isSet((PrincipalPayment) o.getInitialPrincipalPayment()))
				.put("intermediatePrincipalPayment", ExistenceChecker.isSet((AdjustableRelativeOrPeriodicDates) o.getIntermediatePrincipalPayment()))
				.put("finalPrincipalPayment", ExistenceChecker.isSet((PrincipalPayment) o.getFinalPrincipalPayment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PrincipalPaymentSchedule", ValidationType.ONLY_EXISTS, "PrincipalPaymentSchedule", path, "");
		}
		return failure("PrincipalPaymentSchedule", ValidationType.ONLY_EXISTS, "PrincipalPaymentSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
