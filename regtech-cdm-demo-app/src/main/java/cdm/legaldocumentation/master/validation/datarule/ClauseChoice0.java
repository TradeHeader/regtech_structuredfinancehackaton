package cdm.legaldocumentation.master.validation.datarule;

import cdm.legaldocumentation.master.Clause;
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
@RosettaDataRule("ClauseChoice0")
@ImplementedBy(ClauseChoice0.Default.class)
public interface ClauseChoice0 extends Validator<Clause> {
	
	String NAME = "ClauseChoice0";
	String DEFINITION = "optional choice terms, subcomponents";
	
	ValidationResult<Clause> validate(RosettaPath path, Clause clause);
	
	class Default implements ClauseChoice0 {
	
		@Override
		public ValidationResult<Clause> validate(RosettaPath path, Clause clause) {
			ComparisonResult result = executeDataRule(clause);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Clause", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Clause", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Clause clause) {
			try {
				return choice(MapperS.of(clause), Arrays.asList("terms", "subcomponents"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ClauseChoice0 {
	
		@Override
		public ValidationResult<Clause> validate(RosettaPath path, Clause clause) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Clause", path, DEFINITION);
		}
	}
}
