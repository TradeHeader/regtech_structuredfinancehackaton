package cdm.product.common.schedule.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.RelativeDateOffset;
import cdm.observable.asset.FxSpotRateSource;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FxLinkedNotionalScheduleValidator implements Validator<FxLinkedNotionalSchedule> {

	private List<ComparisonResult> getComparisonResults(FxLinkedNotionalSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("varyingNotionalCurrency", (FieldWithMetaString) o.getVaryingNotionalCurrency() != null ? 1 : 0, 1, 1), 
				checkCardinality("varyingNotionalFixingDates", (RelativeDateOffset) o.getVaryingNotionalFixingDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("fxSpotRateSource", (FxSpotRateSource) o.getFxSpotRateSource() != null ? 1 : 0, 1, 1), 
				checkCardinality("fixingTime", (BusinessCenterTime) o.getFixingTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("varyingNotionalInterimExchangePaymentDates", (RelativeDateOffset) o.getVaryingNotionalInterimExchangePaymentDates() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FxLinkedNotionalSchedule> validate(RosettaPath path, FxLinkedNotionalSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxLinkedNotionalSchedule", ValidationType.CARDINALITY, "FxLinkedNotionalSchedule", path, "", error);
		}
		return success("FxLinkedNotionalSchedule", ValidationType.CARDINALITY, "FxLinkedNotionalSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxLinkedNotionalSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxLinkedNotionalSchedule", ValidationType.CARDINALITY, "FxLinkedNotionalSchedule", path, "", res.getError());
				}
				return success("FxLinkedNotionalSchedule", ValidationType.CARDINALITY, "FxLinkedNotionalSchedule", path, "");
			})
			.collect(toList());
	}

}
