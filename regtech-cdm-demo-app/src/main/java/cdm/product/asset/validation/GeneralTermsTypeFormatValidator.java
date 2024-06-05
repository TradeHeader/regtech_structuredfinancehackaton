package cdm.product.asset.validation;

import cdm.product.asset.GeneralTerms;
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

public class GeneralTermsTypeFormatValidator implements Validator<GeneralTerms> {

	private List<ComparisonResult> getComparisonResults(GeneralTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("GeneralTerms", ValidationType.TYPE_FORMAT, "GeneralTerms", path, "", error);
		}
		return success("GeneralTerms", ValidationType.TYPE_FORMAT, "GeneralTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, GeneralTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("GeneralTerms", ValidationType.TYPE_FORMAT, "GeneralTerms", path, "", res.getError());
				}
				return success("GeneralTerms", ValidationType.TYPE_FORMAT, "GeneralTerms", path, "");
			})
			.collect(toList());
	}

}
