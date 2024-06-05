package cdm.product.template.validation.exists;

import cdm.base.datetime.RelativeDateOffset;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.product.template.ExerciseFee;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ExerciseFeeOnlyExistsValidator implements ValidatorWithArg<ExerciseFee, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExerciseFee> ValidationResult<ExerciseFee> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getPayer()))
				.put("receiver", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getReceiver()))
				.put("notionalReference", ExistenceChecker.isSet((ReferenceWithMetaMoney) o.getNotionalReference()))
				.put("feeAmount", ExistenceChecker.isSet((BigDecimal) o.getFeeAmount()))
				.put("feeRate", ExistenceChecker.isSet((BigDecimal) o.getFeeRate()))
				.put("feePaymentDate", ExistenceChecker.isSet((RelativeDateOffset) o.getFeePaymentDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExerciseFee", ValidationType.ONLY_EXISTS, "ExerciseFee", path, "");
		}
		return failure("ExerciseFee", ValidationType.ONLY_EXISTS, "ExerciseFee", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
