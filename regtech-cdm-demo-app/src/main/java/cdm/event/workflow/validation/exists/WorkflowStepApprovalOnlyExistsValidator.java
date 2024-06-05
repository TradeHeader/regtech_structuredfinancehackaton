package cdm.event.workflow.validation.exists;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.WorkflowStepApproval;
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

public class WorkflowStepApprovalOnlyExistsValidator implements ValidatorWithArg<WorkflowStepApproval, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends WorkflowStepApproval> ValidationResult<WorkflowStepApproval> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("approved", ExistenceChecker.isSet((Boolean) o.getApproved()))
				.put("party", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getParty()))
				.put("rejectedReason", ExistenceChecker.isSet((String) o.getRejectedReason()))
				.put("timestamp", ExistenceChecker.isSet((EventTimestamp) o.getTimestamp()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("WorkflowStepApproval", ValidationType.ONLY_EXISTS, "WorkflowStepApproval", path, "");
		}
		return failure("WorkflowStepApproval", ValidationType.ONLY_EXISTS, "WorkflowStepApproval", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
