package cdm.product.common.schedule.validation.exists;

import cdm.product.common.schedule.ObservationDate;
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

public class ObservationDateOnlyExistsValidator implements ValidatorWithArg<ObservationDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationDate> ValidationResult<ObservationDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("unadjustedDate", ExistenceChecker.isSet((Date) o.getUnadjustedDate()))
				.put("adjustedDate", ExistenceChecker.isSet((Date) o.getAdjustedDate()))
				.put("weight", ExistenceChecker.isSet((BigDecimal) o.getWeight()))
				.put("observationReference", ExistenceChecker.isSet((String) o.getObservationReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationDate", ValidationType.ONLY_EXISTS, "ObservationDate", path, "");
		}
		return failure("ObservationDate", ValidationType.ONLY_EXISTS, "ObservationDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
