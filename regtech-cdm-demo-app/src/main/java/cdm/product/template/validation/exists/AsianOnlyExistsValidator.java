package cdm.product.template.validation.exists;

import cdm.product.common.schedule.AveragingPeriod;
import cdm.product.template.Asian;
import cdm.product.template.AveragingInOutEnum;
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

public class AsianOnlyExistsValidator implements ValidatorWithArg<Asian, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Asian> ValidationResult<Asian> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("averagingInOut", ExistenceChecker.isSet((AveragingInOutEnum) o.getAveragingInOut()))
				.put("strikeFactor", ExistenceChecker.isSet((BigDecimal) o.getStrikeFactor()))
				.put("averagingPeriodIn", ExistenceChecker.isSet((AveragingPeriod) o.getAveragingPeriodIn()))
				.put("averagingPeriodOut", ExistenceChecker.isSet((AveragingPeriod) o.getAveragingPeriodOut()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Asian", ValidationType.ONLY_EXISTS, "Asian", path, "");
		}
		return failure("Asian", ValidationType.ONLY_EXISTS, "Asian", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
