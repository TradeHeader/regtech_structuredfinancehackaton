package cdm.product.asset.validation;

import cdm.product.asset.ReferenceInformation;
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

public class ReferenceInformationTypeFormatValidator implements Validator<ReferenceInformation> {

	private List<ComparisonResult> getComparisonResults(ReferenceInformation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReferenceInformation> validate(RosettaPath path, ReferenceInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferenceInformation", ValidationType.TYPE_FORMAT, "ReferenceInformation", path, "", error);
		}
		return success("ReferenceInformation", ValidationType.TYPE_FORMAT, "ReferenceInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferenceInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferenceInformation", ValidationType.TYPE_FORMAT, "ReferenceInformation", path, "", res.getError());
				}
				return success("ReferenceInformation", ValidationType.TYPE_FORMAT, "ReferenceInformation", path, "");
			})
			.collect(toList());
	}

}
