package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_AssetClass_InterestRate.Qualify_AssetClass_InterestRateDefault.class)
public abstract class Qualify_AssetClass_InterestRate implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.product.qualification.functions.Qualify_AssetClass_InterestRate qualify_AssetClass_InterestRate;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	protected abstract MapperS<? extends Product> optionUnderlier(EconomicTerms economicTerms);

	protected abstract MapperS<? extends Product> forwardUnderlier(EconomicTerms economicTerms);

	public static class Qualify_AssetClass_InterestRateDefault extends Qualify_AssetClass_InterestRate {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final ComparisonResult ifThenElseResult;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult = areEqual(MapperS.of(qualify_AssetClass_InterestRate.evaluate(optionUnderlier(economicTerms).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).get())), MapperS.of(true), CardinalityOperator.All).or(areEqual(MapperS.of(qualify_AssetClass_InterestRate.evaluate(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<EconomicTerms>map("getEconomicTerms", security -> security.getEconomicTerms()).get())), MapperS.of(true), CardinalityOperator.All));
			} else {
				ifThenElseResult = ComparisonResult.of(MapperS.of(false));
			}
			is_product = onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()))).and(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.DEBT), CardinalityOperator.All).or(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.INTEREST_RATE), CardinalityOperator.Any)).or(areEqual(optionUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.INTEREST_RATE), CardinalityOperator.Any)).or(ifThenElseResult))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()))).and(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<SecurityTypeEnum>map("getSecurityType", security -> security.getSecurityType()), MapperS.of(SecurityTypeEnum.DEBT), CardinalityOperator.All).or(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.INTEREST_RATE), CardinalityOperator.Any)).or(areEqual(forwardUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.INTEREST_RATE), CardinalityOperator.Any)))).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends Product> optionUnderlier(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).get()).<Product>map("getUnderlier", optionPayout -> optionPayout.getUnderlier());
		}
		
		@Override
		protected MapperS<? extends Product> forwardUnderlier(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).get()).<Product>map("getUnderlier", forwardPayout -> forwardPayout.getUnderlier());
		}
	}
}
