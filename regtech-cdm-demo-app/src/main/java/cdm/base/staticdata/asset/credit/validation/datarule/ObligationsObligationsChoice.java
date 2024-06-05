package cdm.base.staticdata.asset.credit.validation.datarule;

import cdm.base.staticdata.asset.credit.Obligations;
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
@RosettaDataRule("ObligationsObligationsChoice")
@ImplementedBy(ObligationsObligationsChoice.Default.class)
public interface ObligationsObligationsChoice extends Validator<Obligations> {
	
	String NAME = "ObligationsObligationsChoice";
	String DEFINITION = "optional choice fullFaithAndCreditObLiability, generalFundObligationLiability, revenueObligationLiability";
	
	ValidationResult<Obligations> validate(RosettaPath path, Obligations obligations);
	
	class Default implements ObligationsObligationsChoice {
	
		@Override
		public ValidationResult<Obligations> validate(RosettaPath path, Obligations obligations) {
			ComparisonResult result = executeDataRule(obligations);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Obligations", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Obligations", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Obligations obligations) {
			try {
				return choice(MapperS.of(obligations), Arrays.asList("fullFaithAndCreditObLiability", "generalFundObligationLiability", "revenueObligationLiability"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ObligationsObligationsChoice {
	
		@Override
		public ValidationResult<Obligations> validate(RosettaPath path, Obligations obligations) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Obligations", path, DEFINITION);
		}
	}
}
