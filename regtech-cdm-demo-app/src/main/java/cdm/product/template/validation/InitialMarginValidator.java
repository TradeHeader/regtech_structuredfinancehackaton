package cdm.product.template.validation;

import cdm.observable.asset.Money;
import cdm.product.template.InitialMargin;
import cdm.product.template.InitialMarginCalculation;
import cdm.product.template.MarginTypeEnum;
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

public class InitialMarginValidator implements Validator<InitialMargin> {

	private List<ComparisonResult> getComparisonResults(InitialMargin o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("marginType", (MarginTypeEnum) o.getMarginType() != null ? 1 : 0, 1, 1), 
				checkCardinality("margin", (List<? extends InitialMarginCalculation>) o.getMargin() == null ? 0 : ((List<? extends InitialMarginCalculation>) o.getMargin()).size(), 1, 0), 
				checkCardinality("marginThreshold", (Money) o.getMarginThreshold() != null ? 1 : 0, 0, 1), 
				checkCardinality("minimumTransferAmount", (Money) o.getMinimumTransferAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<InitialMargin> validate(RosettaPath path, InitialMargin o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InitialMargin", ValidationType.CARDINALITY, "InitialMargin", path, "", error);
		}
		return success("InitialMargin", ValidationType.CARDINALITY, "InitialMargin", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InitialMargin o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InitialMargin", ValidationType.CARDINALITY, "InitialMargin", path, "", res.getError());
				}
				return success("InitialMargin", ValidationType.CARDINALITY, "InitialMargin", path, "");
			})
			.collect(toList());
	}

}
