package cdm.observable.event.functions;

import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.event.common.Reset;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.event.Observation;
import cdm.product.collateral.Collateral;
import cdm.product.template.AssetPayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_AssetPayoutTradeStateWithObservations.Create_AssetPayoutTradeStateWithObservationsDefault.class)
public abstract class Create_AssetPayoutTradeStateWithObservations implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_AssetReset create_AssetReset;

	/**
	* @param billingInstruction 
	* @return tradeState 
	*/
	public TradeState evaluate(BillingRecordInstruction billingInstruction) {
		TradeState.TradeStateBuilder tradeStateBuilder = doEvaluate(billingInstruction);
		
		final TradeState tradeState;
		if (tradeStateBuilder == null) {
			tradeState = null;
		} else {
			tradeState = tradeStateBuilder.build();
			objectValidator.validate(TradeState.class, tradeState);
		}
		
		return tradeState;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(BillingRecordInstruction billingInstruction);

	protected abstract MapperS<? extends AssetPayout> assetPayout(BillingRecordInstruction billingInstruction);

	protected abstract MapperS<Date> date(BillingRecordInstruction billingInstruction);

	public static class Create_AssetPayoutTradeStateWithObservationsDefault extends Create_AssetPayoutTradeStateWithObservations {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(BillingRecordInstruction billingInstruction) {
			TradeState.TradeStateBuilder tradeState = TradeState.builder();
			return assignOutput(tradeState, billingInstruction);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder tradeState, BillingRecordInstruction billingInstruction) {
			tradeState = toBuilder(MapperS.of(billingInstruction).<ReferenceWithMetaTradeState>map("getTradeState", billingRecordInstruction -> billingRecordInstruction.getTradeState()).<TradeState>map("getValue", _f->_f.getValue()).get());
			
			final Reset reset = create_AssetReset.evaluate(assetPayout(billingInstruction).get(), MapperS.of(billingInstruction).<Observation>mapC("getObservation", billingRecordInstruction -> billingRecordInstruction.getObservation()).getMulti(), date(billingInstruction).get());
			tradeState
				.addResetHistory((reset == null ? Collections.<Reset>emptyList() : Collections.singletonList(reset)));
			
			return Optional.ofNullable(tradeState)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends AssetPayout> assetPayout(BillingRecordInstruction billingInstruction) {
			return MapperS.of(MapperS.of(billingInstruction).<ReferenceWithMetaTradeState>map("getTradeState", billingRecordInstruction -> billingRecordInstruction.getTradeState()).<TradeState>map("getValue", _f->_f.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", collateral -> collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()).get());
		}
		
		@Override
		protected MapperS<Date> date(BillingRecordInstruction billingInstruction) {
			return MapperS.of(billingInstruction).<Date>map("getRecordEndDate", billingRecordInstruction -> billingRecordInstruction.getRecordEndDate());
		}
	}
}
