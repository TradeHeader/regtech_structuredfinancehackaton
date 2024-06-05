package cdm.product.collateral.validation.exists;

import cdm.base.datetime.CalculationFrequency;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.CollateralInterestParameters;
import cdm.product.collateral.CollateralMarginTypeEnum;
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

public class CollateralInterestParametersOnlyExistsValidator implements ValidatorWithArg<CollateralInterestParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralInterestParameters> ValidationResult<CollateralInterestParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("postingParty", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getPostingParty()))
				.put("marginType", ExistenceChecker.isSet((CollateralMarginTypeEnum) o.getMarginType()))
				.put("currency", ExistenceChecker.isSet((String) o.getCurrency()))
				.put("interestCalculationParameters", ExistenceChecker.isSet((CollateralInterestCalculationParameters) o.getInterestCalculationParameters()))
				.put("interestCalculationFrequency", ExistenceChecker.isSet((CalculationFrequency) o.getInterestCalculationFrequency()))
				.put("interestHandlingParameters", ExistenceChecker.isSet((CollateralInterestHandlingParameters) o.getInterestHandlingParameters()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralInterestParameters", ValidationType.ONLY_EXISTS, "CollateralInterestParameters", path, "");
		}
		return failure("CollateralInterestParameters", ValidationType.ONLY_EXISTS, "CollateralInterestParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
