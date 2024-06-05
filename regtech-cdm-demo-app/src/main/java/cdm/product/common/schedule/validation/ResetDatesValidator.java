package cdm.product.common.schedule.validation;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.Offset;
import cdm.base.datetime.RelativeDateOffset;
import cdm.product.common.schedule.InitialFixingDate;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.ResetFrequency;
import cdm.product.common.schedule.ResetRelativeToEnum;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates;
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

public class ResetDatesValidator implements Validator<ResetDates> {

	private List<ComparisonResult> getComparisonResults(ResetDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationPeriodDatesReference", (ReferenceWithMetaCalculationPeriodDates) o.getCalculationPeriodDatesReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("resetRelativeTo", (ResetRelativeToEnum) o.getResetRelativeTo() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialFixingDate", (InitialFixingDate) o.getInitialFixingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixingDates", (RelativeDateOffset) o.getFixingDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalFixingDate", (AdjustableDate) o.getFinalFixingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("rateCutOffDaysOffset", (Offset) o.getRateCutOffDaysOffset() != null ? 1 : 0, 0, 1), 
				checkCardinality("resetFrequency", (ResetFrequency) o.getResetFrequency() != null ? 1 : 0, 0, 1), 
				checkCardinality("resetDatesAdjustments", (BusinessDayAdjustments) o.getResetDatesAdjustments() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ResetDates> validate(RosettaPath path, ResetDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ResetDates", ValidationType.CARDINALITY, "ResetDates", path, "", error);
		}
		return success("ResetDates", ValidationType.CARDINALITY, "ResetDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ResetDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ResetDates", ValidationType.CARDINALITY, "ResetDates", path, "", res.getError());
				}
				return success("ResetDates", ValidationType.CARDINALITY, "ResetDates", path, "");
			})
			.collect(toList());
	}

}
