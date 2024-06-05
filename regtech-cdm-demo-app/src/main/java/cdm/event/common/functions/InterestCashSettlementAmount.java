package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.functions.ExtractCounterpartyByRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.Reset;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.observable.asset.Price;
import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.functions.FixedAmount;
import cdm.product.asset.functions.FloatingAmount;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(InterestCashSettlementAmount.InterestCashSettlementAmountDefault.class)
public abstract class InterestCashSettlementAmount implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ExtractCounterpartyByRole extractCounterpartyByRole;
	@Inject protected FixedAmount fixedAmount;
	@Inject protected FloatingAmount floatingAmount;

	/**
	* @param tradeState 
	* @param interestRatePayout 
	* @param resets 
	* @param date 
	* @return interestCashSettlementAmount 
	*/
	public Transfer evaluate(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
		Transfer.TransferBuilder interestCashSettlementAmountBuilder = doEvaluate(tradeState, interestRatePayout, resets, date);
		
		final Transfer interestCashSettlementAmount;
		if (interestCashSettlementAmountBuilder == null) {
			interestCashSettlementAmount = null;
		} else {
			interestCashSettlementAmount = interestCashSettlementAmountBuilder.build();
			objectValidator.validate(Transfer.class, interestCashSettlementAmount);
		}
		
		return interestCashSettlementAmount;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date);

	protected abstract MapperS<BigDecimal> performance(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date);

	protected abstract MapperS<? extends Party> payer(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date);

	protected abstract MapperS<? extends Party> receiver(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date);

	public static class InterestCashSettlementAmountDefault extends InterestCashSettlementAmount {
		@Override
		protected Transfer.TransferBuilder doEvaluate(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
			if (resets == null) {
				resets = Collections.emptyList();
			}
			Transfer.TransferBuilder interestCashSettlementAmount = Transfer.builder();
			return assignOutput(interestCashSettlementAmount, tradeState, interestRatePayout, resets, date);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder interestCashSettlementAmount, TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
			interestCashSettlementAmount
				.getOrCreateQuantity()
				.setValue(performance(tradeState, interestRatePayout, resets, date).get());
			
			interestCashSettlementAmount
				.getOrCreateQuantity()
				.getOrCreateUnit()
				.setCurrencyValue(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			final Party ifThenElseResult0;
			if (greaterThanEquals(performance(tradeState, interestRatePayout, resets, date), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult0 = payer(tradeState, interestRatePayout, resets, date).get();
			} else {
				ifThenElseResult0 = receiver(tradeState, interestRatePayout, resets, date).get();
			}
			interestCashSettlementAmount
				.getOrCreatePayerReceiver()
				.setPayerPartyReferenceValue(ifThenElseResult0);
			
			final Party ifThenElseResult1;
			if (greaterThanEquals(performance(tradeState, interestRatePayout, resets, date), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult1 = receiver(tradeState, interestRatePayout, resets, date).get();
			} else {
				ifThenElseResult1 = payer(tradeState, interestRatePayout, resets, date).get();
			}
			interestCashSettlementAmount
				.getOrCreatePayerReceiver()
				.setReceiverPartyReferenceValue(ifThenElseResult1);
			
			interestCashSettlementAmount
				.getOrCreateSettlementDate()
				.setAdjustedDateValue(date);
			
			interestCashSettlementAmount
				.getOrCreateSettlementOrigin()
				.setInterestRatePayout(ReferenceWithMetaInterestRatePayout.builder()
					.setGlobalReference(Optional.ofNullable(interestRatePayout)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(interestRatePayout)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			return Optional.ofNullable(interestCashSettlementAmount)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> performance(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
			if (exists(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FixedRateSpecification>map("getFixedRate", rateSpecification -> rateSpecification.getFixedRate())).getOrDefault(false)) {
				return MapperS.of(fixedAmount.evaluate(interestRatePayout, MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), date, null));
			}
			if (exists(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate())).getOrDefault(false)) {
				return MapperS.of(floatingAmount.evaluate(interestRatePayout, MapperS.of(MapperC.of(resets).get()).<Price>map("getResetValue", reset -> reset.getResetValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), date, null));
			}
			return MapperS.<BigDecimal>ofNull();
		}
		
		@Override
		protected MapperS<? extends Party> payer(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(interestRatePayout).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<? extends Party> receiver(TradeState tradeState, InterestRatePayout interestRatePayout, List<? extends Reset> resets, Date date) {
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(interestRatePayout).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", payerReceiver -> payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue());
		}
	}
}
