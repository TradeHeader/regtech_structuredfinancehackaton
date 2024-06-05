package cdm.observable.asset.validation.exists;

import cdm.observable.asset.RateObservation;
import cdm.observable.asset.metafields.ReferenceWithMetaRateObservation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class RateObservationOnlyExistsValidator implements ValidatorWithArg<RateObservation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends RateObservation> ValidationResult<RateObservation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("resetDate", ExistenceChecker.isSet((Date) o.getResetDate()))
				.put("adjustedFixingDate", ExistenceChecker.isSet((Date) o.getAdjustedFixingDate()))
				.put("observedRate", ExistenceChecker.isSet((BigDecimal) o.getObservedRate()))
				.put("treatedRate", ExistenceChecker.isSet((BigDecimal) o.getTreatedRate()))
				.put("observationWeight", ExistenceChecker.isSet((Integer) o.getObservationWeight()))
				.put("rateReference", ExistenceChecker.isSet((ReferenceWithMetaRateObservation) o.getRateReference()))
				.put("forecastRate", ExistenceChecker.isSet((BigDecimal) o.getForecastRate()))
				.put("treatedForecastRate", ExistenceChecker.isSet((BigDecimal) o.getTreatedForecastRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("RateObservation", ValidationType.ONLY_EXISTS, "RateObservation", path, "");
		}
		return failure("RateObservation", ValidationType.ONLY_EXISTS, "RateObservation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
