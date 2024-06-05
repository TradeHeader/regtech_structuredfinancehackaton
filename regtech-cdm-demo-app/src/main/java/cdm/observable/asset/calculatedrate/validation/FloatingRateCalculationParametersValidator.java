package cdm.observable.asset.calculatedrate.validation;

import cdm.base.datetime.BusinessCenters;
import cdm.observable.asset.calculatedrate.CalculationMethodEnum;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.ObservationParameters;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation;
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

public class FloatingRateCalculationParametersValidator implements Validator<FloatingRateCalculationParameters> {

	private List<ComparisonResult> getComparisonResults(FloatingRateCalculationParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationMethod", (CalculationMethodEnum) o.getCalculationMethod() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationShiftCalculation", (ObservationShiftCalculation) o.getObservationShiftCalculation() != null ? 1 : 0, 0, 1), 
				checkCardinality("lookbackCalculation", (OffsetCalculation) o.getLookbackCalculation() != null ? 1 : 0, 0, 1), 
				checkCardinality("lockoutCalculation", (OffsetCalculation) o.getLockoutCalculation() != null ? 1 : 0, 0, 1), 
				checkCardinality("applicableBusinessDays", (BusinessCenters) o.getApplicableBusinessDays() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationParameters", (ObservationParameters) o.getObservationParameters() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRateCalculationParameters> validate(RosettaPath path, FloatingRateCalculationParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateCalculationParameters", ValidationType.CARDINALITY, "FloatingRateCalculationParameters", path, "", error);
		}
		return success("FloatingRateCalculationParameters", ValidationType.CARDINALITY, "FloatingRateCalculationParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateCalculationParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateCalculationParameters", ValidationType.CARDINALITY, "FloatingRateCalculationParameters", path, "", res.getError());
				}
				return success("FloatingRateCalculationParameters", ValidationType.CARDINALITY, "FloatingRateCalculationParameters", path, "");
			})
			.collect(toList());
	}

}
