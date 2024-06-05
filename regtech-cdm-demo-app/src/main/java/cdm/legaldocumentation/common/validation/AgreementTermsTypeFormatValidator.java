package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.AgreementTerms;
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

public class AgreementTermsTypeFormatValidator implements Validator<AgreementTerms> {

	private List<ComparisonResult> getComparisonResults(AgreementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AgreementTerms> validate(RosettaPath path, AgreementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AgreementTerms", ValidationType.TYPE_FORMAT, "AgreementTerms", path, "", error);
		}
		return success("AgreementTerms", ValidationType.TYPE_FORMAT, "AgreementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AgreementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AgreementTerms", ValidationType.TYPE_FORMAT, "AgreementTerms", path, "", res.getError());
				}
				return success("AgreementTerms", ValidationType.TYPE_FORMAT, "AgreementTerms", path, "");
			})
			.collect(toList());
	}

}
