package cdm.observable.asset.validation;

import cdm.observable.asset.RateObservation;
import cdm.observable.asset.metafields.ReferenceWithMetaRateObservation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class RateObservationValidator implements Validator<RateObservation> {

	private List<ComparisonResult> getComparisonResults(RateObservation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("resetDate", (Date) o.getResetDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedFixingDate", (Date) o.getAdjustedFixingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("observedRate", (BigDecimal) o.getObservedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("treatedRate", (BigDecimal) o.getTreatedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationWeight", (Integer) o.getObservationWeight() != null ? 1 : 0, 0, 1), 
				checkCardinality("rateReference", (ReferenceWithMetaRateObservation) o.getRateReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("forecastRate", (BigDecimal) o.getForecastRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("treatedForecastRate", (BigDecimal) o.getTreatedForecastRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<RateObservation> validate(RosettaPath path, RateObservation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RateObservation", ValidationType.CARDINALITY, "RateObservation", path, "", error);
		}
		return success("RateObservation", ValidationType.CARDINALITY, "RateObservation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RateObservation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RateObservation", ValidationType.CARDINALITY, "RateObservation", path, "", res.getError());
				}
				return success("RateObservation", ValidationType.CARDINALITY, "RateObservation", path, "");
			})
			.collect(toList());
	}

}
