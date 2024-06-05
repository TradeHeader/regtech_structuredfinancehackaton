package cdm.event.position.validation.exists;

import cdm.event.common.Lineage;
import cdm.event.position.PortfolioState;
import cdm.event.position.Position;
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

public class PortfolioStateOnlyExistsValidator implements ValidatorWithArg<PortfolioState, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PortfolioState> ValidationResult<PortfolioState> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("positions", ExistenceChecker.isSet((List<? extends Position>) o.getPositions()))
				.put("lineage", ExistenceChecker.isSet((Lineage) o.getLineage()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PortfolioState", ValidationType.ONLY_EXISTS, "PortfolioState", path, "");
		}
		return failure("PortfolioState", ValidationType.ONLY_EXISTS, "PortfolioState", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
