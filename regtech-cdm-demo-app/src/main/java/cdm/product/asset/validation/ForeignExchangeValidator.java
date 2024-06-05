package cdm.product.asset.validation;

import cdm.base.datetime.Period;
import cdm.observable.asset.ExchangeRate;
import cdm.product.asset.ForeignExchange;
import cdm.product.common.settlement.Cashflow;
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

public class ForeignExchangeValidator implements Validator<ForeignExchange> {

	private List<ComparisonResult> getComparisonResults(ForeignExchange o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("exchangedCurrency1", (Cashflow) o.getExchangedCurrency1() != null ? 1 : 0, 1, 1), 
				checkCardinality("exchangedCurrency2", (Cashflow) o.getExchangedCurrency2() != null ? 1 : 0, 1, 1), 
				checkCardinality("tenorPeriod", (Period) o.getTenorPeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("exchangeRate", (ExchangeRate) o.getExchangeRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ForeignExchange> validate(RosettaPath path, ForeignExchange o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ForeignExchange", ValidationType.CARDINALITY, "ForeignExchange", path, "", error);
		}
		return success("ForeignExchange", ValidationType.CARDINALITY, "ForeignExchange", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ForeignExchange o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ForeignExchange", ValidationType.CARDINALITY, "ForeignExchange", path, "", res.getError());
				}
				return success("ForeignExchange", ValidationType.CARDINALITY, "ForeignExchange", path, "");
			})
			.collect(toList());
	}

}
