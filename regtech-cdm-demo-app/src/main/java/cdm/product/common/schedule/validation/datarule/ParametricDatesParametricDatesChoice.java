package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.ParametricDates;
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
@RosettaDataRule("ParametricDatesParametricDatesChoice")
@ImplementedBy(ParametricDatesParametricDatesChoice.Default.class)
public interface ParametricDatesParametricDatesChoice extends Validator<ParametricDates> {
	
	String NAME = "ParametricDatesParametricDatesChoice";
	String DEFINITION = "required choice dayDistribution, dayOfWeek";
	
	ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates);
	
	class Default implements ParametricDatesParametricDatesChoice {
	
		@Override
		public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates) {
			ComparisonResult result = executeDataRule(parametricDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ParametricDates parametricDates) {
			try {
				return choice(MapperS.of(parametricDates), Arrays.asList("dayDistribution", "dayOfWeek"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ParametricDatesParametricDatesChoice {
	
		@Override
		public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION);
		}
	}
}
