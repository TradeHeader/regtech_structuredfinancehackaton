package cdm.event.common.validation.exists;

import cdm.event.common.Exposure;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState;
import cdm.observable.asset.Money;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ExposureOnlyExistsValidator implements ValidatorWithArg<Exposure, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Exposure> ValidationResult<Exposure> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("tradePortfolio", ExistenceChecker.isSet((ReferenceWithMetaPortfolioState) o.getTradePortfolio()))
				.put("aggregateValue", ExistenceChecker.isSet((Money) o.getAggregateValue()))
				.put("calculationDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getCalculationDateTime()))
				.put("valuationDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getValuationDateTime()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Exposure", ValidationType.ONLY_EXISTS, "Exposure", path, "");
		}
		return failure("Exposure", ValidationType.ONLY_EXISTS, "Exposure", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}