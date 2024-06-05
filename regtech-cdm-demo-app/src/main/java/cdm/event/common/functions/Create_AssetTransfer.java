package cdm.event.common.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.Quantity;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.functions.FilterQuantityByFinancialUnit;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaProductIdentifier;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.functions.ExtractCounterpartyByRole;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.functions.FilterPrice;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.collateral.Collateral;
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
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_AssetTransfer.Create_AssetTransferDefault.class)
public abstract class Create_AssetTransfer implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ExtractCounterpartyByRole extractCounterpartyByRole;
	@Inject protected FilterPrice filterPrice;
	@Inject protected FilterQuantityByFinancialUnit filterQuantityByFinancialUnit;

	/**
	* @param instruction 
	* @return transfer 
	*/
	public Transfer evaluate(CalculateTransferInstruction instruction) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(instruction).<Quantity>map("getQuantity", calculateTransferInstruction -> calculateTransferInstruction.getQuantity())).getOrDefault(false)) {
				return areEqual(MapperS.of(instruction).<Quantity>map("getQuantity", calculateTransferInstruction -> calculateTransferInstruction.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FinancialUnitEnum>map("getFinancialUnit", unitType -> unitType.getFinancialUnit()), MapperS.of(FinancialUnitEnum.SHARE), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"");
		
		Transfer.TransferBuilder transferBuilder = doEvaluate(instruction);
		
		final Transfer transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.build();
			objectValidator.validate(Transfer.class, transfer);
		}
		
		return transfer;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(CalculateTransferInstruction instruction);

	protected abstract MapperS<? extends AssetPayout> assetPayout(CalculateTransferInstruction instruction);

	protected abstract MapperS<? extends QuantitySchedule> tradeQuantity(CalculateTransferInstruction instruction);

	protected abstract MapperS<? extends Quantity> securityQuantity(CalculateTransferInstruction instruction);

	protected abstract MapperS<? extends PriceSchedule> securityPrice(CalculateTransferInstruction instruction);

	public static class Create_AssetTransferDefault extends Create_AssetTransfer {
		@Override
		protected Transfer.TransferBuilder doEvaluate(CalculateTransferInstruction instruction) {
			Transfer.TransferBuilder transfer = Transfer.builder();
			return assignOutput(transfer, instruction);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder transfer, CalculateTransferInstruction instruction) {
			transfer
				.setQuantity(NonNegativeQuantity.builder()
					.setValue(securityQuantity(instruction).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
					.setUnit(securityQuantity(instruction).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.build()
				);
			
			transfer
				.getOrCreateObservable()
				.addProductIdentifierValue(assetPayout(instruction).<Product>map("getSecurityInformation", _assetPayout -> _assetPayout.getSecurityInformation()).<Security>map("getSecurity", product -> product.getSecurity()).<ReferenceWithMetaProductIdentifier>mapC("getProductIdentifier", productBase -> productBase.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()).getMulti());
			
			final Party ifThenElseResult0;
			if (exists(MapperS.of(instruction).<PayerReceiver>map("getPayerReceiver", calculateTransferInstruction -> calculateTransferInstruction.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer())).getOrDefault(false)) {
				ifThenElseResult0 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(instruction).<PayerReceiver>map("getPayerReceiver", calculateTransferInstruction -> calculateTransferInstruction.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else if (exists(assetPayout(instruction).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer())).getOrDefault(false)) {
				ifThenElseResult0 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), assetPayout(instruction).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else {
				ifThenElseResult0 = null;
			}
			transfer
				.getOrCreatePayerReceiver()
				.setPayerPartyReferenceValue(ifThenElseResult0);
			
			final Party ifThenElseResult1;
			if (exists(MapperS.of(instruction).<PayerReceiver>map("getPayerReceiver", calculateTransferInstruction -> calculateTransferInstruction.getPayerReceiver()).<CounterpartyRoleEnum>map("getPayer", payerReceiver -> payerReceiver.getPayer())).getOrDefault(false)) {
				ifThenElseResult1 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(instruction).<PayerReceiver>map("getPayerReceiver", calculateTransferInstruction -> calculateTransferInstruction.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", payerReceiver -> payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else if (exists(assetPayout(instruction).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", payerReceiver -> payerReceiver.getReceiver())).getOrDefault(false)) {
				ifThenElseResult1 = MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), assetPayout(instruction).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).<CounterpartyRoleEnum>map("getReceiver", payerReceiver -> payerReceiver.getReceiver()).get())).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
			} else {
				ifThenElseResult1 = null;
			}
			transfer
				.getOrCreatePayerReceiver()
				.setReceiverPartyReferenceValue(ifThenElseResult1);
			
			transfer
				.getOrCreateSettlementDate()
				.setAdjustedDateValue(MapperS.of(instruction).<Date>map("getDate", calculateTransferInstruction -> calculateTransferInstruction.getDate()).get());
			
			AssetPayout transferSettlementOriginAssetPayout = null;
			if (exists(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", collateral -> collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout())).getOrDefault(false)) {
				transferSettlementOriginAssetPayout = MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", collateral -> collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()).get();
			}
			transfer
				.getOrCreateSettlementOrigin()
				.setAssetPayout(ReferenceWithMetaAssetPayout.builder()
					.setGlobalReference(Optional.ofNullable(transferSettlementOriginAssetPayout)
						.map(r -> r.getMeta())
						.map(m -> m.getGlobalKey())
						.orElse(null))
					.setExternalReference(Optional.ofNullable(transferSettlementOriginAssetPayout)
						.map(r -> r.getMeta())
						.map(m -> m.getExternalKey())
						.orElse(null))
					.build()
				);
			
			return Optional.ofNullable(transfer)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends AssetPayout> assetPayout(CalculateTransferInstruction instruction) {
			return MapperS.of(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", collateral -> collateral.getCollateralPortfolio()).<CollateralPortfolio>map("getValue", _f->_f.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", position -> position.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>mapC("getAssetPayout", payout -> payout.getAssetPayout()).get());
		}
		
		@Override
		protected MapperS<? extends QuantitySchedule> tradeQuantity(CalculateTransferInstruction instruction) {
			return MapperS.of(MapperC.of(filterQuantityByFinancialUnit.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti(), FinancialUnitEnum.SHARE)).get());
		}
		
		@Override
		protected MapperS<? extends Quantity> securityQuantity(CalculateTransferInstruction instruction) {
			if (exists(MapperS.of(instruction).<Quantity>map("getQuantity", calculateTransferInstruction -> calculateTransferInstruction.getQuantity())).getOrDefault(false)) {
				return MapperS.of(instruction).<Quantity>map("getQuantity", calculateTransferInstruction -> calculateTransferInstruction.getQuantity());
			}
			return MapperS.of(NonNegativeQuantity.builder()
				.setValue(tradeQuantity(instruction).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
				.setUnit(tradeQuantity(instruction).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
				.build()
			);
		}
		
		@Override
		protected MapperS<? extends PriceSchedule> securityPrice(CalculateTransferInstruction instruction) {
			return MapperS.of(filterPrice.evaluate(MapperS.of(instruction).<TradeState>map("getTradeState", calculateTransferInstruction -> calculateTransferInstruction.getTradeState()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).getMulti(), PriceTypeEnum.ASSET_PRICE, Collections.<ArithmeticOperationEnum>emptyList(), null));
		}
	}
}
