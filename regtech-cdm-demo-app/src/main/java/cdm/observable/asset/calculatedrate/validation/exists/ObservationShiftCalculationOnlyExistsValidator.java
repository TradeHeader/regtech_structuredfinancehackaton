package cdm.observable.asset.calculatedrate.validation.exists;

import cdm.base.datetime.BusinessCenters;
import cdm.observable.asset.calculatedrate.ObservationPeriodDatesEnum;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
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

public class ObservationShiftCalculationOnlyExistsValidator implements ValidatorWithArg<ObservationShiftCalculation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationShiftCalculation> ValidationResult<ObservationShiftCalculation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("offsetDays", ExistenceChecker.isSet((Integer) o.getOffsetDays()))
				.put("calculationBase", ExistenceChecker.isSet((ObservationPeriodDatesEnum) o.getCalculationBase()))
				.put("additionalBusinessDays", ExistenceChecker.isSet((BusinessCenters) o.getAdditionalBusinessDays()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationShiftCalculation", ValidationType.ONLY_EXISTS, "ObservationShiftCalculation", path, "");
		}
		return failure("ObservationShiftCalculation", ValidationType.ONLY_EXISTS, "ObservationShiftCalculation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
