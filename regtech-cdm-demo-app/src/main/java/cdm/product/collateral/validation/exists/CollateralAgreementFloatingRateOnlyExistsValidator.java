package cdm.product.collateral.validation.exists;

import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.SpreadSchedule;
import cdm.product.collateral.CollateralAgreementFloatingRate;
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

public class CollateralAgreementFloatingRateOnlyExistsValidator implements ValidatorWithArg<CollateralAgreementFloatingRate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralAgreementFloatingRate> ValidationResult<CollateralAgreementFloatingRate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rateOption", ExistenceChecker.isSet((ReferenceWithMetaFloatingRateOption) o.getRateOption()))
				.put("spreadSchedule", ExistenceChecker.isSet((SpreadSchedule) o.getSpreadSchedule()))
				.put("capRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getCapRateSchedule()))
				.put("floorRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getFloorRateSchedule()))
				.put("negativeInterest", ExistenceChecker.isSet((Boolean) o.getNegativeInterest()))
				.put("compressibleSpread", ExistenceChecker.isSet((Boolean) o.getCompressibleSpread()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralAgreementFloatingRate", ValidationType.ONLY_EXISTS, "CollateralAgreementFloatingRate", path, "");
		}
		return failure("CollateralAgreementFloatingRate", ValidationType.ONLY_EXISTS, "CollateralAgreementFloatingRate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
