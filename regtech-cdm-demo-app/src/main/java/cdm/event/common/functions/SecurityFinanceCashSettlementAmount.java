package cdm.event.common.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.Quantity;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.functions.FilterQuantityByFinancialUnit;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.functions.ExtractCounterpartyByRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.functions.FilterPrice;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.AssetPayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import cdm.product.template.metafields.ReferenceWithMetaAssetPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(SecurityFinanceCashSettlementAmount.SecurityFinanceCashSettlementAmountDefault.class)
public abstract class SecurityFinanceCashSettlementAmount implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ExtractCounterpartyByRole extractCounterpartyByRole;
	@Inject protected FilterPrice filterPrice;
	@Inject protected FilterQuantityByFinancialUnit filterQuantityByFinancialUnit;

	/**
	* @param tradeState 
	* @param date 
	* @param quantity Specifies quantity amount returned if not the full amount from the TradeState, e.g. partial return
	* @param payerReceiver 
	* @return cashSettlementAmount 
	*/
	public Transfer evaluate(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(quantity)).getOrDefault(false)) {
				return areEqual(MapperS.of(quantity).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FinancialUnitEnum>map("getFinancialUnit", unitType -> unitType.getFinancialUnit()), MapperS.of(FinancialUnitEnum.SHARE), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"");
		
		conditionValidator.validate(() -> areEqual(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()), assetPayout(tradeState, date, quantity, payerReceiver).<Product>map("getSecurityInformation", _assetPayout -> _assetPayout.getSecurityInformation()).<Security>map("getSecurity", product -> product.getSecurity()).<ReferenceWithMetaProductIdentifier>mapC("getProductIdentifier", productBase -> productBase.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()), CardinalityOperator.All),
			"");
		
		Transfer.TransferBuilder cashSettlementAmountBuilder = doEvaluate(tradeState, date, quantity, payerReceiver);
		
		final Transfer cashSettlementAmount;
		if (cashSettlementAmountBuilder == null) {
			cashSettlementAmount = null;
		} else {
			cashSettlementAmount = cashSettlementAmountBuilder.build();
			objectValidator.validate(Transfer.class, cashSettlementAmount);
		}
		
		return cashSettlementAmount;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	protected abstract MapperS<? extends AssetPayout> assetPayout(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	protected abstract MapperS<? extends Collateral> collateral(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	protected abstract MapperC<? extends QuantitySchedule> securityQuantity(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	protected abstract MapperS<? extends PriceSchedule> securityPrice(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	protected abstract MapperS<BigDecimal> marginRatio(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver);

	public static class SecurityFinanceCashSettlementAmountDefault extends SecurityFinanceCashSettlementAmount {
		@Override
		protected Transfer.TransferBuilder doEvaluate(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			Transfer.TransferBuilder cashSettlementAmount = Transfer.builder();
			return assignOutput(cashSettlementAmount, tradeState, date, quantity, payerReceiver);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder cashSettlementAmount, TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			cashSettlementAmount
				.getOrCreateQuantity()
				.setValue(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(securityPrice(tradeState, date, quantity, payerReceiver).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), securityQuantity(tradeState, date, quantity, payerReceiver).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())), marginRatio(tradeState, date, quantity, payerReceiver)).get());
			
			cashSettlementAmount
				.getOrCreateQuantity()
				.getOrCreateUnit()
				.setCurrencyValue(securityPrice(tradeState, date, quantity, payerReceiver).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			final Party ifThenElseResult0;
			if (exists(MapperS.of(payerReceiver)).getOrDefault(false)) {
				ifThenElseResult0 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(payerReceiver).<CounterpartyRoleEnum>map("getReceiver", _payerReceiver -> _payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else if (exists(assetPayout(tradeState, date, quantity, payerReceiver).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", _payerReceiver -> _payerReceiver.getReceiver())).getOrDefault(false)) {
				ifThenElseResult0 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), assetPayout(tradeState, date, quantity, payerReceiver).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", _payerReceiver -> _payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else {
				ifThenElseResult0 = null;
			}
			cashSettlementAmount
				.getOrCreatePayerReceiver()
				.setPayerPartyReferenceValue(ifThenElseResult0);
			
			final Party ifThenElseResult1;
			if (exists(MapperS.of(payerReceiver)).getOrDefault(false)) {
				ifThenElseResult1 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(payerReceiver).<CounterpartyRoleEnum>map("getPayer", _payerReceiver -> _payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else if (exists(assetPayout(tradeState, date, quantity, payerReceiver).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", _payerReceiver -> _payerReceiver.getPayer())).getOrDefault(false)) {
				ifThenElseResult1 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), assetPayout(tradeState, date, quantity, payerReceiver).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", _payerReceiver -> _payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else {
				ifThenElseResult1 = null;
			}
			cashSettlementAmount
				.getOrCreatePayerReceiver()
				.setReceiverPartyReferenceValue(ifThenElseResult1);
			
			cashSettlementAmount
				.getOrCreateSettlementDate()
				.setAdjustedDateValue(date);
			
			final AssetPayout cashSettlementAmountSettlementOriginAssetPayout = assetPayout(tradeState, date, quantity, payerReceiver).get();
			cashSettlementAmount
				.getOrCreateSettlementOrigin()
				.setAssetPayout(ReferenceWithMetaAssetPayout.builder()
					.setGlobalReference(Optional.ofNullable(cashSettlementAmountSettlementOriginAssetPayout)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(cashSettlementAmountSettlementOriginAssetPayout)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			return Optional.ofNullable(cashSettlementAmount)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends AssetPayout> assetPayout(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			return MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", _collateral -> _collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()).get());
		}
		
		@Override
		protected MapperS<? extends Collateral> collateral(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral());
		}
		
		@Override
		protected MapperC<? extends QuantitySchedule> securityQuantity(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			if (exists(MapperS.of(quantity)).getOrDefault(false)) {
				return MapperC.of(Collections.singletonList(quantity));
			}
			return MapperC.<QuantitySchedule>of(filterQuantityByFinancialUnit.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti(), FinancialUnitEnum.SHARE));
		}
		
		@Override
		protected MapperS<? extends PriceSchedule> securityPrice(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			return MapperS.of(filterPrice.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).getMulti(), PriceTypeEnum.ASSET_PRICE, Collections.<ArithmeticOperationEnum>emptyList(), null));
		}
		
		@Override
		protected MapperS<BigDecimal> marginRatio(TradeState tradeState, Date date, Quantity quantity, PayerReceiver payerReceiver) {
			if (exists(MapperS.of(collateral(tradeState, date, quantity, payerReceiver).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getHaircutPercentage", collateralValuationTreatment -> collateralValuationTreatment.getHaircutPercentage())).getOrDefault(false)) {
				return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperS.of(BigDecimal.valueOf(1)), MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(MapperS.of(new BigDecimal("1.0")), MapperS.of(collateral(tradeState, date, quantity, payerReceiver).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getHaircutPercentage", collateralValuationTreatment -> collateralValuationTreatment.getHaircutPercentage())));
			}
			if (exists(MapperS.of(collateral(tradeState, date, quantity, payerReceiver).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getMarginPercentage", collateralValuationTreatment -> collateralValuationTreatment.getMarginPercentage())).getOrDefault(false)) {
				return MapperS.of(collateral(tradeState, date, quantity, payerReceiver).<CollateralProvisions>map("getCollateralProvisions", _collateral -> _collateral.getCollateralProvisions()).<EligibleCollateralCriteria>mapC("getEligibleCollateral", collateralProvisions -> collateralProvisions.getEligibleCollateral()).get()).<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<CollateralValuationTreatment>map("getValuationTreatment", collateralTreatment -> collateralTreatment.getValuationTreatment()).<BigDecimal>map("getMarginPercentage", collateralValuationTreatment -> collateralValuationTreatment.getMarginPercentage());
			}
			return MapperS.of(new BigDecimal("1.0"));
		}
	}
}
