package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.EconomicTerms;
import cdm.product.template.FixedPricePayout;
import cdm.product.template.ForwardPayout;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_AssetClass_Equity.Qualify_AssetClass_EquityDefault.class)
public abstract class Qualify_AssetClass_Equity implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_UnderlierProduct_Equity qualify_UnderlierProduct_Equity;

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

	public static class Qualify_AssetClass_EquityDefault extends Qualify_AssetClass_Equity {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final ComparisonResult ifThenElseResult0;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult0 = areEqual(MapperS.of(qualify_UnderlierProduct_Equity.evaluate(optionUnderlier(economicTerms).get())), MapperS.of(true), CardinalityOperator.All).or(areEqual(optionUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.EQUITY), CardinalityOperator.Any)).or(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.EQUITY), CardinalityOperator.Any));
			} else {
				ifThenElseResult0 = ComparisonResult.of(MapperS.of(false));
			}
			final ComparisonResult ifThenElseResult1;
			if (exists(forwardUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult1 = ComparisonResult.of(MapperS.of(qualify_UnderlierProduct_Equity.evaluate(forwardUnderlier(economicTerms).get()))).or(areEqual(forwardUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.EQUITY), CardinalityOperator.Any)).or(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.EQUITY), CardinalityOperator.Any));
			} else {
				ifThenElseResult1 = ComparisonResult.of(MapperS.of(false));
			}
			is_product = areEqual(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).<Product>map("getUnderlier", performancePayout -> performancePayout.getUnderlier())
				.mapItem(item -> MapperS.of(qualify_UnderlierProduct_Equity.evaluate(item.get()))), MapperS.of(true), CardinalityOperator.All).and(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()), MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<FixedPricePayout>mapC("getFixedPricePayout", payout -> payout.getFixedPricePayout())))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()))))).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()))).and(ifThenElseResult0)).or(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()))).and(ifThenElseResult1)).get();
			
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
