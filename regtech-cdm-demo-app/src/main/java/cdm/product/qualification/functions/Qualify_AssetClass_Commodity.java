package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.product.asset.CommodityPayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.FixedPricePayout;
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

@ImplementedBy(Qualify_AssetClass_Commodity.Qualify_AssetClass_CommodityDefault.class)
public abstract class Qualify_AssetClass_Commodity implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.product.qualification.functions.Qualify_AssetClass_Commodity qualify_AssetClass_Commodity;

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

	public static class Qualify_AssetClass_CommodityDefault extends Qualify_AssetClass_Commodity {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final ComparisonResult ifThenElseResult;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult = areEqual(MapperS.of(qualify_AssetClass_Commodity.evaluate(optionUnderlier(economicTerms).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).get())), MapperS.of(true), CardinalityOperator.All).or(areEqual(MapperS.of(qualify_AssetClass_Commodity.evaluate(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<EconomicTerms>map("getEconomicTerms", security -> security.getEconomicTerms()).get())), MapperS.of(true), CardinalityOperator.All)).or(exists(optionUnderlier(economicTerms).<ReferenceWithMetaCommodity>map("getCommodity", product -> product.getCommodity()).<Commodity>map("getValue", _f->_f.getValue()))).or(areEqual(optionUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.COMMODITY), CardinalityOperator.Any)).or(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.COMMODITY), CardinalityOperator.Any));
			} else {
				ifThenElseResult = ComparisonResult.of(MapperS.of(false));
			}
			is_product = onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<FixedPricePayout>mapC("getFixedPricePayout", payout -> payout.getFixedPricePayout()))).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout()).<Product>map("getUnderlier", commodityPayout -> commodityPayout.getUnderlier()).<ReferenceWithMetaCommodity>map("getCommodity", product -> product.getCommodity()).<Commodity>map("getValue", _f->_f.getValue()))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout()))).and(areEqual(MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout()).resultCount()), MapperS.of(2), CardinalityOperator.All)).and(areEqual(MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout()).<Product>map("getUnderlier", commodityPayout -> commodityPayout.getUnderlier()).<ReferenceWithMetaCommodity>map("getCommodity", product -> product.getCommodity()).<Commodity>map("getValue", _f->_f.getValue()).resultCount()), MapperS.of(2), CardinalityOperator.All))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()))).and(ifThenElseResult)).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<FixedPricePayout>mapC("getFixedPricePayout", payout -> payout.getFixedPricePayout()))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CommodityPayout>mapC("getCommodityPayout", payout -> payout.getCommodityPayout())))).and(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).<Product>map("getUnderlier", forwardPayout -> forwardPayout.getUnderlier()).<ReferenceWithMetaCommodity>map("getCommodity", product -> product.getCommodity()).<Commodity>map("getValue", _f->_f.getValue())).or(areEqual(forwardUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.COMMODITY), CardinalityOperator.Any)).or(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.COMMODITY), CardinalityOperator.Any)))).get();
			
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
