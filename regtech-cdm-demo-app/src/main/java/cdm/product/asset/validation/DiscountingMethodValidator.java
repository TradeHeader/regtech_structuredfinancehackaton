package cdm.product.asset.validation;

import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.DiscountingTypeEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
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

public class DiscountingMethodValidator implements Validator<DiscountingMethod> {

	private List<ComparisonResult> getComparisonResults(DiscountingMethod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("discountingType", (DiscountingTypeEnum) o.getDiscountingType() != null ? 1 : 0, 1, 1), 
				checkCardinality("discountRate", (BigDecimal) o.getDiscountRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("discountRateDayCountFraction", (FieldWithMetaDayCountFractionEnum) o.getDiscountRateDayCountFraction() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DiscountingMethod> validate(RosettaPath path, DiscountingMethod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DiscountingMethod", ValidationType.CARDINALITY, "DiscountingMethod", path, "", error);
		}
		return success("DiscountingMethod", ValidationType.CARDINALITY, "DiscountingMethod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DiscountingMethod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DiscountingMethod", ValidationType.CARDINALITY, "DiscountingMethod", path, "", res.getError());
				}
				return success("DiscountingMethod", ValidationType.CARDINALITY, "DiscountingMethod", path, "");
			})
			.collect(toList());
	}

}
