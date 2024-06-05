package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.LegalAgreementIdentification;
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

public class LegalAgreementIdentificationTypeFormatValidator implements Validator<LegalAgreementIdentification> {

	private List<ComparisonResult> getComparisonResults(LegalAgreementIdentification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("vintage", o.getVintage(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<LegalAgreementIdentification> validate(RosettaPath path, LegalAgreementIdentification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LegalAgreementIdentification", ValidationType.TYPE_FORMAT, "LegalAgreementIdentification", path, "", error);
		}
		return success("LegalAgreementIdentification", ValidationType.TYPE_FORMAT, "LegalAgreementIdentification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalAgreementIdentification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalAgreementIdentification", ValidationType.TYPE_FORMAT, "LegalAgreementIdentification", path, "", res.getError());
				}
				return success("LegalAgreementIdentification", ValidationType.TYPE_FORMAT, "LegalAgreementIdentification", path, "");
			})
			.collect(toList());
	}

}
