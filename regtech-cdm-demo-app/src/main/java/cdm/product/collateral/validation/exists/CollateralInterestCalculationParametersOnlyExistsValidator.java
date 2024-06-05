package cdm.product.collateral.validation.exists;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.CompoundingTypeEnum;
import cdm.base.datetime.RoundingFrequencyEnum;
import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.math.Rounding;
import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CollateralInterestCalculationParametersOnlyExistsValidator implements ValidatorWithArg<CollateralInterestCalculationParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CollateralInterestCalculationParameters> ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fixedRate", ExistenceChecker.isSet((BigDecimal) o.getFixedRate()))
				.put("floatingRate", ExistenceChecker.isSet((CollateralAgreementFloatingRate) o.getFloatingRate()))
				.put("inBaseCurrency", ExistenceChecker.isSet((Boolean) o.getInBaseCurrency()))
				.put("compoundingType", ExistenceChecker.isSet((CompoundingTypeEnum) o.getCompoundingType()))
				.put("compoundingBusinessCenter", ExistenceChecker.isSet((List<BusinessCenterEnum>) o.getCompoundingBusinessCenter()))
				.put("dayCountFraction", ExistenceChecker.isSet((DayCountFractionEnum) o.getDayCountFraction()))
				.put("rounding", ExistenceChecker.isSet((Rounding) o.getRounding()))
				.put("roundingFrequency", ExistenceChecker.isSet((RoundingFrequencyEnum) o.getRoundingFrequency()))
				.put("withholdingTaxRate", ExistenceChecker.isSet((BigDecimal) o.getWithholdingTaxRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CollateralInterestCalculationParameters", ValidationType.ONLY_EXISTS, "CollateralInterestCalculationParameters", path, "");
		}
		return failure("CollateralInterestCalculationParameters", ValidationType.ONLY_EXISTS, "CollateralInterestCalculationParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
