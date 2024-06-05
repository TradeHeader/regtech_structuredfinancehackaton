package cdm.product.collateral.validation.exists;

import cdm.base.math.MoneyRange;
import cdm.base.math.NumberRange;
import cdm.product.collateral.ConcentrationLimit;
import cdm.product.collateral.ConcentrationLimitCriteria;
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

public class ConcentrationLimitOnlyExistsValidator implements ValidatorWithArg<ConcentrationLimit, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ConcentrationLimit> ValidationResult<ConcentrationLimit> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("concentrationLimitCriteria", ExistenceChecker.isSet((List<? extends ConcentrationLimitCriteria>) o.getConcentrationLimitCriteria()))
				.put("valueLimit", ExistenceChecker.isSet((MoneyRange) o.getValueLimit()))
				.put("percentageLimit", ExistenceChecker.isSet((NumberRange) o.getPercentageLimit()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ConcentrationLimit", ValidationType.ONLY_EXISTS, "ConcentrationLimit", path, "");
		}
		return failure("ConcentrationLimit", ValidationType.ONLY_EXISTS, "ConcentrationLimit", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
