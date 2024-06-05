package cdm.product.template.validation.exists;

import cdm.base.datetime.RelativeDateOffset;
import cdm.base.math.Schedule;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.product.common.schedule.AmountSchedule;
import cdm.product.template.ExerciseFeeSchedule;
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

public class ExerciseFeeScheduleOnlyExistsValidator implements ValidatorWithArg<ExerciseFeeSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExerciseFeeSchedule> ValidationResult<ExerciseFeeSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getPayer()))
				.put("receiver", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getReceiver()))
				.put("notionalReference", ExistenceChecker.isSet((ReferenceWithMetaMoney) o.getNotionalReference()))
				.put("feeAmountSchedule", ExistenceChecker.isSet((AmountSchedule) o.getFeeAmountSchedule()))
				.put("feeRateSchedule", ExistenceChecker.isSet((Schedule) o.getFeeRateSchedule()))
				.put("feePaymentDate", ExistenceChecker.isSet((RelativeDateOffset) o.getFeePaymentDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExerciseFeeSchedule", ValidationType.ONLY_EXISTS, "ExerciseFeeSchedule", path, "");
		}
		return failure("ExerciseFeeSchedule", ValidationType.ONLY_EXISTS, "ExerciseFeeSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
