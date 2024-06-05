package cdm.product.template.validation.datarule;

import cdm.product.template.Basket;
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
@RosettaDataRule("BasketOneOf0")
@ImplementedBy(BasketOneOf0.Default.class)
public interface BasketOneOf0 extends Validator<Basket> {
	
	String NAME = "BasketOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<Basket> validate(RosettaPath path, Basket basket);
	
	class Default implements BasketOneOf0 {
	
		@Override
		public ValidationResult<Basket> validate(RosettaPath path, Basket basket) {
			ComparisonResult result = executeDataRule(basket);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Basket", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Basket", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Basket basket) {
			try {
				return choice(MapperS.of(basket), Arrays.asList("basketConstituent", "portfolioBasketConstituent", "productTaxonomy", "productIdentifier"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements BasketOneOf0 {
	
		@Override
		public ValidationResult<Basket> validate(RosettaPath path, Basket basket) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Basket", path, DEFINITION);
		}
	}
}
