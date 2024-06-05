package cdm.product.common.schedule.validation;

import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.metafields.ReferenceWithMetaAdjustableOrRelativeDates;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout;
import cdm.product.common.schedule.FinalCalculationPeriodDateAdjustment;
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

public class FinalCalculationPeriodDateAdjustmentValidator implements Validator<FinalCalculationPeriodDateAdjustment> {

	private List<ComparisonResult> getComparisonResults(FinalCalculationPeriodDateAdjustment o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("relevantUnderlyingDateReference", (ReferenceWithMetaAdjustableOrRelativeDates) o.getRelevantUnderlyingDateReference() != null ? 1 : 0, 1, 1), 
				checkCardinality("swapStreamReference", (ReferenceWithMetaInterestRatePayout) o.getSwapStreamReference() != null ? 1 : 0, 1, 1), 
				checkCardinality("businessDayConvention", (BusinessDayConventionEnum) o.getBusinessDayConvention() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FinalCalculationPeriodDateAdjustment> validate(RosettaPath path, FinalCalculationPeriodDateAdjustment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FinalCalculationPeriodDateAdjustment", ValidationType.CARDINALITY, "FinalCalculationPeriodDateAdjustment", path, "", error);
		}
		return success("FinalCalculationPeriodDateAdjustment", ValidationType.CARDINALITY, "FinalCalculationPeriodDateAdjustment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FinalCalculationPeriodDateAdjustment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FinalCalculationPeriodDateAdjustment", ValidationType.CARDINALITY, "FinalCalculationPeriodDateAdjustment", path, "", res.getError());
				}
				return success("FinalCalculationPeriodDateAdjustment", ValidationType.CARDINALITY, "FinalCalculationPeriodDateAdjustment", path, "");
			})
			.collect(toList());
	}

}
