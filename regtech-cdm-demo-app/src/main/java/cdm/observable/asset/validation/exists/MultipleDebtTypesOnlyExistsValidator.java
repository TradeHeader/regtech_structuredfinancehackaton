package cdm.observable.asset.validation.exists;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.MultipleDebtTypes;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class MultipleDebtTypesOnlyExistsValidator implements ValidatorWithArg<MultipleDebtTypes, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MultipleDebtTypes> ValidationResult<MultipleDebtTypes> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("condition", ExistenceChecker.isSet((QuantifierEnum) o.getCondition()))
				.put("debtType", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getDebtType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MultipleDebtTypes", ValidationType.ONLY_EXISTS, "MultipleDebtTypes", path, "");
		}
		return failure("MultipleDebtTypes", ValidationType.ONLY_EXISTS, "MultipleDebtTypes", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
