package cdm.product.asset.validation.exists;

import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.DividendCurrency;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.ReferenceWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class DividendCurrencyOnlyExistsValidator implements ValidatorWithArg<DividendCurrency, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendCurrency> ValidationResult<DividendCurrency> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("currency", ExistenceChecker.isSet((FieldWithMetaString) o.getCurrency()))
				.put("determinationMethod", ExistenceChecker.isSet((DeterminationMethodEnum) o.getDeterminationMethod()))
				.put("currencyReference", ExistenceChecker.isSet((ReferenceWithMetaString) o.getCurrencyReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendCurrency", ValidationType.ONLY_EXISTS, "DividendCurrency", path, "");
		}
		return failure("DividendCurrency", ValidationType.ONLY_EXISTS, "DividendCurrency", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
