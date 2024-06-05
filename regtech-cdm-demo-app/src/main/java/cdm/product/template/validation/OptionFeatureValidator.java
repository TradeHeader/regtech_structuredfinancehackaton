package cdm.product.template.validation;

import cdm.product.template.AveragingCalculation;
import cdm.product.template.Barrier;
import cdm.product.template.Knock;
import cdm.product.template.OptionFeature;
import cdm.product.template.PassThrough;
import cdm.product.template.StrategyFeature;
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

public class OptionFeatureValidator implements Validator<OptionFeature> {

	private List<ComparisonResult> getComparisonResults(OptionFeature o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("strategyFeature", (StrategyFeature) o.getStrategyFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingFeature", (AveragingCalculation) o.getAveragingFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("barrier", (Barrier) o.getBarrier() != null ? 1 : 0, 0, 1), 
				checkCardinality("knock", (Knock) o.getKnock() != null ? 1 : 0, 0, 1), 
				checkCardinality("passThrough", (PassThrough) o.getPassThrough() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionFeature> validate(RosettaPath path, OptionFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionFeature", ValidationType.CARDINALITY, "OptionFeature", path, "", error);
		}
		return success("OptionFeature", ValidationType.CARDINALITY, "OptionFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionFeature", ValidationType.CARDINALITY, "OptionFeature", path, "", res.getError());
				}
				return success("OptionFeature", ValidationType.CARDINALITY, "OptionFeature", path, "");
			})
			.collect(toList());
	}

}
