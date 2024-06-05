package cdm.observable.asset.validation;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceOperandEnum;
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

public class PriceCompositeValidator implements Validator<PriceComposite> {

	private List<ComparisonResult> getComparisonResults(PriceComposite o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("baseValue", (BigDecimal) o.getBaseValue() != null ? 1 : 0, 1, 1), 
				checkCardinality("operand", (BigDecimal) o.getOperand() != null ? 1 : 0, 1, 1), 
				checkCardinality("arithmeticOperator", (ArithmeticOperationEnum) o.getArithmeticOperator() != null ? 1 : 0, 1, 1), 
				checkCardinality("operandType", (PriceOperandEnum) o.getOperandType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PriceComposite> validate(RosettaPath path, PriceComposite o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceComposite", ValidationType.CARDINALITY, "PriceComposite", path, "", error);
		}
		return success("PriceComposite", ValidationType.CARDINALITY, "PriceComposite", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceComposite o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceComposite", ValidationType.CARDINALITY, "PriceComposite", path, "", res.getError());
				}
				return success("PriceComposite", ValidationType.CARDINALITY, "PriceComposite", path, "");
			})
			.collect(toList());
	}

}
