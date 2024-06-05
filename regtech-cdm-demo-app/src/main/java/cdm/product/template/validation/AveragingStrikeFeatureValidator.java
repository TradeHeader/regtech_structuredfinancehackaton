package cdm.product.template.validation;

import cdm.product.common.schedule.ObservationTerms;
import cdm.product.template.AveragingCalculation;
import cdm.product.template.AveragingStrikeFeature;
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

public class AveragingStrikeFeatureValidator implements Validator<AveragingStrikeFeature> {

	private List<ComparisonResult> getComparisonResults(AveragingStrikeFeature o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("averagingCalculation", (AveragingCalculation) o.getAveragingCalculation() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationTerms", (ObservationTerms) o.getObservationTerms() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AveragingStrikeFeature> validate(RosettaPath path, AveragingStrikeFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingStrikeFeature", ValidationType.CARDINALITY, "AveragingStrikeFeature", path, "", error);
		}
		return success("AveragingStrikeFeature", ValidationType.CARDINALITY, "AveragingStrikeFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingStrikeFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingStrikeFeature", ValidationType.CARDINALITY, "AveragingStrikeFeature", path, "", res.getError());
				}
				return success("AveragingStrikeFeature", ValidationType.CARDINALITY, "AveragingStrikeFeature", path, "");
			})
			.collect(toList());
	}

}
