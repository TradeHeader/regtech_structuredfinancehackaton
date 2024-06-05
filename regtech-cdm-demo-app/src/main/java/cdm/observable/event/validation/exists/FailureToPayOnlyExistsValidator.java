package cdm.observable.event.validation.exists;

import cdm.observable.asset.Money;
import cdm.observable.event.FailureToPay;
import cdm.observable.event.GracePeriodExtension;
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

public class FailureToPayOnlyExistsValidator implements ValidatorWithArg<FailureToPay, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FailureToPay> ValidationResult<FailureToPay> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("applicable", ExistenceChecker.isSet((Boolean) o.getApplicable()))
				.put("gracePeriodExtension", ExistenceChecker.isSet((GracePeriodExtension) o.getGracePeriodExtension()))
				.put("paymentRequirement", ExistenceChecker.isSet((Money) o.getPaymentRequirement()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FailureToPay", ValidationType.ONLY_EXISTS, "FailureToPay", path, "");
		}
		return failure("FailureToPay", ValidationType.ONLY_EXISTS, "FailureToPay", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
