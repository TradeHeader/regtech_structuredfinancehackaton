package cdm.observable.asset.validation.exists;

import cdm.base.staticdata.asset.common.Equity;
import cdm.observable.asset.BondChoiceModel;
import cdm.observable.asset.BondEquityModel;
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

public class BondEquityModelOnlyExistsValidator implements ValidatorWithArg<BondEquityModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BondEquityModel> ValidationResult<BondEquityModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("bondchoiceModel", ExistenceChecker.isSet((BondChoiceModel) o.getBondchoiceModel()))
				.put("equity", ExistenceChecker.isSet((Equity) o.getEquity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BondEquityModel", ValidationType.ONLY_EXISTS, "BondEquityModel", path, "");
		}
		return failure("BondEquityModel", ValidationType.ONLY_EXISTS, "BondEquityModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
