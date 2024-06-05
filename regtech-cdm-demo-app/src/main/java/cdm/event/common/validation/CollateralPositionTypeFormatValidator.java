package cdm.event.common.validation;

import cdm.event.common.CollateralPosition;
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

public class CollateralPositionTypeFormatValidator implements Validator<CollateralPosition> {

	private List<ComparisonResult> getComparisonResults(CollateralPosition o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralPosition> validate(RosettaPath path, CollateralPosition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralPosition", ValidationType.TYPE_FORMAT, "CollateralPosition", path, "", error);
		}
		return success("CollateralPosition", ValidationType.TYPE_FORMAT, "CollateralPosition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralPosition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralPosition", ValidationType.TYPE_FORMAT, "CollateralPosition", path, "", res.getError());
				}
				return success("CollateralPosition", ValidationType.TYPE_FORMAT, "CollateralPosition", path, "");
			})
			.collect(toList());
	}

}
