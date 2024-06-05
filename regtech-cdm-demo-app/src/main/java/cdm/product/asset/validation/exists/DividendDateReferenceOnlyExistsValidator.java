package cdm.product.asset.validation.exists;

import cdm.base.datetime.Offset;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendDateReferenceEnum;
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

public class DividendDateReferenceOnlyExistsValidator implements ValidatorWithArg<DividendDateReference, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendDateReference> ValidationResult<DividendDateReference> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dateReference", ExistenceChecker.isSet((DividendDateReferenceEnum) o.getDateReference()))
				.put("paymentDateOffset", ExistenceChecker.isSet((Offset) o.getPaymentDateOffset()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendDateReference", ValidationType.ONLY_EXISTS, "DividendDateReference", path, "");
		}
		return failure("DividendDateReference", ValidationType.ONLY_EXISTS, "DividendDateReference", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
