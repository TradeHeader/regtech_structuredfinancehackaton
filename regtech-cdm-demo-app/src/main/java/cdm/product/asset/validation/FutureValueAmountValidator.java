package cdm.product.asset.validation;

import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.product.asset.FutureValueAmount;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class FutureValueAmountValidator implements Validator<FutureValueAmount> {

	private List<ComparisonResult> getComparisonResults(FutureValueAmount o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("quantity", (ReferenceWithMetaNonNegativeQuantitySchedule) o.getQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationPeriodNumberOfDays", (Integer) o.getCalculationPeriodNumberOfDays() != null ? 1 : 0, 1, 1), 
				checkCardinality("valueDate", (Date) o.getValueDate() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FutureValueAmount> validate(RosettaPath path, FutureValueAmount o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FutureValueAmount", ValidationType.CARDINALITY, "FutureValueAmount", path, "", error);
		}
		return success("FutureValueAmount", ValidationType.CARDINALITY, "FutureValueAmount", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FutureValueAmount o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FutureValueAmount", ValidationType.CARDINALITY, "FutureValueAmount", path, "", res.getError());
				}
				return success("FutureValueAmount", ValidationType.CARDINALITY, "FutureValueAmount", path, "");
			})
			.collect(toList());
	}

}
