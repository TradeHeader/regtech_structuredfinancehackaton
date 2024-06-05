package cdm.product.asset.validation.datarule;

import cdm.product.asset.DividendCurrency;
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
@RosettaDataRule("DividendCurrencyOneOf0")
@ImplementedBy(DividendCurrencyOneOf0.Default.class)
public interface DividendCurrencyOneOf0 extends Validator<DividendCurrency> {
	
	String NAME = "DividendCurrencyOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<DividendCurrency> validate(RosettaPath path, DividendCurrency dividendCurrency);
	
	class Default implements DividendCurrencyOneOf0 {
	
		@Override
		public ValidationResult<DividendCurrency> validate(RosettaPath path, DividendCurrency dividendCurrency) {
			ComparisonResult result = executeDataRule(dividendCurrency);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendCurrency", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DividendCurrency", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DividendCurrency dividendCurrency) {
			try {
				return choice(MapperS.of(dividendCurrency), Arrays.asList("currency", "determinationMethod", "currencyReference"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DividendCurrencyOneOf0 {
	
		@Override
		public ValidationResult<DividendCurrency> validate(RosettaPath path, DividendCurrency dividendCurrency) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DividendCurrency", path, DEFINITION);
		}
	}
}
