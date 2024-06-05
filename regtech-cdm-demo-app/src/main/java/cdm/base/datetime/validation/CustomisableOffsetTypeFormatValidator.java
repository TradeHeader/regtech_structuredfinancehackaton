package cdm.base.datetime.validation;

import cdm.base.datetime.CustomisableOffset;
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

public class CustomisableOffsetTypeFormatValidator implements Validator<CustomisableOffset> {

	private List<ComparisonResult> getComparisonResults(CustomisableOffset o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CustomisableOffset> validate(RosettaPath path, CustomisableOffset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CustomisableOffset", ValidationType.TYPE_FORMAT, "CustomisableOffset", path, "", error);
		}
		return success("CustomisableOffset", ValidationType.TYPE_FORMAT, "CustomisableOffset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CustomisableOffset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CustomisableOffset", ValidationType.TYPE_FORMAT, "CustomisableOffset", path, "", res.getError());
				}
				return success("CustomisableOffset", ValidationType.TYPE_FORMAT, "CustomisableOffset", path, "");
			})
			.collect(toList());
	}

}
