package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.AssetClassEnum;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.ProductTaxonomy;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaAssetClassEnum;
import cdm.observable.asset.Observable;
import cdm.observable.asset.QuotedCurrencyPair;
import cdm.observable.asset.metafields.FieldWithMetaQuotedCurrencyPair;
import cdm.product.asset.ForeignExchange;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
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
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_AssetClass_ForeignExchange.Qualify_AssetClass_ForeignExchangeDefault.class)
public abstract class Qualify_AssetClass_ForeignExchange implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.product.qualification.functions.Qualify_AssetClass_ForeignExchange qualify_AssetClass_ForeignExchange;

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

	public static class Qualify_AssetClass_ForeignExchangeDefault extends Qualify_AssetClass_ForeignExchange {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final ComparisonResult ifThenElseResult0;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult0 = ComparisonResult.of(MapperS.of(qualify_AssetClass_ForeignExchange.evaluate(optionUnderlier(economicTerms).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).get())));
			} else {
				ifThenElseResult0 = ComparisonResult.of(MapperS.of(false));
			}
			final ComparisonResult ifThenElseResult1;
			if (exists(optionUnderlier(economicTerms)).getOrDefault(false)) {
				ifThenElseResult1 = ComparisonResult.of(MapperS.of(qualify_AssetClass_ForeignExchange.evaluate(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<EconomicTerms>map("getEconomicTerms", security -> security.getEconomicTerms()).get())));
			} else {
				ifThenElseResult1 = ComparisonResult.of(MapperS.of(false));
			}
			is_product = exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).<Product>map("getUnderlier", forwardPayout -> forwardPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())).or(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).<Product>map("getUnderlier", optionPayout -> optionPayout.getUnderlier()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange()))).or(exists(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).<ObservationTerms>map("getObservationTerms", performancePayout -> performancePayout.getObservationTerms()).<Observable>map("getObservable", observationTerms -> observationTerms.getObservable()).<FieldWithMetaQuotedCurrencyPair>map("getCurrencyPair", observable -> observable.getCurrencyPair()).<QuotedCurrencyPair>map("getValue", _f->_f.getValue()))).or(areEqual(optionUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.FOREIGN_EXCHANGE), CardinalityOperator.Any)).or(areEqual(optionUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.FOREIGN_EXCHANGE), CardinalityOperator.Any)).or(ifThenElseResult0).or(ifThenElseResult1).or(areEqual(forwardUnderlier(economicTerms).<Index>map("getIndex", product -> product.getIndex()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.FOREIGN_EXCHANGE), CardinalityOperator.Any)).or(areEqual(forwardUnderlier(economicTerms).<Security>map("getSecurity", product -> product.getSecurity()).<ProductTaxonomy>mapC("getProductTaxonomy", productBase -> productBase.getProductTaxonomy()).<FieldWithMetaAssetClassEnum>map("getPrimaryAssetClass", productTaxonomy -> productTaxonomy.getPrimaryAssetClass()).<AssetClassEnum>map("getValue", _f->_f.getValue()), MapperS.of(AssetClassEnum.FOREIGN_EXCHANGE), CardinalityOperator.Any)).get();
			
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
