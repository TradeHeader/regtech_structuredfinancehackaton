package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.LegalAgreementBase;
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

public class LegalAgreementBaseTypeFormatValidator implements Validator<LegalAgreementBase> {

	private List<ComparisonResult> getComparisonResults(LegalAgreementBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<LegalAgreementBase> validate(RosettaPath path, LegalAgreementBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LegalAgreementBase", ValidationType.TYPE_FORMAT, "LegalAgreementBase", path, "", error);
		}
		return success("LegalAgreementBase", ValidationType.TYPE_FORMAT, "LegalAgreementBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalAgreementBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalAgreementBase", ValidationType.TYPE_FORMAT, "LegalAgreementBase", path, "", res.getError());
				}
				return success("LegalAgreementBase", ValidationType.TYPE_FORMAT, "LegalAgreementBase", path, "");
			})
			.collect(toList());
	}

}
