package cdm.event.common.validation;

import cdm.event.common.CollateralBalance;
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

public class CollateralBalanceTypeFormatValidator implements Validator<CollateralBalance> {

	private List<ComparisonResult> getComparisonResults(CollateralBalance o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralBalance> validate(RosettaPath path, CollateralBalance o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralBalance", ValidationType.TYPE_FORMAT, "CollateralBalance", path, "", error);
		}
		return success("CollateralBalance", ValidationType.TYPE_FORMAT, "CollateralBalance", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralBalance o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralBalance", ValidationType.TYPE_FORMAT, "CollateralBalance", path, "", res.getError());
				}
				return success("CollateralBalance", ValidationType.TYPE_FORMAT, "CollateralBalance", path, "");
			})
			.collect(toList());
	}

}
