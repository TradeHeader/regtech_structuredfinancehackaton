package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
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
@RosettaDataRule("AgreementNameCreditSupportAgreement")
@ImplementedBy(AgreementNameCreditSupportAgreement.Default.class)
public interface AgreementNameCreditSupportAgreement extends Validator<AgreementName> {
	
	String NAME = "AgreementNameCreditSupportAgreement";
	String DEFINITION = "if agreementType = LegalAgreementTypeEnum -> CreditSupportAgreement then creditSupportAgreementType exists";
	
	ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName);
	
	class Default implements AgreementNameCreditSupportAgreement {
	
		@Override
		public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName) {
			ComparisonResult result = executeDataRule(agreementName);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AgreementName", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AgreementName", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AgreementName agreementName) {
			try {
				if (areEqual(MapperS.of(agreementName).<LegalAgreementTypeEnum>map("getAgreementType", _agreementName -> _agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.CREDIT_SUPPORT_AGREEMENT), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(agreementName).<FieldWithMetaCreditSupportAgreementTypeEnum>map("getCreditSupportAgreementType", _agreementName -> _agreementName.getCreditSupportAgreementType()).<CreditSupportAgreementTypeEnum>map("getValue", _f->_f.getValue()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AgreementNameCreditSupportAgreement {
	
		@Override
		public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AgreementName", path, DEFINITION);
		}
	}
}
