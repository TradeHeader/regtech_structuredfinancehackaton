package cdm.event.workflow.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.common.CounterpartyPositionBusinessEvent;
import cdm.event.common.Lineage;
import cdm.event.workflow.CreditLimitInformation;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.MessageInformation;
import cdm.event.workflow.WorkflowState;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStepApproval;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
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

public class WorkflowStepOnlyExistsValidator implements ValidatorWithArg<WorkflowStep, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends WorkflowStep> ValidationResult<WorkflowStep> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("businessEvent", ExistenceChecker.isSet((BusinessEvent) o.getBusinessEvent()))
				.put("counterpartyPositionBusinessEvent", ExistenceChecker.isSet((CounterpartyPositionBusinessEvent) o.getCounterpartyPositionBusinessEvent()))
				.put("proposedEvent", ExistenceChecker.isSet((EventInstruction) o.getProposedEvent()))
				.put("rejected", ExistenceChecker.isSet((Boolean) o.getRejected()))
				.put("approval", ExistenceChecker.isSet((List<? extends WorkflowStepApproval>) o.getApproval()))
				.put("previousWorkflowStep", ExistenceChecker.isSet((ReferenceWithMetaWorkflowStep) o.getPreviousWorkflowStep()))
				.put("nextEvent", ExistenceChecker.isSet((EventInstruction) o.getNextEvent()))
				.put("messageInformation", ExistenceChecker.isSet((MessageInformation) o.getMessageInformation()))
				.put("timestamp", ExistenceChecker.isSet((List<? extends EventTimestamp>) o.getTimestamp()))
				.put("eventIdentifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getEventIdentifier()))
				.put("action", ExistenceChecker.isSet((ActionEnum) o.getAction()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("account", ExistenceChecker.isSet((List<? extends Account>) o.getAccount()))
				.put("lineage", ExistenceChecker.isSet((Lineage) o.getLineage()))
				.put("creditLimitInformation", ExistenceChecker.isSet((CreditLimitInformation) o.getCreditLimitInformation()))
				.put("workflowState", ExistenceChecker.isSet((WorkflowState) o.getWorkflowState()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("WorkflowStep", ValidationType.ONLY_EXISTS, "WorkflowStep", path, "");
		}
		return failure("WorkflowStep", ValidationType.ONLY_EXISTS, "WorkflowStep", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
