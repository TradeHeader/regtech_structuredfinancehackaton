package cdm.event.workflow.validation.datarule;

import cdm.event.workflow.LimitApplicable;
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
@RosettaDataRule("LimitApplicableLimitApplicableChoice")
@ImplementedBy(LimitApplicableLimitApplicableChoice.Default.class)
public interface LimitApplicableLimitApplicableChoice extends Validator<LimitApplicable> {
	
	String NAME = "LimitApplicableLimitApplicableChoice";
	String DEFINITION = "optional choice amountUtilized, utilization";
	
	ValidationResult<LimitApplicable> validate(RosettaPath path, LimitApplicable limitApplicable);
	
	class Default implements LimitApplicableLimitApplicableChoice {
	
		@Override
		public ValidationResult<LimitApplicable> validate(RosettaPath path, LimitApplicable limitApplicable) {
			ComparisonResult result = executeDataRule(limitApplicable);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LimitApplicable", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "LimitApplicable", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(LimitApplicable limitApplicable) {
			try {
				return choice(MapperS.of(limitApplicable), Arrays.asList("amountUtilized", "utilization"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements LimitApplicableLimitApplicableChoice {
	
		@Override
		public ValidationResult<LimitApplicable> validate(RosettaPath path, LimitApplicable limitApplicable) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LimitApplicable", path, DEFINITION);
		}
	}
}
