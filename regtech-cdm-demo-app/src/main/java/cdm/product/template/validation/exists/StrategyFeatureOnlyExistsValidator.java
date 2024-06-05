package cdm.product.template.validation.exists;

import cdm.product.template.CalendarSpread;
import cdm.product.template.StrategyFeature;
import cdm.product.template.StrikeSpread;
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

public class StrategyFeatureOnlyExistsValidator implements ValidatorWithArg<StrategyFeature, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends StrategyFeature> ValidationResult<StrategyFeature> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("strikeSpread", ExistenceChecker.isSet((StrikeSpread) o.getStrikeSpread()))
				.put("calendarSpread", ExistenceChecker.isSet((CalendarSpread) o.getCalendarSpread()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("StrategyFeature", ValidationType.ONLY_EXISTS, "StrategyFeature", path, "");
		}
		return failure("StrategyFeature", ValidationType.ONLY_EXISTS, "StrategyFeature", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
