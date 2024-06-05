package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.OtherAgreementTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("OtherAgreementTermsLegalDocumentSpecified")
@ImplementedBy(OtherAgreementTermsLegalDocumentSpecified.Default.class)
public interface OtherAgreementTermsLegalDocumentSpecified extends Validator<OtherAgreementTerms> {
	
	String NAME = "OtherAgreementTermsLegalDocumentSpecified";
	String DEFINITION = "if isSpecified = True then legalDocument exists";
	
	ValidationResult<OtherAgreementTerms> validate(RosettaPath path, OtherAgreementTerms otherAgreementTerms);
	
	class Default implements OtherAgreementTermsLegalDocumentSpecified {
	
		@Override
		public ValidationResult<OtherAgreementTerms> validate(RosettaPath path, OtherAgreementTerms otherAgreementTerms) {
			ComparisonResult result = executeDataRule(otherAgreementTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OtherAgreementTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OtherAgreementTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OtherAgreementTerms otherAgreementTerms) {
			try {
				if (areEqual(MapperS.of(otherAgreementTerms).<Boolean>map("getIsSpecified", _otherAgreementTerms -> _otherAgreementTerms.getIsSpecified()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(otherAgreementTerms).<String>map("getLegalDocument", _otherAgreementTerms -> _otherAgreementTerms.getLegalDocument()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OtherAgreementTermsLegalDocumentSpecified {
	
		@Override
		public ValidationResult<OtherAgreementTerms> validate(RosettaPath path, OtherAgreementTerms otherAgreementTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OtherAgreementTerms", path, DEFINITION);
		}
	}
}
