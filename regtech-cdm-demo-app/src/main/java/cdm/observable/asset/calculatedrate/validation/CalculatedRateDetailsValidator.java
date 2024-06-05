package cdm.observable.asset.calculatedrate.validation;

import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.observable.asset.calculatedrate.CalculatedRateObservations;
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

public class CalculatedRateDetailsValidator implements Validator<CalculatedRateDetails> {

	private List<ComparisonResult> getComparisonResults(CalculatedRateDetails o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("observations", (CalculatedRateObservations) o.getObservations() != null ? 1 : 0, 0, 1), 
				checkCardinality("aggregateValue", (BigDecimal) o.getAggregateValue() != null ? 1 : 0, 0, 1), 
				checkCardinality("aggregateWeight", (BigDecimal) o.getAggregateWeight() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculatedRate", (BigDecimal) o.getCalculatedRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CalculatedRateDetails> validate(RosettaPath path, CalculatedRateDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculatedRateDetails", ValidationType.CARDINALITY, "CalculatedRateDetails", path, "", error);
		}
		return success("CalculatedRateDetails", ValidationType.CARDINALITY, "CalculatedRateDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculatedRateDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculatedRateDetails", ValidationType.CARDINALITY, "CalculatedRateDetails", path, "", res.getError());
				}
				return success("CalculatedRateDetails", ValidationType.CARDINALITY, "CalculatedRateDetails", path, "");
			})
			.collect(toList());
	}

}
