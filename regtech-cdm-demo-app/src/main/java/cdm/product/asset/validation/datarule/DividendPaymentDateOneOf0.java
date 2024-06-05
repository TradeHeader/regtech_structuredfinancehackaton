package cdm.product.asset.validation.datarule;

import cdm.product.asset.DividendPaymentDate;
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
@RosettaDataRule("DividendPaymentDateOneOf0")
@ImplementedBy(DividendPaymentDateOneOf0.Default.class)
public interface DividendPaymentDateOneOf0 extends Validator<DividendPaymentDate> {
	
	String NAME = "DividendPaymentDateOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<DividendPaymentDate> validate(RosettaPath path, DividendPaymentDate dividendPaymentDate);
	
	class Default implements DividendPaymentDateOneOf0 {
	
		@Override
		public ValidationResult<DividendPaymentDate> validate(RosettaPath path, DividendPaymentDate dividendPaymentDate) {
			ComparisonResult result = executeDataRule(dividendPaymentDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendPaymentDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DividendPaymentDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DividendPaymentDate dividendPaymentDate) {
			try {
				return choice(MapperS.of(dividendPaymentDate), Arrays.asList("dividendDateReference", "dividendDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendPaymentDateOneOf0 {
	
		@Override
		public ValidationResult<DividendPaymentDate> validate(RosettaPath path, DividendPaymentDate dividendPaymentDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendPaymentDate", path, DEFINITION);
		}
	}
}
