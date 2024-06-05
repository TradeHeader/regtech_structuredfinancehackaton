package cdm.product.collateral.validation;

import cdm.product.collateral.InterestAmountApplication;
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

public class InterestAmountApplicationTypeFormatValidator implements Validator<InterestAmountApplication> {

	private List<ComparisonResult> getComparisonResults(InterestAmountApplication o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<InterestAmountApplication> validate(RosettaPath path, InterestAmountApplication o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InterestAmountApplication", ValidationType.TYPE_FORMAT, "InterestAmountApplication", path, "", error);
		}
		return success("InterestAmountApplication", ValidationType.TYPE_FORMAT, "InterestAmountApplication", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InterestAmountApplication o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InterestAmountApplication", ValidationType.TYPE_FORMAT, "InterestAmountApplication", path, "", res.getError());
				}
				return success("InterestAmountApplication", ValidationType.TYPE_FORMAT, "InterestAmountApplication", path, "");
			})
			.collect(toList());
	}

}
