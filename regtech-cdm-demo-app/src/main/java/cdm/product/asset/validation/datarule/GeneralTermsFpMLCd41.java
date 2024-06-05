package cdm.product.asset.validation.datarule;

import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.Tranche;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("GeneralTermsFpML_cd_41")
@ImplementedBy(GeneralTermsFpMLCd41.Default.class)
public interface GeneralTermsFpMLCd41 extends Validator<GeneralTerms> {
	
	String NAME = "GeneralTermsFpML_cd_41";
	String DEFINITION = "if indexReferenceInformation -> tranche is absent then modifiedEquityDelivery is absent";
	
	ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms);
	
	class Default implements GeneralTermsFpMLCd41 {
	
		@Override
		public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms) {
			ComparisonResult result = executeDataRule(generalTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(GeneralTerms generalTerms) {
			try {
				if (notExists(MapperS.of(generalTerms).<CreditIndexReferenceInformation>map("getIndexReferenceInformation", _generalTerms -> _generalTerms.getIndexReferenceInformation()).<Tranche>map("getTranche", creditIndexReferenceInformation -> creditIndexReferenceInformation.getTranche())).getOrDefault(false)) {
					return notExists(MapperS.of(generalTerms).<Boolean>map("getModifiedEquityDelivery", _generalTerms -> _generalTerms.getModifiedEquityDelivery()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements GeneralTermsFpMLCd41 {
	
		@Override
		public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION);
		}
	}
}
