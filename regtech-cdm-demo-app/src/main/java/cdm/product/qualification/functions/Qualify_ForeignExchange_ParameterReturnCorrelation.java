package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.ForeignExchange;
import cdm.product.template.Basket;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_ForeignExchange_ParameterReturnCorrelation.Qualify_ForeignExchange_ParameterReturnCorrelationDefault.class)
public abstract class Qualify_ForeignExchange_ParameterReturnCorrelation implements RosettaFunction,IQualifyFunctionExtension<EconomicTerms> {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_AssetClass_ForeignExchange qualify_AssetClass_ForeignExchange;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	@Override
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	protected abstract MapperS<? extends PerformancePayout> performancePayout(EconomicTerms economicTerms);

	public static class Qualify_ForeignExchange_ParameterReturnCorrelationDefault extends Qualify_ForeignExchange_ParameterReturnCorrelation {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_AssetClass_ForeignExchange.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(onlyExists(Arrays.asList(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout())))).and(areEqual(MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(onlyExists(Arrays.asList(performancePayout(economicTerms).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<CorrelationReturnTerms>map("getCorrelationReturnTerms", returnTerms -> returnTerms.getCorrelationReturnTerms())))).and(onlyExists(Arrays.asList(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket())))).and(onlyExists(Arrays.asList(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<ForeignExchange>map("getForeignExchange", product -> product.getForeignExchange())))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Basket>map("getBasket", product -> product.getBasket()))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<ReferenceWithMetaCommodity>map("getCommodity", product -> product.getCommodity()).<Commodity>map("getValue", _f->_f.getValue()))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Index>map("getIndex", product -> product.getIndex()))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Loan>map("getLoan", product -> product.getLoan()))).and(notExists(performancePayout(economicTerms).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket()).<Product>mapC("getBasketConstituent", basket -> basket.getBasketConstituent()).<Security>map("getSecurity", product -> product.getSecurity()))).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends PerformancePayout> performancePayout(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
