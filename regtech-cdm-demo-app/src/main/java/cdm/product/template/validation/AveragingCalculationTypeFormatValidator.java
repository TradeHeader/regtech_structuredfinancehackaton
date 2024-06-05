package cdm.product.template.validation;

import cdm.product.template.AveragingCalculation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AveragingCalculationTypeFormatValidator implements Validator<AveragingCalculation> {

	private List<ComparisonResult> getComparisonResults(AveragingCalculation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AveragingCalculation> validate(RosettaPath path, AveragingCalculation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingCalculation", ValidationType.TYPE_FORMAT, "AveragingCalculation", path, "", error);
		}
		return success("AveragingCalculation", ValidationType.TYPE_FORMAT, "AveragingCalculation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingCalculation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingCalculation", ValidationType.TYPE_FORMAT, "AveragingCalculation", path, "", res.getError());
				}
				return success("AveragingCalculation", ValidationType.TYPE_FORMAT, "AveragingCalculation", path, "");
			})
			.collect(toList());
	}

}
