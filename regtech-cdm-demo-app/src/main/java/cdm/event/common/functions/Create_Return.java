package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.Quantity;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.math.UnitType;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.BusinessEvent;
import cdm.event.common.BusinessEvent.BusinessEventBuilder;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.ReturnInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Return.Create_ReturnDefault.class)
public abstract class Create_Return implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_QuantityChange create_QuantityChange;

	/**
	* @param tradeState Specifies a previously formed contractual product with a Security Finance payout. It is required that the description of the contractual product be contained within the previous business event, i.e. its lineage must contain the formation of a contractual product.
	* @param returnInstruction Specifies the information required to fully return the Stock Loan in accordance with the economic terms of the contractual product.
	* @param returnDate Specifies the date of the full return.
	* @return returnEvent Produces the business event composed of primitive events describing the transfer and termination, as a result of the input return instruction.
	*/
	public BusinessEvent evaluate(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate) {
		BusinessEvent.BusinessEventBuilder returnEventBuilder = doEvaluate(tradeState, returnInstruction, returnDate);
		
		final BusinessEvent returnEvent;
		if (returnEventBuilder == null) {
			returnEvent = null;
		} else {
			returnEvent = returnEventBuilder.build();
			objectValidator.validate(BusinessEvent.class, returnEvent);
		}
		
		return returnEvent;
	}

	protected abstract BusinessEvent.BusinessEventBuilder doEvaluate(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate);

	protected abstract MapperS<? extends TradableProduct> tradableProduct(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate);

	protected abstract MapperC<? extends NonNegativeQuantitySchedule> quantitySchedule(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate);

	protected abstract MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate);

	public static class Create_ReturnDefault extends Create_Return {
		@Override
		protected BusinessEvent.BusinessEventBuilder doEvaluate(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate) {
			BusinessEvent.BusinessEventBuilder returnEvent = BusinessEvent.builder();
			return assignOutput(returnEvent, tradeState, returnInstruction, returnDate);
		}
		
		protected BusinessEvent.BusinessEventBuilder assignOutput(BusinessEvent.BusinessEventBuilder returnEvent, TradeState tradeState0, ReturnInstruction returnInstruction, Date returnDate) {
			final TradeState tradeState1 = create_QuantityChange.evaluate(QuantityChangeInstruction.builder()
				.setChange(new ArrayList<>(changePriceQuantity(tradeState0, returnInstruction, returnDate).getMulti()))
				.setDirection(QuantityChangeDirectionEnum.DECREASE)
				.setLotIdentifier(Collections.<Identifier>emptyList())
				.build()
			, tradeState0);
			returnEvent
				.addAfter((tradeState1 == null ? Collections.<TradeState>emptyList() : Collections.singletonList(tradeState1)));
			
			returnEvent
				.setEventDate(returnDate);
			
			return Optional.ofNullable(returnEvent)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends TradableProduct> tradableProduct(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperC<? extends NonNegativeQuantitySchedule> quantitySchedule(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate) {
			return MapperS.of(returnInstruction).<Quantity>mapC("getQuantity", _returnInstruction -> _returnInstruction.getQuantity())
				.mapItem(item -> MapperS.of(NonNegativeQuantitySchedule.builder()
					.setValue(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
					.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.build()
				));
		}
		
		@Override
		protected MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState, ReturnInstruction returnInstruction, Date returnDate) {
			return MapperS.of(PriceQuantity.builder()
				.setQuantityValue(new ArrayList<>(quantitySchedule(tradeState, returnInstruction, returnDate).getMulti()))
				.build()
			);
		}
	}
}
