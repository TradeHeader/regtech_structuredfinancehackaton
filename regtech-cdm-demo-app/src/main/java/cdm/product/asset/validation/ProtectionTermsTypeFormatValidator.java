package cdm.product.asset.validation;

import cdm.product.asset.ProtectionTerms;
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

public class ProtectionTermsTypeFormatValidator implements Validator<ProtectionTerms> {

	private List<ComparisonResult> getComparisonResults(ProtectionTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ProtectionTerms> validate(RosettaPath path, ProtectionTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ProtectionTerms", ValidationType.TYPE_FORMAT, "ProtectionTerms", path, "", error);
		}
		return success("ProtectionTerms", ValidationType.TYPE_FORMAT, "ProtectionTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ProtectionTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ProtectionTerms", ValidationType.TYPE_FORMAT, "ProtectionTerms", path, "", res.getError());
				}
				return success("ProtectionTerms", ValidationType.TYPE_FORMAT, "ProtectionTerms", path, "");
			})
			.collect(toList());
	}

}
