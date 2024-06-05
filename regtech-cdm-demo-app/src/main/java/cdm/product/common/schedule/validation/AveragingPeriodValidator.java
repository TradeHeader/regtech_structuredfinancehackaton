package cdm.product.common.schedule.validation;

import cdm.base.datetime.DateTimeList;
import cdm.observable.event.metafields.FieldWithMetaMarketDisruptionEnum;
import cdm.product.common.schedule.AveragingObservationList;
import cdm.product.common.schedule.AveragingPeriod;
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

public class AveragingPeriodValidator implements Validator<AveragingPeriod> {

	private List<ComparisonResult> getComparisonResults(AveragingPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("averagingDateTimes", (DateTimeList) o.getAveragingDateTimes() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingObservations", (AveragingObservationList) o.getAveragingObservations() != null ? 1 : 0, 0, 1), 
				checkCardinality("marketDisruption", (FieldWithMetaMarketDisruptionEnum) o.getMarketDisruption() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AveragingPeriod> validate(RosettaPath path, AveragingPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingPeriod", ValidationType.CARDINALITY, "AveragingPeriod", path, "", error);
		}
		return success("AveragingPeriod", ValidationType.CARDINALITY, "AveragingPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingPeriod", ValidationType.CARDINALITY, "AveragingPeriod", path, "", res.getError());
				}
				return success("AveragingPeriod", ValidationType.CARDINALITY, "AveragingPeriod", path, "");
			})
			.collect(toList());
	}

}
