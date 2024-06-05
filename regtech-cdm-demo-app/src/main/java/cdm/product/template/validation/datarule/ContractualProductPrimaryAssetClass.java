package cdm.product.template.validation.datarule;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
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
@RosettaDataRule("ContractualProductPrimaryAssetClass")
@ImplementedBy(ContractualProductPrimaryAssetClass.Default.class)
public interface ContractualProductPrimaryAssetClass extends Validator<ContractualProduct> {
	
	String NAME = "ContractualProductPrimaryAssetClass";
	String DEFINITION = "if economicTerms -> nonStandardisedTerms = True then productTaxonomy -> primaryAssetClass exists";
	
	ValidationResult<ContractualProduct> validate(RosettaPath path, ContractualProduct contractualProduct);
	
	class Default implements ContractualProductPrimaryAssetClass {
	
		@Override
		public ValidationResult<ContractualProduct> validate(RosettaPath path, ContractualProduct contractualProduct) {
			ComparisonResult result = executeDataRule(contractualProduct);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractualProduct", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ContractualProduct", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ContractualProduct contractualProduct) {
			try {
				if (areEqual(MapperS.of(contractualProduct).<EconomicTerms>map("getEconomicTerms", _contractualProduct -> _contractualProduct.getEconomicTerms()).<Boolean>map("getNonStandardisedTerms", economicTerms -> economicTerms.getNonStandardisedTerms()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(contractualProduct).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ContractualProductPrimaryAssetClass {
	
		@Override
		public ValidationResult<ContractualProduct> validate(RosettaPath path, ContractualProduct contractualProduct) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractualProduct", path, DEFINITION);
		}
	}
}
