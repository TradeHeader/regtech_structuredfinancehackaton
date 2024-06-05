package cdm.observable.asset.validation;

import cdm.observable.asset.CalculationAgent;
import cdm.observable.asset.FallbackReferencePrice;
import cdm.observable.asset.ValuationPostponement;
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

public class FallbackReferencePriceValidator implements Validator<FallbackReferencePrice> {

	private List<ComparisonResult> getComparisonResults(FallbackReferencePrice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("valuationPostponement", (ValuationPostponement) o.getValuationPostponement() != null ? 1 : 0, 0, 1), 
				checkCardinality("fallbackSurveyValuationPostponement", (Boolean) o.getFallbackSurveyValuationPostponement() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgentDetermination", (CalculationAgent) o.getCalculationAgentDetermination() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FallbackReferencePrice> validate(RosettaPath path, FallbackReferencePrice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FallbackReferencePrice", ValidationType.CARDINALITY, "FallbackReferencePrice", path, "", error);
		}
		return success("FallbackReferencePrice", ValidationType.CARDINALITY, "FallbackReferencePrice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FallbackReferencePrice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FallbackReferencePrice", ValidationType.CARDINALITY, "FallbackReferencePrice", path, "", res.getError());
				}
				return success("FallbackReferencePrice", ValidationType.CARDINALITY, "FallbackReferencePrice", path, "");
			})
			.collect(toList());
	}

}
