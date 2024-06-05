package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.ContractualDefinitionsEnum;
import cdm.legaldocumentation.common.ContractualMatrix;
import cdm.legaldocumentation.common.ContractualTermsSupplement;
import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualDefinitionsEnum;
import cdm.legaldocumentation.master.MasterAgreementTypeEnum;
import cdm.legaldocumentation.master.MasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.MasterConfirmationTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterAgreementTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum;
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
@RosettaDataRule("AgreementNameAgreementType")
@ImplementedBy(AgreementNameAgreementType.Default.class)
public interface AgreementNameAgreementType extends Validator<AgreementName> {
	
	String NAME = "AgreementNameAgreementType";
	String DEFINITION = "if agreementType <> LegalAgreementTypeEnum -> Other then otherAgreement is absent else if agreementType <> LegalAgreementTypeEnum -> MasterAgreement then masterAgreementType is absent else if agreementType <> LegalAgreementTypeEnum -> MasterConfirmation then masterConfirmationType is absent and masterConfirmationAnnexType is absent else if agreementType <> LegalAgreementTypeEnum -> Confirmation then contractualDefinitionsType is absent and contractualTermsSupplement is absent and contractualMatrix is absent";
	
	ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName);
	
	class Default implements AgreementNameAgreementType {
	
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
				if (notEqual(MapperS.of(agreementName).<LegalAgreementTypeEnum>map("getAgreementType", _agreementName -> _agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.OTHER), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(agreementName).<String>map("getOtherAgreement", _agreementName -> _agreementName.getOtherAgreement()));
				}
				if (notEqual(MapperS.of(agreementName).<LegalAgreementTypeEnum>map("getAgreementType", _agreementName -> _agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.MASTER_AGREEMENT), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(agreementName).<FieldWithMetaMasterAgreementTypeEnum>map("getMasterAgreementType", _agreementName -> _agreementName.getMasterAgreementType()).<MasterAgreementTypeEnum>map("getValue", _f->_f.getValue()));
				}
				if (notEqual(MapperS.of(agreementName).<LegalAgreementTypeEnum>map("getAgreementType", _agreementName -> _agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.MASTER_CONFIRMATION), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(agreementName).<FieldWithMetaMasterConfirmationTypeEnum>map("getMasterConfirmationType", _agreementName -> _agreementName.getMasterConfirmationType()).<MasterConfirmationTypeEnum>map("getValue", _f->_f.getValue())).and(notExists(MapperS.of(agreementName).<FieldWithMetaMasterConfirmationAnnexTypeEnum>map("getMasterConfirmationAnnexType", _agreementName -> _agreementName.getMasterConfirmationAnnexType()).<MasterConfirmationAnnexTypeEnum>map("getValue", _f->_f.getValue())));
				}
				if (notEqual(MapperS.of(agreementName).<LegalAgreementTypeEnum>map("getAgreementType", _agreementName -> _agreementName.getAgreementType()), MapperS.of(LegalAgreementTypeEnum.CONFIRMATION), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(agreementName).<FieldWithMetaContractualDefinitionsEnum>mapC("getContractualDefinitionsType", _agreementName -> _agreementName.getContractualDefinitionsType()).<ContractualDefinitionsEnum>map("getValue", _f->_f.getValue())).and(notExists(MapperS.of(agreementName).<ContractualTermsSupplement>mapC("getContractualTermsSupplement", _agreementName -> _agreementName.getContractualTermsSupplement()))).and(notExists(MapperS.of(agreementName).<ContractualMatrix>mapC("getContractualMatrix", _agreementName -> _agreementName.getContractualMatrix())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AgreementNameAgreementType {
	
		@Override
		public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AgreementName", path, DEFINITION);
		}
	}
}
