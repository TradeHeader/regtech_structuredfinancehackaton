package cdm.observable.asset.validation.exists;

import cdm.observable.asset.FxInformationSource;
import cdm.observable.asset.FxSettlementRateSource;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class FxSettlementRateSourceOnlyExistsValidator implements ValidatorWithArg<FxSettlementRateSource, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FxSettlementRateSource> ValidationResult<FxSettlementRateSource> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("settlementRateOption", ExistenceChecker.isSet((FieldWithMetaString) o.getSettlementRateOption()))
				.put("nonstandardSettlementRate", ExistenceChecker.isSet((FxInformationSource) o.getNonstandardSettlementRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FxSettlementRateSource", ValidationType.ONLY_EXISTS, "FxSettlementRateSource", path, "");
		}
		return failure("FxSettlementRateSource", ValidationType.ONLY_EXISTS, "FxSettlementRateSource", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
