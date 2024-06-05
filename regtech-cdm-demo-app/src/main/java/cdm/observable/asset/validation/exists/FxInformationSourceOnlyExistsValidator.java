package cdm.observable.asset.validation.exists;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.FxInformationSource;
import cdm.observable.asset.metafields.FieldWithMetaInformationProviderEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class FxInformationSourceOnlyExistsValidator implements ValidatorWithArg<FxInformationSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxInformationSource> ValidationResult<FxInformationSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("sourceProvider", ExistenceChecker.isSet((FieldWithMetaInformationProviderEnum) o.getSourceProvider()))
				.put("sourcePage", ExistenceChecker.isSet((FieldWithMetaString) o.getSourcePage()))
				.put("sourcePageHeading", ExistenceChecker.isSet((String) o.getSourcePageHeading()))
				.put("fixingTime", ExistenceChecker.isSet((BusinessCenterTime) o.getFixingTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxInformationSource", ValidationType.ONLY_EXISTS, "FxInformationSource", path, "");
		}
		return failure("FxInformationSource", ValidationType.ONLY_EXISTS, "FxInformationSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
