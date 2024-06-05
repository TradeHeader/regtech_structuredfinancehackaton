package cdm.event.workflow.validation.exists;

import cdm.event.workflow.Workflow;
import cdm.event.workflow.WorkflowStep;
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

public class WorkflowOnlyExistsValidator implements ValidatorWithArg<Workflow, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Workflow> ValidationResult<Workflow> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("steps", ExistenceChecker.isSet((List<? extends WorkflowStep>) o.getSteps()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Workflow", ValidationType.ONLY_EXISTS, "Workflow", path, "");
		}
		return failure("Workflow", ValidationType.ONLY_EXISTS, "Workflow", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
