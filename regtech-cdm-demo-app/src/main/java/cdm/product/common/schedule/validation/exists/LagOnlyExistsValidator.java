package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.Offset;
import cdm.product.common.schedule.Lag;
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

public class LagOnlyExistsValidator implements ValidatorWithArg<Lag, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Lag> ValidationResult<Lag> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("lagDuration", ExistenceChecker.isSet((Offset) o.getLagDuration()))
				.put("firstObservationDateOffset", ExistenceChecker.isSet((Offset) o.getFirstObservationDateOffset()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Lag", ValidationType.ONLY_EXISTS, "Lag", path, "");
		}
		return failure("Lag", ValidationType.ONLY_EXISTS, "Lag", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
