package cdm.observable.asset.validation;

import cdm.observable.asset.CashPrice;
import cdm.observable.asset.CashPriceTypeEnum;
import cdm.observable.asset.FeeTypeEnum;
import cdm.observable.asset.PremiumExpression;
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

public class CashPriceValidator implements Validator<CashPrice> {

	private List<ComparisonResult> getComparisonResults(CashPrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cashPriceType", (CashPriceTypeEnum) o.getCashPriceType() != null ? 1 : 0, 1, 1), 
				checkCardinality("premiumExpression", (PremiumExpression) o.getPremiumExpression() != null ? 1 : 0, 0, 1), 
				checkCardinality("feeType", (FeeTypeEnum) o.getFeeType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CashPrice> validate(RosettaPath path, CashPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CashPrice", ValidationType.CARDINALITY, "CashPrice", path, "", error);
		}
		return success("CashPrice", ValidationType.CARDINALITY, "CashPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CashPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CashPrice", ValidationType.CARDINALITY, "CashPrice", path, "", res.getError());
				}
				return success("CashPrice", ValidationType.CARDINALITY, "CashPrice", path, "");
			})
			.collect(toList());
	}

}
