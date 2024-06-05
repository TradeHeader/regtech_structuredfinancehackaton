package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.LegalAgreement;
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

public class LegalAgreementTypeFormatValidator implements Validator<LegalAgreement> {

	private List<ComparisonResult> getComparisonResults(LegalAgreement o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LegalAgreement", ValidationType.TYPE_FORMAT, "LegalAgreement", path, "", error);
		}
		return success("LegalAgreement", ValidationType.TYPE_FORMAT, "LegalAgreement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalAgreement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalAgreement", ValidationType.TYPE_FORMAT, "LegalAgreement", path, "", res.getError());
				}
				return success("LegalAgreement", ValidationType.TYPE_FORMAT, "LegalAgreement", path, "");
			})
			.collect(toList());
	}

}
