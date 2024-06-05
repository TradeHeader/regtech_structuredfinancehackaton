package cdm.product.asset.validation;

import cdm.product.asset.PriceReturnTerms;
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

public class PriceReturnTermsTypeFormatValidator implements Validator<PriceReturnTerms> {

	private List<ComparisonResult> getComparisonResults(PriceReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PriceReturnTerms> validate(RosettaPath path, PriceReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceReturnTerms", ValidationType.TYPE_FORMAT, "PriceReturnTerms", path, "", error);
		}
		return success("PriceReturnTerms", ValidationType.TYPE_FORMAT, "PriceReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceReturnTerms", ValidationType.TYPE_FORMAT, "PriceReturnTerms", path, "", res.getError());
				}
				return success("PriceReturnTerms", ValidationType.TYPE_FORMAT, "PriceReturnTerms", path, "");
			})
			.collect(toList());
	}

}
