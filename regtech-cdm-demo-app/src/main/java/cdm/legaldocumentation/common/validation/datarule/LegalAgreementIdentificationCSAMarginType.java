package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.product.collateral.CollateralMarginTypeEnum;
import cdm.product.collateral.CreditSupportAgreementTypeEnum;
import cdm.product.collateral.metafields.FieldWithMetaCreditSupportAgreementTypeEnum;
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
@RosettaDataRule("LegalAgreementIdentificationCSAMarginType")
@ImplementedBy(LegalAgreementIdentificationCSAMarginType.Default.class)
public interface LegalAgreementIdentificationCSAMarginType extends Validator<LegalAgreementIdentification> {
	
	String NAME = "LegalAgreementIdentificationCSAMarginType";
	String DEFINITION = "if agreementName -> creditSupportAgreementMarginType exists then agreementName -> creditSupportAgreementType exists and vintage >= 2016";
	
	ValidationResult<LegalAgreementIdentification> validate(RosettaPath path, LegalAgreementIdentification legalAgreementIdentification);
	
	class Default implements LegalAgreementIdentificationCSAMarginType {
	
		@Override
		public ValidationResult<LegalAgreementIdentification> validate(RosettaPath path, LegalAgreementIdentification legalAgreementIdentification) {
			ComparisonResult result = executeDataRule(legalAgreementIdentification);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreementIdentification", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "LegalAgreementIdentification", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(LegalAgreementIdentification legalAgreementIdentification) {
			try {
				if (exists(MapperS.of(legalAgreementIdentification).<AgreementName>map("getAgreementName", _legalAgreementIdentification -> _legalAgreementIdentification.getAgreementName()).<CollateralMarginTypeEnum>map("getCreditSupportAgreementMarginType", agreementName -> agreementName.getCreditSupportAgreementMarginType())).getOrDefault(false)) {
					return exists(MapperS.of(legalAgreementIdentification).<AgreementName>map("getAgreementName", _legalAgreementIdentification -> _legalAgreementIdentification.getAgreementName()).<FieldWithMetaCreditSupportAgreementTypeEnum>map("getCreditSupportAgreementType", agreementName -> agreementName.getCreditSupportAgreementType()).<CreditSupportAgreementTypeEnum>map("getValue", _f->_f.getValue())).and(greaterThanEquals(MapperS.of(legalAgreementIdentification).<Integer>map("getVintage", _legalAgreementIdentification -> _legalAgreementIdentification.getVintage()), MapperS.of(2016), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements LegalAgreementIdentificationCSAMarginType {
	
		@Override
		public ValidationResult<LegalAgreementIdentification> validate(RosettaPath path, LegalAgreementIdentification legalAgreementIdentification) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreementIdentification", path, DEFINITION);
		}
	}
}
