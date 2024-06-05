package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.CreditNotations;
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
@RosettaDataRule("CreditNotationsOneOf0")
@ImplementedBy(CreditNotationsOneOf0.Default.class)
public interface CreditNotationsOneOf0 extends Validator<CreditNotations> {
	
	String NAME = "CreditNotationsOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<CreditNotations> validate(RosettaPath path, CreditNotations creditNotations);
	
	class Default implements CreditNotationsOneOf0 {
	
		@Override
		public ValidationResult<CreditNotations> validate(RosettaPath path, CreditNotations creditNotations) {
			ComparisonResult result = executeDataRule(creditNotations);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditNotations", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CreditNotations", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CreditNotations creditNotations) {
			try {
				return choice(MapperS.of(creditNotations), Arrays.asList("creditNotation", "creditNotations"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CreditNotationsOneOf0 {
	
		@Override
		public ValidationResult<CreditNotations> validate(RosettaPath path, CreditNotations creditNotations) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CreditNotations", path, DEFINITION);
		}
	}
}
