package cdm.event.common.validation.exists;

import cdm.event.common.CounterpartyPositionState;
import cdm.event.common.ObservationEvent;
import cdm.event.common.State;
import cdm.event.common.Valuation;
import cdm.event.position.CounterpartyPosition;
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

public class CounterpartyPositionStateOnlyExistsValidator implements ValidatorWithArg<CounterpartyPositionState, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CounterpartyPositionState> ValidationResult<CounterpartyPositionState> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("counterpartyPosition", ExistenceChecker.isSet((CounterpartyPosition) o.getCounterpartyPosition()))
				.put("state", ExistenceChecker.isSet((State) o.getState()))
				.put("observationHistory", ExistenceChecker.isSet((List<? extends ObservationEvent>) o.getObservationHistory()))
				.put("valuationHistory", ExistenceChecker.isSet((List<? extends Valuation>) o.getValuationHistory()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CounterpartyPositionState", ValidationType.ONLY_EXISTS, "CounterpartyPositionState", path, "");
		}
		return failure("CounterpartyPositionState", ValidationType.ONLY_EXISTS, "CounterpartyPositionState", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
