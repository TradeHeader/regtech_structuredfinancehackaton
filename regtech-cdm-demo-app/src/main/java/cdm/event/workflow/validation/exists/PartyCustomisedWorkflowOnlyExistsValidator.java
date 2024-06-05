package cdm.event.workflow.validation.exists;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.workflow.CustomisedWorkflow;
import cdm.event.workflow.PartyCustomisedWorkflow;
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

public class PartyCustomisedWorkflowOnlyExistsValidator implements ValidatorWithArg<PartyCustomisedWorkflow, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PartyCustomisedWorkflow> ValidationResult<PartyCustomisedWorkflow> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("partyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getPartyReference()))
				.put("partyName", ExistenceChecker.isSet((String) o.getPartyName()))
				.put("customisedWorkflow", ExistenceChecker.isSet((List<? extends CustomisedWorkflow>) o.getCustomisedWorkflow()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PartyCustomisedWorkflow", ValidationType.ONLY_EXISTS, "PartyCustomisedWorkflow", path, "");
		}
		return failure("PartyCustomisedWorkflow", ValidationType.ONLY_EXISTS, "PartyCustomisedWorkflow", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
