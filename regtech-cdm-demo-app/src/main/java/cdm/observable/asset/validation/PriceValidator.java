package cdm.observable.asset.validation;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.UnitType;
import cdm.observable.asset.CashPrice;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceTypeEnum;
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

public class PriceValidator implements Validator<Price> {

	private List<ComparisonResult> getComparisonResults(Price o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("value", (BigDecimal) o.getValue() != null ? 1 : 0, 0, 1), 
				checkCardinality("unit", (UnitType) o.getUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("perUnitOf", (UnitType) o.getPerUnitOf() != null ? 1 : 0, 0, 1), 
				checkCardinality("priceType", (PriceTypeEnum) o.getPriceType() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceExpression", (PriceExpressionEnum) o.getPriceExpression() != null ? 1 : 0, 0, 1), 
				checkCardinality("composite", (PriceComposite) o.getComposite() != null ? 1 : 0, 0, 1), 
				checkCardinality("arithmeticOperator", (ArithmeticOperationEnum) o.getArithmeticOperator() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashPrice", (CashPrice) o.getCashPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Price> validate(RosettaPath path, Price o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Price", ValidationType.CARDINALITY, "Price", path, "", error);
		}
		return success("Price", ValidationType.CARDINALITY, "Price", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Price o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Price", ValidationType.CARDINALITY, "Price", path, "", res.getError());
				}
				return success("Price", ValidationType.CARDINALITY, "Price", path, "");
			})
			.collect(toList());
	}

}
