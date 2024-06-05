package cdm.product.asset.validation.exists;

import cdm.base.datetime.metafields.ReferenceWithMetaAdjustableOrRelativeDate;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendPaymentDate;
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

public class DividendPaymentDateOnlyExistsValidator implements ValidatorWithArg<DividendPaymentDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendPaymentDate> ValidationResult<DividendPaymentDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dividendDateReference", ExistenceChecker.isSet((DividendDateReference) o.getDividendDateReference()))
				.put("dividendDate", ExistenceChecker.isSet((ReferenceWithMetaAdjustableOrRelativeDate) o.getDividendDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendPaymentDate", ValidationType.ONLY_EXISTS, "DividendPaymentDate", path, "");
		}
		return failure("DividendPaymentDate", ValidationType.ONLY_EXISTS, "DividendPaymentDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
