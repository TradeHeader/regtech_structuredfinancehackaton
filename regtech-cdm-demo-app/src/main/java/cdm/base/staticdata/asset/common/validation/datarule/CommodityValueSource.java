package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.Commodity;
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
@RosettaDataRule("CommodityValueSource")
@ImplementedBy(CommodityValueSource.Default.class)
public interface CommodityValueSource extends Validator<Commodity> {
	
	String NAME = "CommodityValueSource";
	String DEFINITION = "if productTaxonomy exists then (productTaxonomy -> source exists and productTaxonomy -> value exists)";
	
	ValidationResult<Commodity> validate(RosettaPath path, Commodity commodity);
	
	class Default implements CommodityValueSource {
	
		@Override
		public ValidationResult<Commodity> validate(RosettaPath path, Commodity commodity) {
			ComparisonResult result = executeDataRule(commodity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Commodity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Commodity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Commodity commodity) {
			try {
				if (exists(MapperS.of(commodity).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy())).getOrDefault(false)) {
					return exists(MapperS.of(commodity).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<TaxonomySourceEnum>map("getSource", taxonomy -> taxonomy.getSource())).and(exists(MapperS.of(commodity).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<TaxonomyValue>map("getValue", taxonomy -> taxonomy.getValue())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityValueSource {
	
		@Override
		public ValidationResult<Commodity> validate(RosettaPath path, Commodity commodity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Commodity", path, DEFINITION);
		}
	}
}
