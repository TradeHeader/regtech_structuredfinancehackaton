package cdm.product.common.schedule.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.math.Rounding;
import cdm.observable.asset.FxSpotRateSource;
import cdm.observable.asset.Observable;
import cdm.observable.common.TimeTypeEnum;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.ObservationTerms;
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

public class ObservationTermsValidator implements Validator<ObservationTerms> {

	private List<ComparisonResult> getComparisonResults(ObservationTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("pricingTime", (BusinessCenterTime) o.getPricingTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("pricingTimeType", (TimeTypeEnum) o.getPricingTimeType() != null ? 1 : 0, 0, 1), 
				checkCardinality("informationSource", (FxSpotRateSource) o.getInformationSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("precision", (Rounding) o.getPrecision() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationPeriodDates", (CalculationPeriodDates) o.getCalculationPeriodDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("observable", (Observable) o.getObservable() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationDates", (ObservationDates) o.getObservationDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("numberOfObservationDates", (Integer) o.getNumberOfObservationDates() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ObservationTerms> validate(RosettaPath path, ObservationTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationTerms", ValidationType.CARDINALITY, "ObservationTerms", path, "", error);
		}
		return success("ObservationTerms", ValidationType.CARDINALITY, "ObservationTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationTerms", ValidationType.CARDINALITY, "ObservationTerms", path, "", res.getError());
				}
				return success("ObservationTerms", ValidationType.CARDINALITY, "ObservationTerms", path, "");
			})
			.collect(toList());
	}

}
