package cdm.product.template.validation.exists;

import cdm.product.template.EarlyTerminationEvent;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class EarlyTerminationEventOnlyExistsValidator implements ValidatorWithArg<EarlyTerminationEvent, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EarlyTerminationEvent> ValidationResult<EarlyTerminationEvent> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("adjustedExerciseDate", ExistenceChecker.isSet((Date) o.getAdjustedExerciseDate()))
				.put("adjustedEarlyTerminationDate", ExistenceChecker.isSet((Date) o.getAdjustedEarlyTerminationDate()))
				.put("adjustedCashSettlementValuationDate", ExistenceChecker.isSet((Date) o.getAdjustedCashSettlementValuationDate()))
				.put("adjustedCashSettlementPaymentDate", ExistenceChecker.isSet((Date) o.getAdjustedCashSettlementPaymentDate()))
				.put("adjustedExerciseFeePaymentDate", ExistenceChecker.isSet((Date) o.getAdjustedExerciseFeePaymentDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EarlyTerminationEvent", ValidationType.ONLY_EXISTS, "EarlyTerminationEvent", path, "");
		}
		return failure("EarlyTerminationEvent", ValidationType.ONLY_EXISTS, "EarlyTerminationEvent", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
