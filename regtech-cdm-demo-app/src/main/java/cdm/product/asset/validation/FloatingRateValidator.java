package cdm.product.asset.validation;

import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FloatingRate;
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
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FloatingRateValidator implements Validator<FloatingRate> {

	private List<ComparisonResult> getComparisonResults(FloatingRate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rateOption", (ReferenceWithMetaFloatingRateOption) o.getRateOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("spreadSchedule", (SpreadSchedule) o.getSpreadSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("capRateSchedule", (StrikeSchedule) o.getCapRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("floorRateSchedule", (StrikeSchedule) o.getFloorRateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRateMultiplierSchedule", (RateSchedule) o.getFloatingRateMultiplierSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("rateTreatment", (RateTreatmentEnum) o.getRateTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationParameters", (FloatingRateCalculationParameters) o.getCalculationParameters() != null ? 1 : 0, 0, 1), 
				checkCardinality("fallbackRate", (FallbackRateParameters) o.getFallbackRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRate> validate(RosettaPath path, FloatingRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRate", ValidationType.CARDINALITY, "FloatingRate", path, "", error);
		}
		return success("FloatingRate", ValidationType.CARDINALITY, "FloatingRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRate", ValidationType.CARDINALITY, "FloatingRate", path, "", res.getError());
				}
				return success("FloatingRate", ValidationType.CARDINALITY, "FloatingRate", path, "");
			})
			.collect(toList());
	}

}
