package cdm.observable.asset.calculatedrate.validation.exists;

import cdm.observable.asset.calculatedrate.CalculatedRateObservations;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CalculatedRateObservationsOnlyExistsValidator implements ValidatorWithArg<CalculatedRateObservations, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculatedRateObservations> ValidationResult<CalculatedRateObservations> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("observationDates", ExistenceChecker.isSet((List<Date>) o.getObservationDates()))
				.put("weights", ExistenceChecker.isSet((List<BigDecimal>) o.getWeights()))
				.put("observedRates", ExistenceChecker.isSet((List<BigDecimal>) o.getObservedRates()))
				.put("processedRates", ExistenceChecker.isSet((List<BigDecimal>) o.getProcessedRates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculatedRateObservations", ValidationType.ONLY_EXISTS, "CalculatedRateObservations", path, "");
		}
		return failure("CalculatedRateObservations", ValidationType.ONLY_EXISTS, "CalculatedRateObservations", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
