package cdm.observable.asset.validation.exists;

import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.InformationSource;
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

public class FxSpotRateSourceOnlyExistsValidator implements ValidatorWithArg<FxSpotRateSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxSpotRateSource> ValidationResult<FxSpotRateSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("primarySource", ExistenceChecker.isSet((InformationSource) o.getPrimarySource()))
				.put("secondarySource", ExistenceChecker.isSet((InformationSource) o.getSecondarySource()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxSpotRateSource", ValidationType.ONLY_EXISTS, "FxSpotRateSource", path, "");
		}
		return failure("FxSpotRateSource", ValidationType.ONLY_EXISTS, "FxSpotRateSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
