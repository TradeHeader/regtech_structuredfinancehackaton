package cdm.base.staticdata.asset.common.validation.datarule;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("CommodityOrdinalExists")
@ImplementedBy(CommodityOrdinalExists.Default.class)
public interface CommodityOrdinalExists extends Validator<Commodity> {
	
	String NAME = "CommodityOrdinalExists";
	String DEFINITION = "if productTaxonomy -> value -> classification count > 1 then productTaxonomy -> value -> classification -> ordinal exists";
	
	ValidationResult<Commodity> validate(RosettaPath path, Commodity commodity);
	
	class Default implements CommodityOrdinalExists {
	
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
				if (greaterThan(MapperS.of(MapperS.of(commodity).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<TaxonomyValue>map("getValue", taxonomy -> taxonomy.getValue()).<TaxonomyClassification>mapC("getClassification", taxonomyValue -> taxonomyValue.getClassification()).resultCount()), MapperS.of(1), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(commodity).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<TaxonomyValue>map("getValue", taxonomy -> taxonomy.getValue()).<TaxonomyClassification>mapC("getClassification", taxonomyValue -> taxonomyValue.getClassification()).<Integer>map("getOrdinal", taxonomyClassification -> taxonomyClassification.getOrdinal()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CommodityOrdinalExists {
	
		@Override
		public ValidationResult<Commodity> validate(RosettaPath path, Commodity commodity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Commodity", path, DEFINITION);
		}
	}
}
