package cdm.product.template.validation.exists;

import cdm.product.template.CancelableProvision;
import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.EvergreenProvision;
import cdm.product.template.ExtendibleProvision;
import cdm.product.template.TerminationProvision;
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

public class TerminationProvisionOnlyExistsValidator implements ValidatorWithArg<TerminationProvision, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TerminationProvision> ValidationResult<TerminationProvision> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cancelableProvision", ExistenceChecker.isSet((CancelableProvision) o.getCancelableProvision()))
				.put("earlyTerminationProvision", ExistenceChecker.isSet((EarlyTerminationProvision) o.getEarlyTerminationProvision()))
				.put("evergreenProvision", ExistenceChecker.isSet((EvergreenProvision) o.getEvergreenProvision()))
				.put("extendibleProvision", ExistenceChecker.isSet((ExtendibleProvision) o.getExtendibleProvision()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TerminationProvision", ValidationType.ONLY_EXISTS, "TerminationProvision", path, "");
		}
		return failure("TerminationProvision", ValidationType.ONLY_EXISTS, "TerminationProvision", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
