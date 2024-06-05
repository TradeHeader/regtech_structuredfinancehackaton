package cdm.legaldocumentation.csa.validation;

import cdm.legaldocumentation.csa.SecurityAgreementElections;
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

public class SecurityAgreementElectionsValidator implements Validator<SecurityAgreementElections> {

	private List<ComparisonResult> getComparisonResults(SecurityAgreementElections o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityAgreementElections> validate(RosettaPath path, SecurityAgreementElections o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityAgreementElections", ValidationType.CARDINALITY, "SecurityAgreementElections", path, "", error);
		}
		return success("SecurityAgreementElections", ValidationType.CARDINALITY, "SecurityAgreementElections", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityAgreementElections o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityAgreementElections", ValidationType.CARDINALITY, "SecurityAgreementElections", path, "", res.getError());
				}
				return success("SecurityAgreementElections", ValidationType.CARDINALITY, "SecurityAgreementElections", path, "");
			})
			.collect(toList());
	}

}
