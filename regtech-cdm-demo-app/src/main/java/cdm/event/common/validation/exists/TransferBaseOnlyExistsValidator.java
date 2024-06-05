package cdm.event.common.validation.exists;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.staticdata.identifier.metafields.FieldWithMetaIdentifier;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.TransferBase;
import cdm.observable.asset.Observable;
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

public class TransferBaseOnlyExistsValidator implements ValidatorWithArg<TransferBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TransferBase> ValidationResult<TransferBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifier", ExistenceChecker.isSet((List<? extends FieldWithMetaIdentifier>) o.getIdentifier()))
				.put("quantity", ExistenceChecker.isSet((NonNegativeQuantity) o.getQuantity()))
				.put("observable", ExistenceChecker.isSet((Observable) o.getObservable()))
				.put("payerReceiver", ExistenceChecker.isSet((PartyReferencePayerReceiver) o.getPayerReceiver()))
				.put("settlementDate", ExistenceChecker.isSet((AdjustableOrAdjustedOrRelativeDate) o.getSettlementDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TransferBase", ValidationType.ONLY_EXISTS, "TransferBase", path, "");
		}
		return failure("TransferBase", ValidationType.ONLY_EXISTS, "TransferBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
