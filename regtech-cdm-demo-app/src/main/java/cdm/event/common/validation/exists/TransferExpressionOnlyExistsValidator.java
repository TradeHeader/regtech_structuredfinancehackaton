package cdm.event.common.validation.exists;

import cdm.event.common.ScheduledTransfer;
import cdm.event.common.TransferExpression;
import cdm.observable.asset.FeeTypeEnum;
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

public class TransferExpressionOnlyExistsValidator implements ValidatorWithArg<TransferExpression, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TransferExpression> ValidationResult<TransferExpression> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("priceTransfer", ExistenceChecker.isSet((FeeTypeEnum) o.getPriceTransfer()))
				.put("scheduledTransfer", ExistenceChecker.isSet((ScheduledTransfer) o.getScheduledTransfer()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TransferExpression", ValidationType.ONLY_EXISTS, "TransferExpression", path, "");
		}
		return failure("TransferExpression", ValidationType.ONLY_EXISTS, "TransferExpression", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
