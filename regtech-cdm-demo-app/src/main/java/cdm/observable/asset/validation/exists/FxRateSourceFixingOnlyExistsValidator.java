package cdm.observable.asset.validation.exists;

import cdm.base.datetime.AdjustableDate;
import cdm.observable.asset.FxRateSourceFixing;
import cdm.observable.asset.FxSettlementRateSource;
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

public class FxRateSourceFixingOnlyExistsValidator implements ValidatorWithArg<FxRateSourceFixing, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxRateSourceFixing> ValidationResult<FxRateSourceFixing> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("settlementRateSource", ExistenceChecker.isSet((FxSettlementRateSource) o.getSettlementRateSource()))
				.put("fixingDate", ExistenceChecker.isSet((AdjustableDate) o.getFixingDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxRateSourceFixing", ValidationType.ONLY_EXISTS, "FxRateSourceFixing", path, "");
		}
		return failure("FxRateSourceFixing", ValidationType.ONLY_EXISTS, "FxRateSourceFixing", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
