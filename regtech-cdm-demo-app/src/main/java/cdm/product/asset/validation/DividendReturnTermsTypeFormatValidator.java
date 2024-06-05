package cdm.product.asset.validation;

import cdm.product.asset.DividendReturnTerms;
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

public class DividendReturnTermsTypeFormatValidator implements Validator<DividendReturnTerms> {

	private List<ComparisonResult> getComparisonResults(DividendReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendReturnTerms", ValidationType.TYPE_FORMAT, "DividendReturnTerms", path, "", error);
		}
		return success("DividendReturnTerms", ValidationType.TYPE_FORMAT, "DividendReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendReturnTerms", ValidationType.TYPE_FORMAT, "DividendReturnTerms", path, "", res.getError());
				}
				return success("DividendReturnTerms", ValidationType.TYPE_FORMAT, "DividendReturnTerms", path, "");
			})
			.collect(toList());
	}

}
