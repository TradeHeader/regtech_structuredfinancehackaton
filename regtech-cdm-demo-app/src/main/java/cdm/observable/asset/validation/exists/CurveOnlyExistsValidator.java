package cdm.observable.asset.validation.exists;

import cdm.observable.asset.Curve;
import cdm.observable.asset.InterestRateCurve;
import cdm.observable.asset.metafields.FieldWithMetaCommodityReferencePriceEnum;
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

public class CurveOnlyExistsValidator implements ValidatorWithArg<Curve, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Curve> ValidationResult<Curve> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("interestRateCurve", ExistenceChecker.isSet((InterestRateCurve) o.getInterestRateCurve()))
				.put("commodityCurve", ExistenceChecker.isSet((FieldWithMetaCommodityReferencePriceEnum) o.getCommodityCurve()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Curve", ValidationType.ONLY_EXISTS, "Curve", path, "");
		}
		return failure("Curve", ValidationType.ONLY_EXISTS, "Curve", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
