package cdm.observable.asset.fro.validation.exists;

import cdm.base.staticdata.asset.common.ISOCurrencyCodeEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.observable.asset.fro.FloatingRateIndexIdentification;
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

public class FloatingRateIndexIdentificationOnlyExistsValidator implements ValidatorWithArg<FloatingRateIndexIdentification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateIndexIdentification> ValidationResult<FloatingRateIndexIdentification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("floatingRateIndex", ExistenceChecker.isSet((FieldWithMetaFloatingRateIndexEnum) o.getFloatingRateIndex()))
				.put("currency", ExistenceChecker.isSet((ISOCurrencyCodeEnum) o.getCurrency()))
				.put("froType", ExistenceChecker.isSet((String) o.getFroType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateIndexIdentification", ValidationType.ONLY_EXISTS, "FloatingRateIndexIdentification", path, "");
		}
		return failure("FloatingRateIndexIdentification", ValidationType.ONLY_EXISTS, "FloatingRateIndexIdentification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
