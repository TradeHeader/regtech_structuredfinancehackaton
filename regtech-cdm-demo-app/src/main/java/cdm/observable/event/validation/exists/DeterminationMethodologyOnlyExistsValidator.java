package cdm.observable.event.validation.exists;

import cdm.base.math.AveragingCalculationMethodEnum;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.event.DeterminationMethodology;
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

public class DeterminationMethodologyOnlyExistsValidator implements ValidatorWithArg<DeterminationMethodology, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DeterminationMethodology> ValidationResult<DeterminationMethodology> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("determinationMethod", ExistenceChecker.isSet((DeterminationMethodEnum) o.getDeterminationMethod()))
				.put("averagingMethod", ExistenceChecker.isSet((AveragingCalculationMethodEnum) o.getAveragingMethod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DeterminationMethodology", ValidationType.ONLY_EXISTS, "DeterminationMethodology", path, "");
		}
		return failure("DeterminationMethodology", ValidationType.ONLY_EXISTS, "DeterminationMethodology", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
