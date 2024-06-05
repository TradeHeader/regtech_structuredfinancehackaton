package cdm.product.template.validation;

import cdm.product.template.PortfolioReturnTerms;
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

public class PortfolioReturnTermsTypeFormatValidator implements Validator<PortfolioReturnTerms> {

	private List<ComparisonResult> getComparisonResults(PortfolioReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PortfolioReturnTerms> validate(RosettaPath path, PortfolioReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PortfolioReturnTerms", ValidationType.TYPE_FORMAT, "PortfolioReturnTerms", path, "", error);
		}
		return success("PortfolioReturnTerms", ValidationType.TYPE_FORMAT, "PortfolioReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PortfolioReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PortfolioReturnTerms", ValidationType.TYPE_FORMAT, "PortfolioReturnTerms", path, "", res.getError());
				}
				return success("PortfolioReturnTerms", ValidationType.TYPE_FORMAT, "PortfolioReturnTerms", path, "");
			})
			.collect(toList());
	}

}
