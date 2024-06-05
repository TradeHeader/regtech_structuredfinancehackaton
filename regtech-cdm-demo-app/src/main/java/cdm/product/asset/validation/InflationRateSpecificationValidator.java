package cdm.product.asset.validation;

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
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class InflationRateSpecificationValidator implements Validator<InflationRateSpecification> {

	private List<ComparisonResult> getComparisonResults(InflationRateSpecification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rateOption", (ReferenceWithMetaFloatingRateOption) o.getRateOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("spreadSchedule", (SpreadSchedule) o.getSpreadSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("capRateSchedule", (StrikeSchedule) o.getCapRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("floorRateSchedule", (StrikeSchedule) o.getFloorRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRateMultiplierSchedule", (RateSchedule) o.getFloatingRateMultiplierSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("rateTreatment", (RateTreatmentEnum) o.getRateTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationParameters", (FloatingRateCalculationParameters) o.getCalculationParameters() != null ? 1 : 0, 0, 1), 
				checkCardinality("fallbackRate", (FallbackRateParameters) o.getFallbackRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialRate", (Price) o.getInitialRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalRateRounding", (Rounding) o.getFinalRateRounding() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingMethod", (AveragingWeightingMethodEnum) o.getAveragingMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("negativeInterestRateTreatment", (NegativeInterestRateTreatmentEnum) o.getNegativeInterestRateTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("inflationLag", (Offset) o.getInflationLag() != null ? 1 : 0, 1, 1), 
				checkCardinality("indexSource", (FieldWithMetaString) o.getIndexSource() != null ? 1 : 0, 1, 1), 
				checkCardinality("mainPublication", (FieldWithMetaString) o.getMainPublication() != null ? 1 : 0, 1, 1), 
				checkCardinality("interpolationMethod", (FieldWithMetaInterpolationMethodEnum) o.getInterpolationMethod() != null ? 1 : 0, 1, 1), 
				checkCardinality("initialIndexLevel", (BigDecimal) o.getInitialIndexLevel() != null ? 1 : 0, 0, 1), 
				checkCardinality("fallbackBondApplicable", (Boolean) o.getFallbackBondApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationMethod", (InflationCalculationMethodEnum) o.getCalculationMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationStyle", (InflationCalculationStyleEnum) o.getCalculationStyle() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalPrincipalExchangeCalculation", (FinalPrincipalExchangeCalculationEnum) o.getFinalPrincipalExchangeCalculation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<InflationRateSpecification> validate(RosettaPath path, InflationRateSpecification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InflationRateSpecification", ValidationType.CARDINALITY, "InflationRateSpecification", path, "", error);
		}
		return success("InflationRateSpecification", ValidationType.CARDINALITY, "InflationRateSpecification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InflationRateSpecification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InflationRateSpecification", ValidationType.CARDINALITY, "InflationRateSpecification", path, "", res.getError());
				}
				return success("InflationRateSpecification", ValidationType.CARDINALITY, "InflationRateSpecification", path, "");
			})
			.collect(toList());
	}

}
