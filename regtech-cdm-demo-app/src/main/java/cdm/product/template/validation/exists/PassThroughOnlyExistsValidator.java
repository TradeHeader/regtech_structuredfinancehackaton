package cdm.product.template.validation.exists;

import cdm.product.template.PassThrough;
import cdm.product.template.PassThroughItem;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PassThroughOnlyExistsValidator implements ValidatorWithArg<PassThrough, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PassThrough> ValidationResult<PassThrough> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("passThroughItem", ExistenceChecker.isSet((List<? extends PassThroughItem>) o.getPassThroughItem()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PassThrough", ValidationType.ONLY_EXISTS, "PassThrough", path, "");
		}
		return failure("PassThrough", ValidationType.ONLY_EXISTS, "PassThrough", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
