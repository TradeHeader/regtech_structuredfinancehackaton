package cdm.observable.asset.validation;

import cdm.observable.asset.QuotationStyleEnum;
import cdm.observable.asset.TransactedPrice;
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

public class TransactedPriceValidator implements Validator<TransactedPrice> {

	private List<ComparisonResult> getComparisonResults(TransactedPrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("marketFixedRate", (BigDecimal) o.getMarketFixedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialPoints", (BigDecimal) o.getInitialPoints() != null ? 1 : 0, 0, 1), 
				checkCardinality("marketPrice", (BigDecimal) o.getMarketPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("quotationStyle", (QuotationStyleEnum) o.getQuotationStyle() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TransactedPrice> validate(RosettaPath path, TransactedPrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TransactedPrice", ValidationType.CARDINALITY, "TransactedPrice", path, "", error);
		}
		return success("TransactedPrice", ValidationType.CARDINALITY, "TransactedPrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TransactedPrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TransactedPrice", ValidationType.CARDINALITY, "TransactedPrice", path, "", res.getError());
				}
				return success("TransactedPrice", ValidationType.CARDINALITY, "TransactedPrice", path, "");
			})
			.collect(toList());
	}

}
