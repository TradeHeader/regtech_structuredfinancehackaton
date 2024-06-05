package cdm.event.common.validation.datarule;

import cdm.event.common.Valuation;
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
@RosettaDataRule("ValuationValuationType")
@ImplementedBy(ValuationValuationType.Default.class)
public interface ValuationValuationType extends Validator<Valuation> {
	
	String NAME = "ValuationValuationType";
	String DEFINITION = "required choice method, source";
	
	ValidationResult<Valuation> validate(RosettaPath path, Valuation valuation);
	
	class Default implements ValuationValuationType {
	
		@Override
		public ValidationResult<Valuation> validate(RosettaPath path, Valuation valuation) {
			ComparisonResult result = executeDataRule(valuation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Valuation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Valuation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Valuation valuation) {
			try {
				return choice(MapperS.of(valuation), Arrays.asList("method", "source"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ValuationValuationType {
	
		@Override
		public ValidationResult<Valuation> validate(RosettaPath path, Valuation valuation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Valuation", path, DEFINITION);
		}
	}
}
