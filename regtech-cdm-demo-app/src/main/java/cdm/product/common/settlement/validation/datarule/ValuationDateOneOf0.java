package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.ValuationDate;
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
@RosettaDataRule("ValuationDateOneOf0")
@ImplementedBy(ValuationDateOneOf0.Default.class)
public interface ValuationDateOneOf0 extends Validator<ValuationDate> {
	
	String NAME = "ValuationDateOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<ValuationDate> validate(RosettaPath path, ValuationDate valuationDate);
	
	class Default implements ValuationDateOneOf0 {
	
		@Override
		public ValidationResult<ValuationDate> validate(RosettaPath path, ValuationDate valuationDate) {
			ComparisonResult result = executeDataRule(valuationDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ValuationDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ValuationDate valuationDate) {
			try {
				return choice(MapperS.of(valuationDate), Arrays.asList("singleValuationDate", "multipleValuationDates", "valuationDate", "fxFixingDate", "fxFixingSchedule"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ValuationDateOneOf0 {
	
		@Override
		public ValidationResult<ValuationDate> validate(RosettaPath path, ValuationDate valuationDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationDate", path, DEFINITION);
		}
	}
}
