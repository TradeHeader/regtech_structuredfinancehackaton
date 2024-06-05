package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.MortgageSectorEnum;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.ReferenceObligation;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Credit_SingleName_ABS_CDSonCDO.Qualify_Credit_SingleName_ABS_CDSonCDODefault.class)
public abstract class Qualify_Credit_SingleName_ABS_CDSonCDO implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_CreditDefaultSwap_SingleName qualify_CreditDefaultSwap_SingleName;

	/**
	* @param tradeState 
	* @return is_product 
	*/
	public Boolean evaluate(TradeState tradeState) {
		Boolean is_product = doEvaluate(tradeState);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(TradeState tradeState);

	protected abstract MapperS<? extends EconomicTerms> economicTerms(TradeState tradeState);

	protected abstract MapperC<? extends Security> security(TradeState tradeState);

	public static class Qualify_Credit_SingleName_ABS_CDSonCDODefault extends Qualify_Credit_SingleName_ABS_CDSonCDO {
		@Override
		protected Boolean doEvaluate(TradeState tradeState) {
			Boolean is_product = null;
			return assignOutput(is_product, tradeState);
		}
		
		protected Boolean assignOutput(Boolean is_product, TradeState tradeState) {
			is_product = ComparisonResult.of(MapperS.of(qualify_CreditDefaultSwap_SingleName.evaluate(economicTerms(tradeState).get()))).and(areEqual(security(tradeState).<SecurityTypeEnum>map("getSecurityType", _security -> _security.getSecurityType()), MapperS.of(SecurityTypeEnum.MORTGAGE), CardinalityOperator.Any)).and(areEqual(security(tradeState).<MortgageSectorEnum>map("getMortgageSector", _security -> _security.getMortgageSector()), MapperS.of(MortgageSectorEnum.CDO), CardinalityOperator.Any)).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> economicTerms(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms());
		}
		
		@Override
		protected MapperC<? extends Security> security(TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<ReferenceInformation>map("getReferenceInformation", generalTerms -> generalTerms.getReferenceInformation()).<ReferenceObligation>mapC("getReferenceObligation", referenceInformation -> referenceInformation.getReferenceObligation()).<Security>map("getSecurity", referenceObligation -> referenceObligation.getSecurity());
		}
	}
}
