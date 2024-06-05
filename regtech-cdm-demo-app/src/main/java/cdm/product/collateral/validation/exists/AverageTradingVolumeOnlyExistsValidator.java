package cdm.product.collateral.validation.exists;

import cdm.base.datetime.Period;
import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.AverageTradingVolumeMethodologyEnum;
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

public class AverageTradingVolumeOnlyExistsValidator implements ValidatorWithArg<AverageTradingVolume, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AverageTradingVolume> ValidationResult<AverageTradingVolume> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("period", ExistenceChecker.isSet((Period) o.getPeriod()))
				.put("methodology", ExistenceChecker.isSet((AverageTradingVolumeMethodologyEnum) o.getMethodology()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AverageTradingVolume", ValidationType.ONLY_EXISTS, "AverageTradingVolume", path, "");
		}
		return failure("AverageTradingVolume", ValidationType.ONLY_EXISTS, "AverageTradingVolume", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
