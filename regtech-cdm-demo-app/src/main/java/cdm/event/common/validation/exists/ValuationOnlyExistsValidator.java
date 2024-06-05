package cdm.event.common.validation.exists;

import cdm.event.common.PriceTimingEnum;
import cdm.event.common.Valuation;
import cdm.event.common.ValuationSourceEnum;
import cdm.event.common.ValuationTypeEnum;
import cdm.observable.asset.Money;
import cdm.observable.asset.Price;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ValuationOnlyExistsValidator implements ValidatorWithArg<Valuation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Valuation> ValidationResult<Valuation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("amount", ExistenceChecker.isSet((Money) o.getAmount()))
				.put("timestamp", ExistenceChecker.isSet((ZonedDateTime) o.getTimestamp()))
				.put("method", ExistenceChecker.isSet((ValuationTypeEnum) o.getMethod()))
				.put("source", ExistenceChecker.isSet((ValuationSourceEnum) o.getSource()))
				.put("delta", ExistenceChecker.isSet((BigDecimal) o.getDelta()))
				.put("valuationTiming", ExistenceChecker.isSet((PriceTimingEnum) o.getValuationTiming()))
				.put("priceComponent", ExistenceChecker.isSet((Price) o.getPriceComponent()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Valuation", ValidationType.ONLY_EXISTS, "Valuation", path, "");
		}
		return failure("Valuation", ValidationType.ONLY_EXISTS, "Valuation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
