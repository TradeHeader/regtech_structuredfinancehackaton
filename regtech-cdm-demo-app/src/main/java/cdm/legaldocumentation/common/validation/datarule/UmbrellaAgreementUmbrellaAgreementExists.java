package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.UmbrellaAgreement;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
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
@RosettaDataRule("UmbrellaAgreementUmbrellaAgreementExists")
@ImplementedBy(UmbrellaAgreementUmbrellaAgreementExists.Default.class)
public interface UmbrellaAgreementUmbrellaAgreementExists extends Validator<UmbrellaAgreement> {
	
	String NAME = "UmbrellaAgreementUmbrellaAgreementExists";
	String DEFINITION = "if isApplicable = True then language exists and parties exists";
	
	ValidationResult<UmbrellaAgreement> validate(RosettaPath path, UmbrellaAgreement umbrellaAgreement);
	
	class Default implements UmbrellaAgreementUmbrellaAgreementExists {
	
		@Override
		public ValidationResult<UmbrellaAgreement> validate(RosettaPath path, UmbrellaAgreement umbrellaAgreement) {
			ComparisonResult result = executeDataRule(umbrellaAgreement);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "UmbrellaAgreement", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "UmbrellaAgreement", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(UmbrellaAgreement umbrellaAgreement) {
			try {
				if (areEqual(MapperS.of(umbrellaAgreement).<Boolean>map("getIsApplicable", _umbrellaAgreement -> _umbrellaAgreement.getIsApplicable()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(umbrellaAgreement).<String>map("getLanguage", _umbrellaAgreement -> _umbrellaAgreement.getLanguage())).and(exists(MapperS.of(umbrellaAgreement).<UmbrellaAgreementEntity>mapC("getParties", _umbrellaAgreement -> _umbrellaAgreement.getParties())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements UmbrellaAgreementUmbrellaAgreementExists {
	
		@Override
		public ValidationResult<UmbrellaAgreement> validate(RosettaPath path, UmbrellaAgreement umbrellaAgreement) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "UmbrellaAgreement", path, DEFINITION);
		}
	}
}
