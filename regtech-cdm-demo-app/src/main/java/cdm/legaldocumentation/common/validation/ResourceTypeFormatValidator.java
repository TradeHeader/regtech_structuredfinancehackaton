package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.Resource;
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

public class ResourceTypeFormatValidator implements Validator<Resource> {

	private List<ComparisonResult> getComparisonResults(Resource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Resource> validate(RosettaPath path, Resource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Resource", ValidationType.TYPE_FORMAT, "Resource", path, "", error);
		}
		return success("Resource", ValidationType.TYPE_FORMAT, "Resource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Resource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Resource", ValidationType.TYPE_FORMAT, "Resource", path, "", res.getError());
				}
				return success("Resource", ValidationType.TYPE_FORMAT, "Resource", path, "");
			})
			.collect(toList());
	}

}
