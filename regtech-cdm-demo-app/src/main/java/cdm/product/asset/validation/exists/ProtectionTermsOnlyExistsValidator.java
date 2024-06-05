package cdm.product.asset.validation.exists;

import cdm.base.staticdata.asset.credit.Obligations;
import cdm.observable.event.CreditEvents;
import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.ProtectionTerms;
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

public class ProtectionTermsOnlyExistsValidator implements ValidatorWithArg<ProtectionTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ProtectionTerms> ValidationResult<ProtectionTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("creditEvents", ExistenceChecker.isSet((CreditEvents) o.getCreditEvents()))
				.put("obligations", ExistenceChecker.isSet((Obligations) o.getObligations()))
				.put("floatingAmountEvents", ExistenceChecker.isSet((FloatingAmountEvents) o.getFloatingAmountEvents()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ProtectionTerms", ValidationType.ONLY_EXISTS, "ProtectionTerms", path, "");
		}
		return failure("ProtectionTerms", ValidationType.ONLY_EXISTS, "ProtectionTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
