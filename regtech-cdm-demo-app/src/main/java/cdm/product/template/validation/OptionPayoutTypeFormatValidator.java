package cdm.product.template.validation;

import cdm.product.template.OptionPayout;
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

public class OptionPayoutTypeFormatValidator implements Validator<OptionPayout> {

	private List<ComparisonResult> getComparisonResults(OptionPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<OptionPayout> validate(RosettaPath path, OptionPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionPayout", ValidationType.TYPE_FORMAT, "OptionPayout", path, "", error);
		}
		return success("OptionPayout", ValidationType.TYPE_FORMAT, "OptionPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionPayout", ValidationType.TYPE_FORMAT, "OptionPayout", path, "", res.getError());
				}
				return success("OptionPayout", ValidationType.TYPE_FORMAT, "OptionPayout", path, "");
			})
			.collect(toList());
	}

}
