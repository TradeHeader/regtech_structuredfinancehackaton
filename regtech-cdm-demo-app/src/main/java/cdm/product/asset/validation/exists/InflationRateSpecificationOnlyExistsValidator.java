package cdm.product.asset.validation.exists;

import cdm.base.datetime.Offset;
import cdm.base.math.AveragingWeightingMethodEnum;
import cdm.base.math.Rounding;
import cdm.observable.asset.Price;
import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.InflationCalculationMethodEnum;
import cdm.observable.asset.calculatedrate.InflationCalculationStyleEnum;
import cdm.observable.asset.metafields.FieldWithMetaInterpolationMethodEnum;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FinalPrincipalExchangeCalculationEnum;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.SpreadSchedule;
import cdm.product.common.schedule.RateSchedule;
import cdm.product.template.StrikeSchedule;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class InflationRateSpecificationOnlyExistsValidator implements ValidatorWithArg<InflationRateSpecification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InflationRateSpecification> ValidationResult<InflationRateSpecification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rateOption", ExistenceChecker.isSet((ReferenceWithMetaFloatingRateOption) o.getRateOption()))
				.put("spreadSchedule", ExistenceChecker.isSet((SpreadSchedule) o.getSpreadSchedule()))
				.put("capRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getCapRateSchedule()))
				.put("floorRateSchedule", ExistenceChecker.isSet((StrikeSchedule) o.getFloorRateSchedule()))
				.put("floatingRateMultiplierSchedule", ExistenceChecker.isSet((RateSchedule) o.getFloatingRateMultiplierSchedule()))
				.put("rateTreatment", ExistenceChecker.isSet((RateTreatmentEnum) o.getRateTreatment()))
				.put("calculationParameters", ExistenceChecker.isSet((FloatingRateCalculationParameters) o.getCalculationParameters()))
				.put("fallbackRate", ExistenceChecker.isSet((FallbackRateParameters) o.getFallbackRate()))
				.put("initialRate", ExistenceChecker.isSet((Price) o.getInitialRate()))
				.put("finalRateRounding", ExistenceChecker.isSet((Rounding) o.getFinalRateRounding()))
				.put("averagingMethod", ExistenceChecker.isSet((AveragingWeightingMethodEnum) o.getAveragingMethod()))
				.put("negativeInterestRateTreatment", ExistenceChecker.isSet((NegativeInterestRateTreatmentEnum) o.getNegativeInterestRateTreatment()))
				.put("inflationLag", ExistenceChecker.isSet((Offset) o.getInflationLag()))
				.put("indexSource", ExistenceChecker.isSet((FieldWithMetaString) o.getIndexSource()))
				.put("mainPublication", ExistenceChecker.isSet((FieldWithMetaString) o.getMainPublication()))
				.put("interpolationMethod", ExistenceChecker.isSet((FieldWithMetaInterpolationMethodEnum) o.getInterpolationMethod()))
				.put("initialIndexLevel", ExistenceChecker.isSet((BigDecimal) o.getInitialIndexLevel()))
				.put("fallbackBondApplicable", ExistenceChecker.isSet((Boolean) o.getFallbackBondApplicable()))
				.put("calculationMethod", ExistenceChecker.isSet((InflationCalculationMethodEnum) o.getCalculationMethod()))
				.put("calculationStyle", ExistenceChecker.isSet((InflationCalculationStyleEnum) o.getCalculationStyle()))
				.put("finalPrincipalExchangeCalculation", ExistenceChecker.isSet((FinalPrincipalExchangeCalculationEnum) o.getFinalPrincipalExchangeCalculation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InflationRateSpecification", ValidationType.ONLY_EXISTS, "InflationRateSpecification", path, "");
		}
		return failure("InflationRateSpecification", ValidationType.ONLY_EXISTS, "InflationRateSpecification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
