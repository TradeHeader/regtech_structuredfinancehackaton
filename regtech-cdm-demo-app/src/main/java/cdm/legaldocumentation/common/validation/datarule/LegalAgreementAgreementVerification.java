package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import cdm.legaldocumentation.csa.CreditSupportAgreementElections;
import cdm.legaldocumentation.csa.SecurityAgreementElections;
import cdm.legaldocumentation.master.MasterAgreementSchedule;
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
@RosettaDataRule("LegalAgreementAgreementVerification")
@ImplementedBy(LegalAgreementAgreementVerification.Default.class)
public interface LegalAgreementAgreementVerification extends Validator<LegalAgreement> {
	
	String NAME = "LegalAgreementAgreementVerification";
	String DEFINITION = "if agreementTerms -> agreement -> securityAgreementElections exists then legalAgreementIdentification -> agreementName -> agreementType = LegalAgreementTypeEnum -> SecurityAgreement else if agreementTerms -> agreement -> creditSupportAgreementElections exists then legalAgreementIdentification -> agreementName -> creditSupportAgreementType = CreditSupportAgreementTypeEnum -> CreditSupportAnnex or legalAgreementIdentification -> agreementName -> creditSupportAgreementType = CreditSupportAgreementTypeEnum -> CreditSupportDeed else if agreementTerms -> agreement -> collateralTransferAgreementElections exists then legalAgreementIdentification -> agreementName -> creditSupportAgreementType = CreditSupportAgreementTypeEnum -> CollateralTransferAgreement else if agreementTerms -> agreement -> masterAgreementSchedule exists then legalAgreementIdentification -> agreementName -> agreementType = LegalAgreementTypeEnum -> MasterAgreement";
	
	ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement);
	
	class Default implements LegalAgreementAgreementVerification {
	
		@Override
		public ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement) {
			ComparisonResult result = executeDataRule(legalAgreement);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(LegalAgreement legalAgreement) {
			try {
				if (exists(MapperS.of(legalAgreement).<AgreementTerms>map("getAgreementTerms", _legalAgreement -> _legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<SecurityAgreementElections>map("getSecurityAgreementElections", agreement -> agreement.getSecurityAgreementElections())).getOrDefault(false)) {
					return areEqual(MapperS.of(legalAgreement).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<LegalAgreementTypeEnum>map("getAgreementType", agreementName -> agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.SECURITY_AGREEMENT), CardinalityOperator.All);
				}
				if (exists(MapperS.of(legalAgreement).<AgreementTerms>map("getAgreementTerms", _legalAgreement -> _legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<CreditSupportAgreementElections>map("getCreditSupportAgreementElections", agreement -> agreement.getCreditSupportAgreementElections())).getOrDefault(false)) {
					return areEqual(MapperS.of(legalAgreement).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<FieldWithMetaCreditSupportAgreementTypeEnum>map("getCreditSupportAgreementType", agreementName -> agreementName.getCreditSupportAgreementType()).<CreditSupportAgreementTypeEnum>map("getValue", _f->_f.getValue()), MapperS.of(CreditSupportAgreementTypeEnum.CREDIT_SUPPORT_ANNEX), CardinalityOperator.All).or(areEqual(MapperS.of(legalAgreement).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<FieldWithMetaCreditSupportAgreementTypeEnum>map("getCreditSupportAgreementType", agreementName -> agreementName.getCreditSupportAgreementType()).<CreditSupportAgreementTypeEnum>map("getValue", _f->_f.getValue()), MapperS.of(CreditSupportAgreementTypeEnum.CREDIT_SUPPORT_DEED), CardinalityOperator.All));
				}
				if (exists(MapperS.of(legalAgreement).<AgreementTerms>map("getAgreementTerms", _legalAgreement -> _legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<CollateralTransferAgreementElections>map("getCollateralTransferAgreementElections", agreement -> agreement.getCollateralTransferAgreementElections())).getOrDefault(false)) {
					return areEqual(MapperS.of(legalAgreement).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<FieldWithMetaCreditSupportAgreementTypeEnum>map("getCreditSupportAgreementType", agreementName -> agreementName.getCreditSupportAgreementType()).<CreditSupportAgreementTypeEnum>map("getValue", _f->_f.getValue()), MapperS.of(CreditSupportAgreementTypeEnum.COLLATERAL_TRANSFER_AGREEMENT), CardinalityOperator.All);
				}
				if (exists(MapperS.of(legalAgreement).<AgreementTerms>map("getAgreementTerms", _legalAgreement -> _legalAgreement.getAgreementTerms()).<Agreement>map("getAgreement", agreementTerms -> agreementTerms.getAgreement()).<MasterAgreementSchedule>map("getMasterAgreementSchedule", agreement -> agreement.getMasterAgreementSchedule())).getOrDefault(false)) {
					return areEqual(MapperS.of(legalAgreement).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreementBase -> legalAgreementBase.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<LegalAgreementTypeEnum>map("getAgreementType", agreementName -> agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.MASTER_AGREEMENT), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements LegalAgreementAgreementVerification {
	
		@Override
		public ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION);
		}
	}
}
