package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.FxLinkedNotionalAmount;
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

public class FxLinkedNotionalAmountValidator implements Validator<FxLinkedNotionalAmount> {

	private List<ComparisonResult> getComparisonResults(FxLinkedNotionalAmount o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("resetDate", (Date) o.getResetDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedFxSpotFixingDate", (Date) o.getAdjustedFxSpotFixingDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("observedFxSpotRate", (BigDecimal) o.getObservedFxSpotRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("notionalAmount", (BigDecimal) o.getNotionalAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FxLinkedNotionalAmount> validate(RosettaPath path, FxLinkedNotionalAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxLinkedNotionalAmount", ValidationType.CARDINALITY, "FxLinkedNotionalAmount", path, "", error);
		}
		return success("FxLinkedNotionalAmount", ValidationType.CARDINALITY, "FxLinkedNotionalAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxLinkedNotionalAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxLinkedNotionalAmount", ValidationType.CARDINALITY, "FxLinkedNotionalAmount", path, "", res.getError());
				}
				return success("FxLinkedNotionalAmount", ValidationType.CARDINALITY, "FxLinkedNotionalAmount", path, "");
			})
			.collect(toList());
	}

}
