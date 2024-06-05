package cdm.event.common.functions;

import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.functions.FilterQuantityByFinancialUnit;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.functions.ExtractCounterpartyByRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.event.common.Reset;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.observable.asset.Price;
import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.functions.FixedAmount;
import cdm.product.asset.functions.FloatingAmount;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.functions.CalculationPeriodRange;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.AssetPayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolveSecurityFinanceBillingAmount.ResolveSecurityFinanceBillingAmountDefault.class)
public abstract class ResolveSecurityFinanceBillingAmount implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected CalculationPeriodRange calculationPeriodRange0;
	@Inject protected ExtractCounterpartyByRole extractCounterpartyByRole;
	@Inject protected FilterQuantityByFinancialUnit filterQuantityByFinancialUnit;
	@Inject protected FixedAmount fixedAmount;
	@Inject protected FloatingAmount floatingAmount;

	/**
	* @param tradeState 
	* @param reset 
	* @param recordStartDate 
	* @param recordEndDate 
	* @param transferDate 
	* @return transfer 
	*/
	public Transfer evaluate(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
		Transfer.TransferBuilder transferBuilder = doEvaluate(tradeState, reset, recordStartDate, recordEndDate, transferDate);
		
		final Transfer transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.build();
			objectValidator.validate(Transfer.class, transfer);
		}
		
		return transfer;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperC<? extends QuantitySchedule> securityQuantity(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends InterestRatePayout> interestRatePayout(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends AssetPayout> assetPayout(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends Collateral> collateral(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<BigDecimal> haircutPercentage(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<BigDecimal> valuationPercentage(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<BigDecimal> marginRatio(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<BigDecimal> billingQuantity(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends CalculationPeriodData> calculationPeriodRange1(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<BigDecimal> performance(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends Party> payerPartyReference(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	protected abstract MapperS<? extends Party> receiverPartyReference(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate);

	public static class ResolveSecurityFinanceBillingAmountDefault extends ResolveSecurityFinanceBillingAmount {
		@Override
		protected Transfer.TransferBuilder doEvaluate(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			Transfer.TransferBuilder transfer = Transfer.builder();
			return assignOutput(transfer, tradeState, reset, recordStartDate, recordEndDate, transferDate);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder transfer, TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			transfer
				.getOrCreateQuantity()
				.setValue(performance(tradeState, reset, recordStartDate, recordEndDate, transferDate).get());
			
			transfer
				.getOrCreateQuantity()
				.getOrCreateUnit()
				.setCurrencyValue(interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			final Party ifThenElseResult0;
			if (greaterThanEquals(performance(tradeState, reset, recordStartDate, recordEndDate, transferDate), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult0 = payerPartyReference(tradeState, reset, recordStartDate, recordEndDate, transferDate).get();
			} else {
				ifThenElseResult0 = receiverPartyReference(tradeState, reset, recordStartDate, recordEndDate, transferDate).get();
			}
			transfer
				.getOrCreatePayerReceiver()
				.setPayerPartyReferenceValue(ifThenElseResult0);
			
			final Party ifThenElseResult1;
			if (greaterThanEquals(performance(tradeState, reset, recordStartDate, recordEndDate, transferDate), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult1 = receiverPartyReference(tradeState, reset, recordStartDate, recordEndDate, transferDate).get();
			} else {
				ifThenElseResult1 = payerPartyReference(tradeState, reset, recordStartDate, recordEndDate, transferDate).get();
			}
			transfer
				.getOrCreatePayerReceiver()
				.setReceiverPartyReferenceValue(ifThenElseResult1);
			
			transfer
				.getOrCreateSettlementDate()
				.setAdjustedDateValue(transferDate);
			
			return Optional.ofNullable(transfer)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends QuantitySchedule> securityQuantity(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperC.<QuantitySchedule>of(filterQuantityByFinancialUnit.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti(), FinancialUnitEnum.SHARE));
		}
		
		@Override
		protected MapperS<? extends InterestRatePayout> interestRatePayout(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()).get());
		}
		
		@Override
		protected MapperS<? extends AssetPayout> assetPayout(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", _collateral -> _collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()).get());
		}
		
		@Override
		protected MapperS<? extends Collateral> collateral(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral());
		}
		
		@Override
		protected MapperS<BigDecimal> haircutPercentage(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(MapperS.of(new BigDecimal("1.0")), MapperS.of(collateral(tradeState, reset, recordStartDate, recordEndDate, transferDate).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getHaircutPercentage", collateralValuationTreatment -> collateralValuationTreatment.getHaircutPercentage()));
		}
		
		@Override
		protected MapperS<BigDecimal> valuationPercentage(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperS.of(BigDecimal.valueOf(1)), haircutPercentage(tradeState, reset, recordStartDate, recordEndDate, transferDate));
		}
		
		@Override
		protected MapperS<BigDecimal> marginRatio(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			if (exists(MapperS.of(collateral(tradeState, reset, recordStartDate, recordEndDate, transferDate).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getHaircutPercentage", collateralValuationTreatment -> collateralValuationTreatment.getHaircutPercentage())).getOrDefault(false)) {
				return valuationPercentage(tradeState, reset, recordStartDate, recordEndDate, transferDate);
			}
			if (exists(MapperS.of(collateral(tradeState, reset, recordStartDate, recordEndDate, transferDate).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getMarginPercentage", collateralValuationTreatment -> collateralValuationTreatment.getMarginPercentage())).getOrDefault(false)) {
				return MapperS.of(collateral(tradeState, reset, recordStartDate, recordEndDate, transferDate).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getMarginPercentage", collateralValuationTreatment -> collateralValuationTreatment.getMarginPercentage());
			}
			return MapperS.of(new BigDecimal("1.0"));
		}
		
		@Override
		protected MapperS<BigDecimal> billingQuantity(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(reset).<Price>map("getResetValue", _reset -> _reset.getResetValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), securityQuantity(tradeState, reset, recordStartDate, recordEndDate, transferDate).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())), marginRatio(tradeState, reset, recordStartDate, recordEndDate, transferDate));
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodData> calculationPeriodRange1(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(calculationPeriodRange0.evaluate(recordStartDate, recordEndDate, null));
		}
		
		@Override
		protected MapperS<BigDecimal> performance(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			if (exists(interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FixedRateSpecification>map("getFixedRate", rateSpecification -> rateSpecification.getFixedRate())).getOrDefault(false)) {
				return MapperS.of(fixedAmount.evaluate(interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).get(), billingQuantity(tradeState, reset, recordStartDate, recordEndDate, transferDate).get(), recordEndDate, calculationPeriodRange1(tradeState, reset, recordStartDate, recordEndDate, transferDate).get()));
			}
			if (exists(interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate())).getOrDefault(false)) {
				return MapperS.of(floatingAmount.evaluate(interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).get(), MapperS.of(reset).<Price>map("getResetValue", _reset -> _reset.getResetValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), billingQuantity(tradeState, reset, recordStartDate, recordEndDate, transferDate).get(), recordEndDate, calculationPeriodRange1(tradeState, reset, recordStartDate, recordEndDate, transferDate).get()));
			}
			return MapperS.<BigDecimal>ofNull();
		}
		
		@Override
		protected MapperS<? extends Party> payerPartyReference(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<? extends Party> receiverPartyReference(TradeState tradeState, Reset reset, Date recordStartDate, Date recordEndDate, Date transferDate) {
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), interestRatePayout(tradeState, reset, recordStartDate, recordEndDate, transferDate).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", payerReceiver -> payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue());
		}
	}
}
