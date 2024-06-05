package cdm.observable.asset.validation.exists;

import cdm.observable.asset.QuotationStyleEnum;
import cdm.observable.asset.TransactedPrice;
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

public class TransactedPriceOnlyExistsValidator implements ValidatorWithArg<TransactedPrice, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TransactedPrice> ValidationResult<TransactedPrice> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("marketFixedRate", ExistenceChecker.isSet((BigDecimal) o.getMarketFixedRate()))
				.put("initialPoints", ExistenceChecker.isSet((BigDecimal) o.getInitialPoints()))
				.put("marketPrice", ExistenceChecker.isSet((BigDecimal) o.getMarketPrice()))
				.put("quotationStyle", ExistenceChecker.isSet((QuotationStyleEnum) o.getQuotationStyle()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TransactedPrice", ValidationType.ONLY_EXISTS, "TransactedPrice", path, "");
		}
		return failure("TransactedPrice", ValidationType.ONLY_EXISTS, "TransactedPrice", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
