package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralInterestNotification;
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

public class CollateralInterestNotificationTypeFormatValidator implements Validator<CollateralInterestNotification> {

	private List<ComparisonResult> getComparisonResults(CollateralInterestNotification o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralInterestNotification> validate(RosettaPath path, CollateralInterestNotification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralInterestNotification", ValidationType.TYPE_FORMAT, "CollateralInterestNotification", path, "", error);
		}
		return success("CollateralInterestNotification", ValidationType.TYPE_FORMAT, "CollateralInterestNotification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralInterestNotification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralInterestNotification", ValidationType.TYPE_FORMAT, "CollateralInterestNotification", path, "", res.getError());
				}
				return success("CollateralInterestNotification", ValidationType.TYPE_FORMAT, "CollateralInterestNotification", path, "");
			})
			.collect(toList());
	}

}
