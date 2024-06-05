package cdm.product.asset.validation;

import cdm.product.asset.CorrelationReturnTerms;
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

public class CorrelationReturnTermsTypeFormatValidator implements Validator<CorrelationReturnTerms> {

	private List<ComparisonResult> getComparisonResults(CorrelationReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("annualizationFactor", o.getAnnualizationFactor(), empty(), of(0), empty(), empty()), 
				checkNumber("expectedN", o.getExpectedN(), empty(), of(0), empty(), empty()), 
				checkNumber("numberOfDataSeries", o.getNumberOfDataSeries(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<CorrelationReturnTerms> validate(RosettaPath path, CorrelationReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CorrelationReturnTerms", ValidationType.TYPE_FORMAT, "CorrelationReturnTerms", path, "", error);
		}
		return success("CorrelationReturnTerms", ValidationType.TYPE_FORMAT, "CorrelationReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CorrelationReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CorrelationReturnTerms", ValidationType.TYPE_FORMAT, "CorrelationReturnTerms", path, "", res.getError());
				}
				return success("CorrelationReturnTerms", ValidationType.TYPE_FORMAT, "CorrelationReturnTerms", path, "");
			})
			.collect(toList());
	}

}
