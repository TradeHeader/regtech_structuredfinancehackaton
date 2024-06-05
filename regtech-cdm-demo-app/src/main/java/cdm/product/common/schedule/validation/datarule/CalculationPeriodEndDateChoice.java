package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.CalculationPeriod;
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
@RosettaDataRule("CalculationPeriodEndDateChoice")
@ImplementedBy(CalculationPeriodEndDateChoice.Default.class)
public interface CalculationPeriodEndDateChoice extends Validator<CalculationPeriod> {
	
	String NAME = "CalculationPeriodEndDateChoice";
	String DEFINITION = "required choice adjustedEndDate, unadjustedEndDate";
	
	ValidationResult<CalculationPeriod> validate(RosettaPath path, CalculationPeriod calculationPeriod);
	
	class Default implements CalculationPeriodEndDateChoice {
	
		@Override
		public ValidationResult<CalculationPeriod> validate(RosettaPath path, CalculationPeriod calculationPeriod) {
			ComparisonResult result = executeDataRule(calculationPeriod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationPeriod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CalculationPeriod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CalculationPeriod calculationPeriod) {
			try {
				return choice(MapperS.of(calculationPeriod), Arrays.asList("adjustedEndDate", "unadjustedEndDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CalculationPeriodEndDateChoice {
	
		@Override
		public ValidationResult<CalculationPeriod> validate(RosettaPath path, CalculationPeriod calculationPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CalculationPeriod", path, DEFINITION);
		}
	}
}
