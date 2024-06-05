package cdm.observable.asset.validation.exists;

import cdm.observable.asset.MakeWholeAmount;
import cdm.observable.asset.ReferenceSwapCurve;
import cdm.observable.asset.SwapCurveValuation;
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

public class ReferenceSwapCurveOnlyExistsValidator implements ValidatorWithArg<ReferenceSwapCurve, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ReferenceSwapCurve> ValidationResult<ReferenceSwapCurve> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("swapUnwindValue", ExistenceChecker.isSet((SwapCurveValuation) o.getSwapUnwindValue()))
				.put("makeWholeAmount", ExistenceChecker.isSet((MakeWholeAmount) o.getMakeWholeAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ReferenceSwapCurve", ValidationType.ONLY_EXISTS, "ReferenceSwapCurve", path, "");
		}
		return failure("ReferenceSwapCurve", ValidationType.ONLY_EXISTS, "ReferenceSwapCurve", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
