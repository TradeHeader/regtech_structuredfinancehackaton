package cdm.event.common.validation.exists;

import cdm.base.math.Quantity;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.Reset;
import cdm.event.common.TradeState;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
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

public class CalculateTransferInstructionOnlyExistsValidator implements ValidatorWithArg<CalculateTransferInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculateTransferInstruction> ValidationResult<CalculateTransferInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("tradeState", ExistenceChecker.isSet((TradeState) o.getTradeState()))
				.put("payout", ExistenceChecker.isSet((ReferenceWithMetaPayout) o.getPayout()))
				.put("resets", ExistenceChecker.isSet((List<? extends Reset>) o.getResets()))
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("quantity", ExistenceChecker.isSet((Quantity) o.getQuantity()))
				.put("date", ExistenceChecker.isSet((Date) o.getDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculateTransferInstruction", ValidationType.ONLY_EXISTS, "CalculateTransferInstruction", path, "");
		}
		return failure("CalculateTransferInstruction", ValidationType.ONLY_EXISTS, "CalculateTransferInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
