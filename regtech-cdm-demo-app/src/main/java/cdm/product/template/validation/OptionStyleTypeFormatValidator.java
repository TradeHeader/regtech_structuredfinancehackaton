package cdm.product.template.validation;

import cdm.product.template.OptionStyle;
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

public class OptionStyleTypeFormatValidator implements Validator<OptionStyle> {

	private List<ComparisonResult> getComparisonResults(OptionStyle o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<OptionStyle> validate(RosettaPath path, OptionStyle o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionStyle", ValidationType.TYPE_FORMAT, "OptionStyle", path, "", error);
		}
		return success("OptionStyle", ValidationType.TYPE_FORMAT, "OptionStyle", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionStyle o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionStyle", ValidationType.TYPE_FORMAT, "OptionStyle", path, "", res.getError());
				}
				return success("OptionStyle", ValidationType.TYPE_FORMAT, "OptionStyle", path, "");
			})
			.collect(toList());
	}

}
