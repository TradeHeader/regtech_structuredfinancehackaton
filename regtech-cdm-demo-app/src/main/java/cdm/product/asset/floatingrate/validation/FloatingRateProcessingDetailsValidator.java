package cdm.product.asset.floatingrate.validation;

import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
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

public class FloatingRateProcessingDetailsValidator implements Validator<FloatingRateProcessingDetails> {

	private List<ComparisonResult> getComparisonResults(FloatingRateProcessingDetails o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rawRate", (BigDecimal) o.getRawRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("processingParameters", (FloatingRateProcessingParameters) o.getProcessingParameters() != null ? 1 : 0, 0, 1), 
				checkCardinality("processedRate", (BigDecimal) o.getProcessedRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("spreadExclusiveRate", (BigDecimal) o.getSpreadExclusiveRate() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRateProcessingDetails> validate(RosettaPath path, FloatingRateProcessingDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateProcessingDetails", ValidationType.CARDINALITY, "FloatingRateProcessingDetails", path, "", error);
		}
		return success("FloatingRateProcessingDetails", ValidationType.CARDINALITY, "FloatingRateProcessingDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateProcessingDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateProcessingDetails", ValidationType.CARDINALITY, "FloatingRateProcessingDetails", path, "", res.getError());
				}
				return success("FloatingRateProcessingDetails", ValidationType.CARDINALITY, "FloatingRateProcessingDetails", path, "");
			})
			.collect(toList());
	}

}
