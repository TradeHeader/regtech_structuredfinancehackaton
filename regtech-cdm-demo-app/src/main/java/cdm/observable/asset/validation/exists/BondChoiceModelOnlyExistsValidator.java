package cdm.observable.asset.validation.exists;

import cdm.base.staticdata.asset.common.Bond;
import cdm.base.staticdata.asset.common.ConvertibleBond;
import cdm.observable.asset.BondChoiceModel;
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

public class BondChoiceModelOnlyExistsValidator implements ValidatorWithArg<BondChoiceModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BondChoiceModel> ValidationResult<BondChoiceModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("bond", ExistenceChecker.isSet((Bond) o.getBond()))
				.put("convertibleBond", ExistenceChecker.isSet((ConvertibleBond) o.getConvertibleBond()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BondChoiceModel", ValidationType.ONLY_EXISTS, "BondChoiceModel", path, "");
		}
		return failure("BondChoiceModel", ValidationType.ONLY_EXISTS, "BondChoiceModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
