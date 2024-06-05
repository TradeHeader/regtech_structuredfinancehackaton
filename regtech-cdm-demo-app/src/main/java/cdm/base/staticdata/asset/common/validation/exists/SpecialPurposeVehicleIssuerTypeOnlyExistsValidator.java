package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.CreditRiskEnum;
import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
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

public class SpecialPurposeVehicleIssuerTypeOnlyExistsValidator implements ValidatorWithArg<SpecialPurposeVehicleIssuerType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SpecialPurposeVehicleIssuerType> ValidationResult<SpecialPurposeVehicleIssuerType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("creditRisk", ExistenceChecker.isSet((CreditRiskEnum) o.getCreditRisk()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SpecialPurposeVehicleIssuerType", ValidationType.ONLY_EXISTS, "SpecialPurposeVehicleIssuerType", path, "");
		}
		return failure("SpecialPurposeVehicleIssuerType", ValidationType.ONLY_EXISTS, "SpecialPurposeVehicleIssuerType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
