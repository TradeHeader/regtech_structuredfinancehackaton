package cdm.product.template.validation.exists;

import cdm.observable.asset.Price;
import cdm.observable.asset.ReferenceSwapCurve;
import cdm.product.asset.metafields.ReferenceWithMetaFixedRateSpecification;
import cdm.product.template.AveragingStrikeFeature;
import cdm.product.template.OptionStrike;
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

public class OptionStrikeOnlyExistsValidator implements ValidatorWithArg<OptionStrike, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OptionStrike> ValidationResult<OptionStrike> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("strikePrice", ExistenceChecker.isSet((Price) o.getStrikePrice()))
				.put("strikeReference", ExistenceChecker.isSet((ReferenceWithMetaFixedRateSpecification) o.getStrikeReference()))
				.put("referenceSwapCurve", ExistenceChecker.isSet((ReferenceSwapCurve) o.getReferenceSwapCurve()))
				.put("averagingStrikeFeature", ExistenceChecker.isSet((AveragingStrikeFeature) o.getAveragingStrikeFeature()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OptionStrike", ValidationType.ONLY_EXISTS, "OptionStrike", path, "");
		}
		return failure("OptionStrike", ValidationType.ONLY_EXISTS, "OptionStrike", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
