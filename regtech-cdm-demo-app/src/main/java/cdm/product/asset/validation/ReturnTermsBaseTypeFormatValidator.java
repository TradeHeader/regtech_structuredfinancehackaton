package cdm.product.asset.validation;

import cdm.product.asset.ReturnTermsBase;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ReturnTermsBaseTypeFormatValidator implements Validator<ReturnTermsBase> {

	private List<ComparisonResult> getComparisonResults(ReturnTermsBase o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("annualizationFactor", o.getAnnualizationFactor(), empty(), of(0), empty(), empty()), 
				checkNumber("expectedN", o.getExpectedN(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<ReturnTermsBase> validate(RosettaPath path, ReturnTermsBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReturnTermsBase", ValidationType.TYPE_FORMAT, "ReturnTermsBase", path, "", error);
		}
		return success("ReturnTermsBase", ValidationType.TYPE_FORMAT, "ReturnTermsBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReturnTermsBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReturnTermsBase", ValidationType.TYPE_FORMAT, "ReturnTermsBase", path, "", res.getError());
				}
				return success("ReturnTermsBase", ValidationType.TYPE_FORMAT, "ReturnTermsBase", path, "");
			})
			.collect(toList());
	}

}
