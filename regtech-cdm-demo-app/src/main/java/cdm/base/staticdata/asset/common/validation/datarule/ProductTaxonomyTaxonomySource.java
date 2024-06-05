package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.TaxonomySourceEnum;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ProductTaxonomyTaxonomySource")
@ImplementedBy(ProductTaxonomyTaxonomySource.Default.class)
public interface ProductTaxonomyTaxonomySource extends Validator<ProductTaxonomy> {
	
	String NAME = "ProductTaxonomyTaxonomySource";
	String DEFINITION = "if source exists then (value exists or productQualifier exists)";
	
	ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy productTaxonomy);
	
	class Default implements ProductTaxonomyTaxonomySource {
	
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
				if (exists(MapperS.of(productTaxonomy).<TaxonomySourceEnum>map("getSource", taxonomy -> taxonomy.getSource())).getOrDefault(false)) {
					return exists(MapperS.of(productTaxonomy).<TaxonomyValue>map("getValue", taxonomy -> taxonomy.getValue())).or(exists(MapperS.of(productTaxonomy).<String>map("getProductQualifier", _productTaxonomy -> _productTaxonomy.getProductQualifier())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ProductTaxonomyTaxonomySource {
	
		@Override
		public ValidationResult<ProductTaxonomy> validate(RosettaPath path, ProductTaxonomy productTaxonomy) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ProductTaxonomy", path, DEFINITION);
		}
	}
}
