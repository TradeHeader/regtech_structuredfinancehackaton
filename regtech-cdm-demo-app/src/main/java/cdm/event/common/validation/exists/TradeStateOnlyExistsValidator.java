package cdm.event.common.validation.exists;

import cdm.event.common.ObservationEvent;
import cdm.event.common.Reset;
import cdm.event.common.State;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.TransferState;
import cdm.event.common.Valuation;
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

public class TradeStateOnlyExistsValidator implements ValidatorWithArg<TradeState, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TradeState> ValidationResult<TradeState> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("trade", ExistenceChecker.isSet((Trade) o.getTrade()))
				.put("state", ExistenceChecker.isSet((State) o.getState()))
				.put("resetHistory", ExistenceChecker.isSet((List<? extends Reset>) o.getResetHistory()))
				.put("transferHistory", ExistenceChecker.isSet((List<? extends TransferState>) o.getTransferHistory()))
				.put("observationHistory", ExistenceChecker.isSet((List<? extends ObservationEvent>) o.getObservationHistory()))
				.put("valuationHistory", ExistenceChecker.isSet((List<? extends Valuation>) o.getValuationHistory()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TradeState", ValidationType.ONLY_EXISTS, "TradeState", path, "");
		}
		return failure("TradeState", ValidationType.ONLY_EXISTS, "TradeState", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
