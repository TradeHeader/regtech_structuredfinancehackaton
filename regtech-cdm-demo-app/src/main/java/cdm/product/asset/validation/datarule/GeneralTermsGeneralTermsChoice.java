package cdm.product.asset.validation.datarule;

import cdm.product.asset.GeneralTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("GeneralTermsGeneralTermsChoice")
@ImplementedBy(GeneralTermsGeneralTermsChoice.Default.class)
public interface GeneralTermsGeneralTermsChoice extends Validator<GeneralTerms> {
	
	String NAME = "GeneralTermsGeneralTermsChoice";
	String DEFINITION = "required choice referenceInformation, indexReferenceInformation, basketReferenceInformation";
	
	ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms);
	
	class Default implements GeneralTermsGeneralTermsChoice {
	
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
				return choice(MapperS.of(generalTerms), Arrays.asList("referenceInformation", "indexReferenceInformation", "basketReferenceInformation"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements GeneralTermsGeneralTermsChoice {
	
		@Override
		public ValidationResult<GeneralTerms> validate(RosettaPath path, GeneralTerms generalTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "GeneralTerms", path, DEFINITION);
		}
	}
}
