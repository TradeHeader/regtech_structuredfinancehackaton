package cdm.product.template.validation.exists;

import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.FxRate;
import cdm.observable.asset.FxSpotRateSource;
import cdm.product.template.Quanto;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class QuantoOnlyExistsValidator implements ValidatorWithArg<Quanto, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Quanto> ValidationResult<Quanto> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fxRate", ExistenceChecker.isSet((List<? extends FxRate>) o.getFxRate()))
				.put("fxSpotRateSource", ExistenceChecker.isSet((FxSpotRateSource) o.getFxSpotRateSource()))
				.put("fixingTime", ExistenceChecker.isSet((BusinessCenterTime) o.getFixingTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Quanto", ValidationType.ONLY_EXISTS, "Quanto", path, "");
		}
		return failure("Quanto", ValidationType.ONLY_EXISTS, "Quanto", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
