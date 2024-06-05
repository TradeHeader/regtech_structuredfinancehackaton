package cdm.event.common.validation.exists;

import cdm.base.datetime.TimeZone;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.event.common.Trade;
import cdm.event.common.TradePricingReport;
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

public class TradePricingReportOnlyExistsValidator implements ValidatorWithArg<TradePricingReport, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TradePricingReport> ValidationResult<TradePricingReport> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("trade", ExistenceChecker.isSet((Trade) o.getTrade()))
				.put("pricingTime", ExistenceChecker.isSet((TimeZone) o.getPricingTime()))
				.put("discountingIndex", ExistenceChecker.isSet((FloatingRateIndexEnum) o.getDiscountingIndex()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TradePricingReport", ValidationType.ONLY_EXISTS, "TradePricingReport", path, "");
		}
		return failure("TradePricingReport", ValidationType.ONLY_EXISTS, "TradePricingReport", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
