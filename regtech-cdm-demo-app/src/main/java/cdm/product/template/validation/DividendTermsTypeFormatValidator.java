package cdm.product.template.validation;

import cdm.product.template.DividendTerms;
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

public class DividendTermsTypeFormatValidator implements Validator<DividendTerms> {

	private List<ComparisonResult> getComparisonResults(DividendTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DividendTerms> validate(RosettaPath path, DividendTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendTerms", ValidationType.TYPE_FORMAT, "DividendTerms", path, "", error);
		}
		return success("DividendTerms", ValidationType.TYPE_FORMAT, "DividendTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendTerms", ValidationType.TYPE_FORMAT, "DividendTerms", path, "", res.getError());
				}
				return success("DividendTerms", ValidationType.TYPE_FORMAT, "DividendTerms", path, "");
			})
			.collect(toList());
	}

}
