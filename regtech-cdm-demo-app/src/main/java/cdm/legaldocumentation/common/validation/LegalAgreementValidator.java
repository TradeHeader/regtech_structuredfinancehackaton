package cdm.legaldocumentation.common.validation;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.UmbrellaAgreement;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class LegalAgreementValidator implements Validator<LegalAgreement> {

	private List<ComparisonResult> getComparisonResults(LegalAgreement o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("agreementDate", (Date) o.getAgreementDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("effectiveDate", (Date) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("legalAgreementIdentification", (LegalAgreementIdentification) o.getLegalAgreementIdentification() != null ? 1 : 0, 1, 1), 
				checkCardinality("contractualParty", (List<? extends ReferenceWithMetaParty>) o.getContractualParty() == null ? 0 : ((List<? extends ReferenceWithMetaParty>) o.getContractualParty()).size(), 2, 2), 
				checkCardinality("agreementTerms", (AgreementTerms) o.getAgreementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("umbrellaAgreement", (UmbrellaAgreement) o.getUmbrellaAgreement() != null ? 1 : 0, 0, 1)
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
			return failure("LegalAgreement", ValidationType.CARDINALITY, "LegalAgreement", path, "", error);
		}
		return success("LegalAgreement", ValidationType.CARDINALITY, "LegalAgreement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalAgreement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalAgreement", ValidationType.CARDINALITY, "LegalAgreement", path, "", res.getError());
				}
				return success("LegalAgreement", ValidationType.CARDINALITY, "LegalAgreement", path, "");
			})
			.collect(toList());
	}

}
