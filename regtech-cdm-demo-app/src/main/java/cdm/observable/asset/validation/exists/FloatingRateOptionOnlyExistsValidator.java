package cdm.observable.asset.validation.exists;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaInflationRateIndexEnum;
import cdm.observable.asset.FloatingRateOption;
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

public class FloatingRateOptionOnlyExistsValidator implements ValidatorWithArg<FloatingRateOption, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateOption> ValidationResult<FloatingRateOption> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("floatingRateIndex", ExistenceChecker.isSet((FieldWithMetaFloatingRateIndexEnum) o.getFloatingRateIndex()))
				.put("inflationRateIndex", ExistenceChecker.isSet((FieldWithMetaInflationRateIndexEnum) o.getInflationRateIndex()))
				.put("indexTenor", ExistenceChecker.isSet((Period) o.getIndexTenor()))
				.put("indexReferenceInformation", ExistenceChecker.isSet((IndexReferenceInformation) o.getIndexReferenceInformation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateOption", ValidationType.ONLY_EXISTS, "FloatingRateOption", path, "");
		}
		return failure("FloatingRateOption", ValidationType.ONLY_EXISTS, "FloatingRateOption", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
