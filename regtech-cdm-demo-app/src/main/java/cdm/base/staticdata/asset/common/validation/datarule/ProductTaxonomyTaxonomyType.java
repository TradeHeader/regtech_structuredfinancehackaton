package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.ProductTaxonomy;
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
@RosettaDataRule("ProductTaxonomyTaxonomyType")
@ImplementedBy(ProductTaxonomyTaxonomyType.Default.class)
public interface ProductTaxonomyTaxonomyType extends Validator<ProductTaxonomy> {
	
	String NAME = "ProductTaxonomyTaxonomyType";
	String DEFINITION = "required choice source, primaryAssetClass, secondaryAssetClass";
	
	ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy productTaxonomy);
	
	class Default implements ProductTaxonomyTaxonomyType {
	
		@Override
		public ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy productTaxonomy) {
			ComparisonResult result = executeDataRule(productTaxonomy);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ProductTaxonomy", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ProductTaxonomy", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ProductTaxonomy productTaxonomy) {
			try {
				return choice(MapperS.of(productTaxonomy), Arrays.asList("source", "primaryAssetClass", "secondaryAssetClass"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ProductTaxonomyTaxonomyType {
	
		@Override
		public ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy productTaxonomy) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ProductTaxonomy", path, DEFINITION);
		}
	}
}
