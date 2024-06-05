package cdm.observable.asset.validation.exists;

import cdm.observable.asset.BondPriceAndYieldModel;
import cdm.observable.asset.CleanOrDirtyPrice;
import cdm.observable.asset.RelativePrice;
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

public class BondPriceAndYieldModelOnlyExistsValidator implements ValidatorWithArg<BondPriceAndYieldModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BondPriceAndYieldModel> ValidationResult<BondPriceAndYieldModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cleanOrDirtyPrice", ExistenceChecker.isSet((CleanOrDirtyPrice) o.getCleanOrDirtyPrice()))
				.put("relativePrice", ExistenceChecker.isSet((RelativePrice) o.getRelativePrice()))
				.put("yieldToMaturity", ExistenceChecker.isSet((BigDecimal) o.getYieldToMaturity()))
				.put("inflationFactor", ExistenceChecker.isSet((BigDecimal) o.getInflationFactor()))
				.put("allInPrice", ExistenceChecker.isSet((BigDecimal) o.getAllInPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BondPriceAndYieldModel", ValidationType.ONLY_EXISTS, "BondPriceAndYieldModel", path, "");
		}
		return failure("BondPriceAndYieldModel", ValidationType.ONLY_EXISTS, "BondPriceAndYieldModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
