package cdm.legaldocumentation.csa.validation;

import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
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

public class CollateralTransferAgreementElectionsTypeFormatValidator implements Validator<CollateralTransferAgreementElections> {

	private List<ComparisonResult> getComparisonResults(CollateralTransferAgreementElections o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralTransferAgreementElections> validate(RosettaPath path, CollateralTransferAgreementElections o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralTransferAgreementElections", ValidationType.TYPE_FORMAT, "CollateralTransferAgreementElections", path, "", error);
		}
		return success("CollateralTransferAgreementElections", ValidationType.TYPE_FORMAT, "CollateralTransferAgreementElections", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralTransferAgreementElections o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralTransferAgreementElections", ValidationType.TYPE_FORMAT, "CollateralTransferAgreementElections", path, "", res.getError());
				}
				return success("CollateralTransferAgreementElections", ValidationType.TYPE_FORMAT, "CollateralTransferAgreementElections", path, "");
			})
			.collect(toList());
	}

}
