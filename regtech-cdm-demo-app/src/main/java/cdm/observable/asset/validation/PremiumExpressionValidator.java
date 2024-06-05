package cdm.observable.asset.validation;

import cdm.observable.asset.Money;
import cdm.observable.asset.PremiumExpression;
import cdm.observable.asset.PremiumTypeEnum;
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

public class PremiumExpressionValidator implements Validator<PremiumExpression> {

	private List<ComparisonResult> getComparisonResults(PremiumExpression o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("premiumType", (PremiumTypeEnum) o.getPremiumType() != null ? 1 : 0, 0, 1), 
				checkCardinality("pricePerOption", (Money) o.getPricePerOption() != null ? 1 : 0, 0, 1), 
				checkCardinality("percentageOfNotional", (BigDecimal) o.getPercentageOfNotional() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PremiumExpression> validate(RosettaPath path, PremiumExpression o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PremiumExpression", ValidationType.CARDINALITY, "PremiumExpression", path, "", error);
		}
		return success("PremiumExpression", ValidationType.CARDINALITY, "PremiumExpression", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PremiumExpression o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PremiumExpression", ValidationType.CARDINALITY, "PremiumExpression", path, "", res.getError());
				}
				return success("PremiumExpression", ValidationType.CARDINALITY, "PremiumExpression", path, "");
			})
			.collect(toList());
	}

}
