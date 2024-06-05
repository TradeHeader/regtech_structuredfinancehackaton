package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.Lag;
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

public class LagTypeFormatValidator implements Validator<Lag> {

	private List<ComparisonResult> getComparisonResults(Lag o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Lag> validate(RosettaPath path, Lag o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Lag", ValidationType.TYPE_FORMAT, "Lag", path, "", error);
		}
		return success("Lag", ValidationType.TYPE_FORMAT, "Lag", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Lag o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Lag", ValidationType.TYPE_FORMAT, "Lag", path, "", res.getError());
				}
				return success("Lag", ValidationType.TYPE_FORMAT, "Lag", path, "");
			})
			.collect(toList());
	}

}
