package cdm.event.common.functions;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_TermsChange.Create_TermsChangeDefault.class)
public abstract class Create_TermsChange implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param termsChange Instructions to be used as an input to the function
	* @param before current trade to be ammended
	* @return tradeState 
	*/
	public TradeState evaluate(TermsChangeInstruction termsChange, TradeState before) {
		TradeState.TradeStateBuilder tradeStateBuilder = doEvaluate(termsChange, before);
		
		final TradeState tradeState;
		if (tradeStateBuilder == null) {
			tradeState = null;
		} else {
			tradeState = tradeStateBuilder.build();
			objectValidator.validate(TradeState.class, tradeState);
		}
		
		return tradeState;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(TermsChangeInstruction termsChange, TradeState before);

	protected abstract MapperS<? extends Product> newProduct(TermsChangeInstruction termsChange, TradeState before);

	protected abstract MapperC<? extends AncillaryParty> newAncillaryParty(TermsChangeInstruction termsChange, TradeState before);

	protected abstract MapperS<NotionalAdjustmentEnum> newAdjustment(TermsChangeInstruction termsChange, TradeState before);

	public static class Create_TermsChangeDefault extends Create_TermsChange {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(TermsChangeInstruction termsChange, TradeState before) {
			TradeState.TradeStateBuilder tradeState = TradeState.builder();
			return assignOutput(tradeState, termsChange, before);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder tradeState, TermsChangeInstruction termsChange, TradeState before) {
			tradeState = toBuilder(before);
			
			tradeState
				.getOrCreateTrade()
				.setTradableProduct(TradableProduct.builder()
					.setProduct(newProduct(termsChange, before).get())
					.setTradeLot(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).getMulti())
					.setCounterparty(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti())
					.setAncillaryParty(new ArrayList<>(newAncillaryParty(termsChange, before).getMulti()))
					.setAdjustment(newAdjustment(termsChange, before).get())
					.build()
				);
			
			return Optional.ofNullable(tradeState)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends Product> newProduct(TermsChangeInstruction termsChange, TradeState before) {
			if (exists(MapperS.of(termsChange).<Product>map("getProduct", termsChangeInstruction -> termsChangeInstruction.getProduct())).getOrDefault(false)) {
				return MapperS.of(termsChange).<Product>map("getProduct", termsChangeInstruction -> termsChangeInstruction.getProduct());
			}
			return MapperS.of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct());
		}
		
		@Override
		protected MapperC<? extends AncillaryParty> newAncillaryParty(TermsChangeInstruction termsChange, TradeState before) {
			if (exists(MapperS.of(termsChange).<AncillaryParty>mapC("getAncillaryParty", termsChangeInstruction -> termsChangeInstruction.getAncillaryParty())).getOrDefault(false)) {
				return MapperS.of(termsChange).<AncillaryParty>mapC("getAncillaryParty", termsChangeInstruction -> termsChangeInstruction.getAncillaryParty());
			}
			return MapperS.of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<AncillaryParty>mapC("getAncillaryParty", tradableProduct -> tradableProduct.getAncillaryParty());
		}
		
		@Override
		protected MapperS<NotionalAdjustmentEnum> newAdjustment(TermsChangeInstruction termsChange, TradeState before) {
			if (exists(MapperS.of(termsChange).<NotionalAdjustmentEnum>map("getAdjustment", termsChangeInstruction -> termsChangeInstruction.getAdjustment())).getOrDefault(false)) {
				return MapperS.of(termsChange).<NotionalAdjustmentEnum>map("getAdjustment", termsChangeInstruction -> termsChangeInstruction.getAdjustment());
			}
			return MapperS.of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<NotionalAdjustmentEnum>map("getAdjustment", tradableProduct -> tradableProduct.getAdjustment());
		}
	}
}
