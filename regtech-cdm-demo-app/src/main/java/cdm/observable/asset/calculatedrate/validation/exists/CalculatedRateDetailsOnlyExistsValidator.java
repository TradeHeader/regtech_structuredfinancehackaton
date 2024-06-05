package cdm.observable.asset.calculatedrate.validation.exists;

import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.observable.asset.calculatedrate.CalculatedRateObservations;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CalculatedRateDetailsOnlyExistsValidator implements ValidatorWithArg<CalculatedRateDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculatedRateDetails> ValidationResult<CalculatedRateDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("observations", ExistenceChecker.isSet((CalculatedRateObservations) o.getObservations()))
				.put("weightedRates", ExistenceChecker.isSet((List<BigDecimal>) o.getWeightedRates()))
				.put("growthFactor", ExistenceChecker.isSet((List<BigDecimal>) o.getGrowthFactor()))
				.put("compoundedGrowth", ExistenceChecker.isSet((List<BigDecimal>) o.getCompoundedGrowth()))
				.put("aggregateValue", ExistenceChecker.isSet((BigDecimal) o.getAggregateValue()))
				.put("aggregateWeight", ExistenceChecker.isSet((BigDecimal) o.getAggregateWeight()))
				.put("calculatedRate", ExistenceChecker.isSet((BigDecimal) o.getCalculatedRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculatedRateDetails", ValidationType.ONLY_EXISTS, "CalculatedRateDetails", path, "");
		}
		return failure("CalculatedRateDetails", ValidationType.ONLY_EXISTS, "CalculatedRateDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
