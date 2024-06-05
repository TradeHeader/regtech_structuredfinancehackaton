package cdm.product.template.validation;

import cdm.product.template.ReturnTerms;
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

public class ReturnTermsTypeFormatValidator implements Validator<ReturnTerms> {

	private List<ComparisonResult> getComparisonResults(ReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReturnTerms> validate(RosettaPath path, ReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReturnTerms", ValidationType.TYPE_FORMAT, "ReturnTerms", path, "", error);
		}
		return success("ReturnTerms", ValidationType.TYPE_FORMAT, "ReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReturnTerms", ValidationType.TYPE_FORMAT, "ReturnTerms", path, "", res.getError());
				}
				return success("ReturnTerms", ValidationType.TYPE_FORMAT, "ReturnTerms", path, "");
			})
			.collect(toList());
	}

}
