package cdm.event.common.validation;

import cdm.event.common.Exposure;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState;
import cdm.observable.asset.Money;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ExposureValidator implements Validator<Exposure> {

	private List<ComparisonResult> getComparisonResults(Exposure o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("tradePortfolio", (ReferenceWithMetaPortfolioState) o.getTradePortfolio() != null ? 1 : 0, 1, 1), 
				checkCardinality("aggregateValue", (Money) o.getAggregateValue() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationDateTime", (ZonedDateTime) o.getCalculationDateTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDateTime", (ZonedDateTime) o.getValuationDateTime() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Exposure> validate(RosettaPath path, Exposure o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Exposure", ValidationType.CARDINALITY, "Exposure", path, "", error);
		}
		return success("Exposure", ValidationType.CARDINALITY, "Exposure", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Exposure o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Exposure", ValidationType.CARDINALITY, "Exposure", path, "", res.getError());
				}
				return success("Exposure", ValidationType.CARDINALITY, "Exposure", path, "");
			})
			.collect(toList());
	}

}
