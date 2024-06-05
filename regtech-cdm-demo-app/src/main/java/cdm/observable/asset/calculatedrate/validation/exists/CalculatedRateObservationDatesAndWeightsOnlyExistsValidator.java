package cdm.observable.asset.calculatedrate.validation.exists;

import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
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

public class CalculatedRateObservationDatesAndWeightsOnlyExistsValidator implements ValidatorWithArg<CalculatedRateObservationDatesAndWeights, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculatedRateObservationDatesAndWeights> ValidationResult<CalculatedRateObservationDatesAndWeights> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("observationDates", ExistenceChecker.isSet((List<Date>) o.getObservationDates()))
				.put("weights", ExistenceChecker.isSet((List<BigDecimal>) o.getWeights()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculatedRateObservationDatesAndWeights", ValidationType.ONLY_EXISTS, "CalculatedRateObservationDatesAndWeights", path, "");
		}
		return failure("CalculatedRateObservationDatesAndWeights", ValidationType.ONLY_EXISTS, "CalculatedRateObservationDatesAndWeights", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
