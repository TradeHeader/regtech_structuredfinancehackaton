package cdm.event.common.validation;

import cdm.event.common.PriceTimingEnum;
import cdm.event.common.Valuation;
import cdm.event.common.ValuationSourceEnum;
import cdm.event.common.ValuationTypeEnum;
import cdm.observable.asset.Money;
import cdm.observable.asset.Price;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ValuationValidator implements Validator<Valuation> {

	private List<ComparisonResult> getComparisonResults(Valuation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("amount", (Money) o.getAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("timestamp", (ZonedDateTime) o.getTimestamp() != null ? 1 : 0, 1, 1), 
				checkCardinality("method", (ValuationTypeEnum) o.getMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("source", (ValuationSourceEnum) o.getSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("delta", (BigDecimal) o.getDelta() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationTiming", (PriceTimingEnum) o.getValuationTiming() != null ? 1 : 0, 0, 1), 
				checkCardinality("priceComponent", (Price) o.getPriceComponent() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Valuation> validate(RosettaPath path, Valuation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Valuation", ValidationType.CARDINALITY, "Valuation", path, "", error);
		}
		return success("Valuation", ValidationType.CARDINALITY, "Valuation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Valuation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Valuation", ValidationType.CARDINALITY, "Valuation", path, "", res.getError());
				}
				return success("Valuation", ValidationType.CARDINALITY, "Valuation", path, "");
			})
			.collect(toList());
	}

}
