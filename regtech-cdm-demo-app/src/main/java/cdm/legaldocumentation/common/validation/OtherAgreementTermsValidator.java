package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.OtherAgreementTerms;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OtherAgreementTermsValidator implements Validator<OtherAgreementTerms> {

	private List<ComparisonResult> getComparisonResults(OtherAgreementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("isSpecified", (Boolean) o.getIsSpecified() != null ? 1 : 0, 1, 1), 
				checkCardinality("legalDocument", (String) o.getLegalDocument() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OtherAgreementTerms> validate(RosettaPath path, OtherAgreementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OtherAgreementTerms", ValidationType.CARDINALITY, "OtherAgreementTerms", path, "", error);
		}
		return success("OtherAgreementTerms", ValidationType.CARDINALITY, "OtherAgreementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OtherAgreementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OtherAgreementTerms", ValidationType.CARDINALITY, "OtherAgreementTerms", path, "", res.getError());
				}
				return success("OtherAgreementTerms", ValidationType.CARDINALITY, "OtherAgreementTerms", path, "");
			})
			.collect(toList());
	}

}
