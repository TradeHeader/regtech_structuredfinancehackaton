package cdm.product.asset.floatingrate.validation.exists;

import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
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

public class FloatingRateSettingDetailsOnlyExistsValidator implements ValidatorWithArg<FloatingRateSettingDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateSettingDetails> ValidationResult<FloatingRateSettingDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationDetails", ExistenceChecker.isSet((CalculatedRateDetails) o.getCalculationDetails()))
				.put("observationDate", ExistenceChecker.isSet((Date) o.getObservationDate()))
				.put("resetDate", ExistenceChecker.isSet((Date) o.getResetDate()))
				.put("floatingRate", ExistenceChecker.isSet((BigDecimal) o.getFloatingRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateSettingDetails", ValidationType.ONLY_EXISTS, "FloatingRateSettingDetails", path, "");
		}
		return failure("FloatingRateSettingDetails", ValidationType.ONLY_EXISTS, "FloatingRateSettingDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
