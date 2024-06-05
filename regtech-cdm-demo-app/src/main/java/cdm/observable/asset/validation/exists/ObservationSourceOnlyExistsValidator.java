package cdm.observable.asset.validation.exists;

import cdm.observable.asset.Curve;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.ObservationSource;
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

public class ObservationSourceOnlyExistsValidator implements ValidatorWithArg<ObservationSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationSource> ValidationResult<ObservationSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("curve", ExistenceChecker.isSet((Curve) o.getCurve()))
				.put("informationSource", ExistenceChecker.isSet((InformationSource) o.getInformationSource()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationSource", ValidationType.ONLY_EXISTS, "ObservationSource", path, "");
		}
		return failure("ObservationSource", ValidationType.ONLY_EXISTS, "ObservationSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
