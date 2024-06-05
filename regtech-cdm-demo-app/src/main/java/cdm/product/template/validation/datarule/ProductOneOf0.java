package cdm.product.template.validation.datarule;

import cdm.product.template.Product;
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
@RosettaDataRule("ProductOneOf0")
@ImplementedBy(ProductOneOf0.Default.class)
public interface ProductOneOf0 extends Validator<Product> {
	
	String NAME = "ProductOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<Product> validate(RosettaPath path, Product product);
	
	class Default implements ProductOneOf0 {
	
		@Override
		public ValidationResult<Product> validate(RosettaPath path, Product product) {
			ComparisonResult result = executeDataRule(product);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Product", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Product", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Product product) {
			try {
				return choice(MapperS.of(product), Arrays.asList("contractualProduct", "index", "loan", "assetPool", "foreignExchange", "commodity", "security", "basket"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ProductOneOf0 {
	
		@Override
		public ValidationResult<Product> validate(RosettaPath path, Product product) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Product", path, DEFINITION);
		}
	}
}
