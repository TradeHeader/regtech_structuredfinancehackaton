package cdm.product.asset.validation.exists;

import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.product.asset.InterestShortFall;
import cdm.product.asset.InterestShortfallCapEnum;
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

public class InterestShortFallOnlyExistsValidator implements ValidatorWithArg<InterestShortFall, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InterestShortFall> ValidationResult<InterestShortFall> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("interestShortfallCap", ExistenceChecker.isSet((InterestShortfallCapEnum) o.getInterestShortfallCap()))
				.put("compounding", ExistenceChecker.isSet((Boolean) o.getCompounding()))
				.put("rateSource", ExistenceChecker.isSet((FieldWithMetaFloatingRateIndexEnum) o.getRateSource()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InterestShortFall", ValidationType.ONLY_EXISTS, "InterestShortFall", path, "");
		}
		return failure("InterestShortFall", ValidationType.ONLY_EXISTS, "InterestShortFall", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
