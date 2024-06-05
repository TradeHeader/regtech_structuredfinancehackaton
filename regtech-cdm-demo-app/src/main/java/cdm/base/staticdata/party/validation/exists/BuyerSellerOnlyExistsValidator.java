package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
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

public class BuyerSellerOnlyExistsValidator implements ValidatorWithArg<BuyerSeller, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BuyerSeller> ValidationResult<BuyerSeller> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("buyer", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getBuyer()))
				.put("seller", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getSeller()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BuyerSeller", ValidationType.ONLY_EXISTS, "BuyerSeller", path, "");
		}
		return failure("BuyerSeller", ValidationType.ONLY_EXISTS, "BuyerSeller", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
