package cdm.event.workflow.validation.exists;

import cdm.event.workflow.PartyCustomisedWorkflow;
import cdm.event.workflow.WarehouseIdentityEnum;
import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.WorkflowStatusEnum;
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

public class WorkflowStateOnlyExistsValidator implements ValidatorWithArg<WorkflowState, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends WorkflowState> ValidationResult<WorkflowState> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("workflowStatus", ExistenceChecker.isSet((WorkflowStatusEnum) o.getWorkflowStatus()))
				.put("comment", ExistenceChecker.isSet((String) o.getComment()))
				.put("partyCustomisedWorkflow", ExistenceChecker.isSet((List<? extends PartyCustomisedWorkflow>) o.getPartyCustomisedWorkflow()))
				.put("warehouseIdentity", ExistenceChecker.isSet((WarehouseIdentityEnum) o.getWarehouseIdentity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("WorkflowState", ValidationType.ONLY_EXISTS, "WorkflowState", path, "");
		}
		return failure("WorkflowState", ValidationType.ONLY_EXISTS, "WorkflowState", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
