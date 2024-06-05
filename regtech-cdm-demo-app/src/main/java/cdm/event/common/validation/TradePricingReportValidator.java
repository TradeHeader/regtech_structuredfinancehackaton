package cdm.event.common.validation;

import cdm.base.datetime.TimeZone;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.event.common.Trade;
import cdm.event.common.TradePricingReport;
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

public class TradePricingReportValidator implements Validator<TradePricingReport> {

	private List<ComparisonResult> getComparisonResults(TradePricingReport o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("trade", (Trade) o.getTrade() != null ? 1 : 0, 1, 1), 
				checkCardinality("pricingTime", (TimeZone) o.getPricingTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("discountingIndex", (FloatingRateIndexEnum) o.getDiscountingIndex() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TradePricingReport> validate(RosettaPath path, TradePricingReport o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradePricingReport", ValidationType.CARDINALITY, "TradePricingReport", path, "", error);
		}
		return success("TradePricingReport", ValidationType.CARDINALITY, "TradePricingReport", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradePricingReport o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradePricingReport", ValidationType.CARDINALITY, "TradePricingReport", path, "", res.getError());
				}
				return success("TradePricingReport", ValidationType.CARDINALITY, "TradePricingReport", path, "");
			})
			.collect(toList());
	}

}
