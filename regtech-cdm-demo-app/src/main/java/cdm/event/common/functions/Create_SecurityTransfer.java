package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.Quantity;
import cdm.base.math.UnitType;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import cdm.observable.asset.Observable;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.TransferSettlementEnum;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Arrays;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_SecurityTransfer.Create_SecurityTransferDefault.class)
public abstract class Create_SecurityTransfer implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeState 
	* @param date 
	* @param quantity Specifies quantity amount returned if not the full amount from the TradeState, e.g. partial return
	* @return transfer 
	*/
	public Transfer evaluate(TradeState tradeState, Date date, Quantity quantity) {
		Transfer.TransferBuilder transferBuilder = doEvaluate(tradeState, date, quantity);
		
		final Transfer transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.build();
			objectValidator.validate(Transfer.class, transfer);
		}
		
		// post-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<Security>map("getSecurity", product -> product.getSecurity())).and(areEqual(MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<SettlementTerms>map("getSettlementTerms", priceQuantity -> priceQuantity.getSettlementTerms()).<TransferSettlementEnum>map("getTransferSettlementType", settlementBase -> settlementBase.getTransferSettlementType()).get()), MapperS.of(TransferSettlementEnum.DELIVERY_VERSUS_PAYMENT), CardinalityOperator.All)).getOrDefault(false)) {
				return onlyExists(Arrays.asList(MapperS.of(transfer).<NonNegativeQuantity>map("getQuantity", transferBase -> transferBase.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()))).and(exists(MapperS.of(transfer).<Observable>map("getObservable", transferBase -> transferBase.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue())));
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"");
		
		return transfer;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(TradeState tradeState, Date date, Quantity quantity);

	public static class Create_SecurityTransferDefault extends Create_SecurityTransfer {
		@Override
		protected Transfer.TransferBuilder doEvaluate(TradeState tradeState, Date date, Quantity quantity) {
			Transfer.TransferBuilder transfer = Transfer.builder();
			return assignOutput(transfer, tradeState, date, quantity);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder transfer, TradeState tradeState, Date date, Quantity quantity) {
			return Optional.ofNullable(transfer)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
