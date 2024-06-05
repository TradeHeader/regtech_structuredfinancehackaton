package cdm.product.template.validation;

import cdm.product.template.Barrier;
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

public class BarrierTypeFormatValidator implements Validator<Barrier> {

	private List<ComparisonResult> getComparisonResults(Barrier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Barrier> validate(RosettaPath path, Barrier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Barrier", ValidationType.TYPE_FORMAT, "Barrier", path, "", error);
		}
		return success("Barrier", ValidationType.TYPE_FORMAT, "Barrier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Barrier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Barrier", ValidationType.TYPE_FORMAT, "Barrier", path, "", res.getError());
				}
				return success("Barrier", ValidationType.TYPE_FORMAT, "Barrier", path, "");
			})
			.collect(toList());
	}

}
