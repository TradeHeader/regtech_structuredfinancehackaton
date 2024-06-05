package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum;
import cdm.product.collateral.CollateralMarginTypeEnum;
import cdm.product.collateral.metafields.FieldWithMetaCreditSupportAgreementTypeEnum;
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

public class AgreementNameValidator implements Validator<AgreementName> {

	private List<ComparisonResult> getComparisonResults(AgreementName o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("agreementType", (LegalAgreementTypeEnum) o.getAgreementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("creditSupportAgreementType", (FieldWithMetaCreditSupportAgreementTypeEnum) o.getCreditSupportAgreementType() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditSupportAgreementMarginType", (CollateralMarginTypeEnum) o.getCreditSupportAgreementMarginType() != null ? 1 : 0, 0, 1), 
				checkCardinality("masterAgreementType", (FieldWithMetaMasterAgreementTypeEnum) o.getMasterAgreementType() != null ? 1 : 0, 0, 1), 
				checkCardinality("masterConfirmationType", (FieldWithMetaMasterConfirmationTypeEnum) o.getMasterConfirmationType() != null ? 1 : 0, 0, 1), 
				checkCardinality("masterConfirmationAnnexType", (FieldWithMetaMasterConfirmationAnnexTypeEnum) o.getMasterConfirmationAnnexType() != null ? 1 : 0, 0, 1), 
				checkCardinality("otherAgreement", (String) o.getOtherAgreement() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AgreementName", ValidationType.CARDINALITY, "AgreementName", path, "", error);
		}
		return success("AgreementName", ValidationType.CARDINALITY, "AgreementName", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AgreementName o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AgreementName", ValidationType.CARDINALITY, "AgreementName", path, "", res.getError());
				}
				return success("AgreementName", ValidationType.CARDINALITY, "AgreementName", path, "");
			})
			.collect(toList());
	}

}
