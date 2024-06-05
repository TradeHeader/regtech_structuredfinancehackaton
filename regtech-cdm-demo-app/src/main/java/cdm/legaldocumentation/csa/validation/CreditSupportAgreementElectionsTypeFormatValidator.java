package cdm.legaldocumentation.csa.validation;

import cdm.legaldocumentation.csa.CreditSupportAgreementElections;
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

public class CreditSupportAgreementElectionsTypeFormatValidator implements Validator<CreditSupportAgreementElections> {

	private List<ComparisonResult> getComparisonResults(CreditSupportAgreementElections o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CreditSupportAgreementElections> validate(RosettaPath path, CreditSupportAgreementElections o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditSupportAgreementElections", ValidationType.TYPE_FORMAT, "CreditSupportAgreementElections", path, "", error);
		}
		return success("CreditSupportAgreementElections", ValidationType.TYPE_FORMAT, "CreditSupportAgreementElections", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditSupportAgreementElections o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditSupportAgreementElections", ValidationType.TYPE_FORMAT, "CreditSupportAgreementElections", path, "", res.getError());
				}
				return success("CreditSupportAgreementElections", ValidationType.TYPE_FORMAT, "CreditSupportAgreementElections", path, "");
			})
			.collect(toList());
	}

}
