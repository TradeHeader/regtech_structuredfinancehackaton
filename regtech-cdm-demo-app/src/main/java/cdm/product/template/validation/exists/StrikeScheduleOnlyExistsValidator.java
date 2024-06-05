package cdm.product.template.validation.exists;

import cdm.base.staticdata.party.PayerReceiverEnum;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.template.StrikeSchedule;
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

public class StrikeScheduleOnlyExistsValidator implements ValidatorWithArg<StrikeSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends StrikeSchedule> ValidationResult<StrikeSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("price", ExistenceChecker.isSet((ReferenceWithMetaPriceSchedule) o.getPrice()))
				.put("buyer", ExistenceChecker.isSet((PayerReceiverEnum) o.getBuyer()))
				.put("seller", ExistenceChecker.isSet((PayerReceiverEnum) o.getSeller()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("StrikeSchedule", ValidationType.ONLY_EXISTS, "StrikeSchedule", path, "");
		}
		return failure("StrikeSchedule", ValidationType.ONLY_EXISTS, "StrikeSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
