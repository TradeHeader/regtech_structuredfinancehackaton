package cdm.observable.event.validation.exists;

import cdm.base.datetime.TimeZone;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.Observable;
import cdm.observable.event.DeterminationMethodology;
import cdm.observable.event.ObservationIdentifier;
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

public class ObservationIdentifierOnlyExistsValidator implements ValidatorWithArg<ObservationIdentifier, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationIdentifier> ValidationResult<ObservationIdentifier> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("observable", ExistenceChecker.isSet((Observable) o.getObservable()))
				.put("observationDate", ExistenceChecker.isSet((Date) o.getObservationDate()))
				.put("observationTime", ExistenceChecker.isSet((TimeZone) o.getObservationTime()))
				.put("informationSource", ExistenceChecker.isSet((InformationSource) o.getInformationSource()))
				.put("determinationMethodology", ExistenceChecker.isSet((DeterminationMethodology) o.getDeterminationMethodology()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationIdentifier", ValidationType.ONLY_EXISTS, "ObservationIdentifier", path, "");
		}
		return failure("ObservationIdentifier", ValidationType.ONLY_EXISTS, "ObservationIdentifier", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
