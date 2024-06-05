package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PercentageRule;
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

public class PercentageRuleTypeFormatValidator implements Validator<PercentageRule> {

	private List<ComparisonResult> getComparisonResults(PercentageRule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PercentageRule> validate(RosettaPath path, PercentageRule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PercentageRule", ValidationType.TYPE_FORMAT, "PercentageRule", path, "", error);
		}
		return success("PercentageRule", ValidationType.TYPE_FORMAT, "PercentageRule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PercentageRule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PercentageRule", ValidationType.TYPE_FORMAT, "PercentageRule", path, "", res.getError());
				}
				return success("PercentageRule", ValidationType.TYPE_FORMAT, "PercentageRule", path, "");
			})
			.collect(toList());
	}

}
