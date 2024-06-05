package cdm.product.asset.validation;

import cdm.product.asset.VolatilityReturnTerms;
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

public class VolatilityReturnTermsTypeFormatValidator implements Validator<VolatilityReturnTerms> {

	private List<ComparisonResult> getComparisonResults(VolatilityReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("annualizationFactor", o.getAnnualizationFactor(), empty(), of(0), empty(), empty()), 
				checkNumber("expectedN", o.getExpectedN(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<VolatilityReturnTerms> validate(RosettaPath path, VolatilityReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("VolatilityReturnTerms", ValidationType.TYPE_FORMAT, "VolatilityReturnTerms", path, "", error);
		}
		return success("VolatilityReturnTerms", ValidationType.TYPE_FORMAT, "VolatilityReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, VolatilityReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("VolatilityReturnTerms", ValidationType.TYPE_FORMAT, "VolatilityReturnTerms", path, "", res.getError());
				}
				return success("VolatilityReturnTerms", ValidationType.TYPE_FORMAT, "VolatilityReturnTerms", path, "");
			})
			.collect(toList());
	}

}
