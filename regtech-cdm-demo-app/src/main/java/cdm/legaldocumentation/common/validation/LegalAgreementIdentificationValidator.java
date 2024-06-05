package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.GoverningLawEnum;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementPublisherEnum;
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

public class LegalAgreementIdentificationValidator implements Validator<LegalAgreementIdentification> {

	private List<ComparisonResult> getComparisonResults(LegalAgreementIdentification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("governingLaw", (GoverningLawEnum) o.getGoverningLaw() != null ? 1 : 0, 0, 1), 
				checkCardinality("agreementName", (AgreementName) o.getAgreementName() != null ? 1 : 0, 1, 1), 
				checkCardinality("publisher", (LegalAgreementPublisherEnum) o.getPublisher() != null ? 1 : 0, 0, 1), 
				checkCardinality("vintage", (Integer) o.getVintage() != null ? 1 : 0, 0, 1)
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
			return failure("LegalAgreementIdentification", ValidationType.CARDINALITY, "LegalAgreementIdentification", path, "", error);
		}
		return success("LegalAgreementIdentification", ValidationType.CARDINALITY, "LegalAgreementIdentification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LegalAgreementIdentification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LegalAgreementIdentification", ValidationType.CARDINALITY, "LegalAgreementIdentification", path, "", res.getError());
				}
				return success("LegalAgreementIdentification", ValidationType.CARDINALITY, "LegalAgreementIdentification", path, "");
			})
			.collect(toList());
	}

}
