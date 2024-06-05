package cdm.event.common.validation.exists;

import cdm.base.staticdata.party.Party;
import cdm.event.common.BillingInstruction;
import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.BillingSummaryInstruction;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class BillingInstructionOnlyExistsValidator implements ValidatorWithArg<BillingInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BillingInstruction> ValidationResult<BillingInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("sendingParty", ExistenceChecker.isSet((Party) o.getSendingParty()))
				.put("receivingParty", ExistenceChecker.isSet((Party) o.getReceivingParty()))
				.put("billingStartDate", ExistenceChecker.isSet((Date) o.getBillingStartDate()))
				.put("billingEndDate", ExistenceChecker.isSet((Date) o.getBillingEndDate()))
				.put("billingRecordInstruction", ExistenceChecker.isSet((List<? extends BillingRecordInstruction>) o.getBillingRecordInstruction()))
				.put("billingSummary", ExistenceChecker.isSet((List<? extends BillingSummaryInstruction>) o.getBillingSummary()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BillingInstruction", ValidationType.ONLY_EXISTS, "BillingInstruction", path, "");
		}
		return failure("BillingInstruction", ValidationType.ONLY_EXISTS, "BillingInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
