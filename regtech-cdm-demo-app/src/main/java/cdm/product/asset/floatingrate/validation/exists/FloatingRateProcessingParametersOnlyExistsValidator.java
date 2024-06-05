package cdm.product.asset.floatingrate.validation.exists;

import cdm.base.math.Rounding;
import cdm.observable.asset.Price;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
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

public class FloatingRateProcessingParametersOnlyExistsValidator implements ValidatorWithArg<FloatingRateProcessingParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateProcessingParameters> ValidationResult<FloatingRateProcessingParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("initialRate", ExistenceChecker.isSet((Price) o.getInitialRate()))
				.put("multiplier", ExistenceChecker.isSet((BigDecimal) o.getMultiplier()))
				.put("spread", ExistenceChecker.isSet((BigDecimal) o.getSpread()))
				.put("treatment", ExistenceChecker.isSet((RateTreatmentEnum) o.getTreatment()))
				.put("capRate", ExistenceChecker.isSet((BigDecimal) o.getCapRate()))
				.put("floorRate", ExistenceChecker.isSet((BigDecimal) o.getFloorRate()))
				.put("rounding", ExistenceChecker.isSet((Rounding) o.getRounding()))
				.put("negativeTreatment", ExistenceChecker.isSet((NegativeInterestRateTreatmentEnum) o.getNegativeTreatment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateProcessingParameters", ValidationType.ONLY_EXISTS, "FloatingRateProcessingParameters", path, "");
		}
		return failure("FloatingRateProcessingParameters", ValidationType.ONLY_EXISTS, "FloatingRateProcessingParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
