package cdm.product.asset.validation.exists;

import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FloatingRate;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.SpreadSchedule;
import cdm.product.common.schedule.RateSchedule;
import cdm.product.template.StrikeSchedule;
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

public class FloatingRateOnlyExistsValidator implements ValidatorWithArg<FloatingRate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRate> ValidationResult<FloatingRate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rateOption", ExistenceChecker.isSet((ReferenceWithMetaFloatingRateOption) o.getRateOption()))
				.put("spreadSchedule", ExistenceChecker.isSet((SpreadSchedule) o.getSpreadSchedule()))
				.put("capRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getCapRateSchedule()))
				.put("floorRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getFloorRateSchedule()))
				.put("floatingRateMultiplierSchedule", ExistenceChecker.isSet((RateSchedule) o.getFloatingRateMultiplierSchedule()))
				.put("rateTreatment", ExistenceChecker.isSet((RateTreatmentEnum) o.getRateTreatment()))
				.put("calculationParameters", ExistenceChecker.isSet((FloatingRateCalculationParameters) o.getCalculationParameters()))
				.put("fallbackRate", ExistenceChecker.isSet((FallbackRateParameters) o.getFallbackRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRate", ValidationType.ONLY_EXISTS, "FloatingRate", path, "");
		}
		return failure("FloatingRate", ValidationType.ONLY_EXISTS, "FloatingRate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
