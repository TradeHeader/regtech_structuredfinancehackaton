package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.CalculationAgent;
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
@RosettaDataRule("CalculationAgentCalculationAgentChoice")
@ImplementedBy(CalculationAgentCalculationAgentChoice.Default.class)
public interface CalculationAgentCalculationAgentChoice extends Validator<CalculationAgent> {
	
	String NAME = "CalculationAgentCalculationAgentChoice";
	String DEFINITION = "optional choice calculationAgentParty, calculationAgentPartyEnum";
	
	ValidationResult<CalculationAgent> validate(RosettaPath path, CalculationAgent calculationAgent);
	
	class Default implements CalculationAgentCalculationAgentChoice {
	
		@Override
		public ValidationResult<CalculationAgent> validate(RosettaPath path, CalculationAgent calculationAgent) {
			ComparisonResult result = executeDataRule(calculationAgent);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationAgent", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CalculationAgent", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CalculationAgent calculationAgent) {
			try {
				return choice(MapperS.of(calculationAgent), Arrays.asList("calculationAgentParty", "calculationAgentPartyEnum"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CalculationAgentCalculationAgentChoice {
	
		@Override
		public ValidationResult<CalculationAgent> validate(RosettaPath path, CalculationAgent calculationAgent) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationAgent", path, DEFINITION);
		}
	}
}
