package cdm.event.common.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.position.PositionStatusEnum;
import cdm.product.collateral.Collateral;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Execution.Create_ExecutionDefault.class)
public abstract class Create_Execution implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instruction Instructions to be used as an input to the function
	* @return execution Execution primitive event with absent before state and an after state containing the tradable product, parties, associated party roles and the known settlement terms.
	*/
	public TradeState evaluate(ExecutionInstruction instruction) {
		TradeState.TradeStateBuilder executionBuilder = doEvaluate(instruction);
		
		final TradeState execution;
		if (executionBuilder == null) {
			execution = null;
		} else {
			execution = executionBuilder.build();
			objectValidator.validate(TradeState.class, execution);
		}
		
		return execution;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(ExecutionInstruction instruction);

	public static class Create_ExecutionDefault extends Create_Execution {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(ExecutionInstruction instruction) {
			TradeState.TradeStateBuilder execution = TradeState.builder();
			return assignOutput(execution, instruction);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder execution, ExecutionInstruction instruction) {
			execution
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.setProduct(MapperS.of(instruction).<Product>map("getProduct", executionInstruction -> executionInstruction.getProduct()).get());
			
			final TradeLot tradeLot = TradeLot.builder()
				.setPriceQuantity(MapperS.of(instruction).<PriceQuantity>mapC("getPriceQuantity", executionInstruction -> executionInstruction.getPriceQuantity()).getMulti())
				.setLotIdentifier(MapperS.of(instruction).<Identifier>map("getLotIdentifier", executionInstruction -> executionInstruction.getLotIdentifier()).getMulti())
				.build()
			;
			execution
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.addTradeLot((tradeLot == null ? Collections.<TradeLot>emptyList() : Collections.singletonList(tradeLot)));
			
			execution
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.addCounterparty(MapperS.of(instruction).<Counterparty>mapC("getCounterparty", executionInstruction -> executionInstruction.getCounterparty()).getMulti());
			
			execution
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.addAncillaryParty(MapperS.of(instruction).<AncillaryParty>mapC("getAncillaryParty", executionInstruction -> executionInstruction.getAncillaryParty()).getMulti());
			
			execution
				.getOrCreateTrade()
				.addParty(MapperS.of(instruction).<Party>mapC("getParties", executionInstruction -> executionInstruction.getParties()).getMulti());
			
			execution
				.getOrCreateTrade()
				.addPartyRole(MapperS.of(instruction).<PartyRole>mapC("getPartyRoles", executionInstruction -> executionInstruction.getPartyRoles()).getMulti());
			
			execution
				.getOrCreateTrade()
				.setExecutionDetails(MapperS.of(instruction).<ExecutionDetails>map("getExecutionDetails", executionInstruction -> executionInstruction.getExecutionDetails()).get());
			
			execution
				.getOrCreateTrade()
				.setTradeDateValue(MapperS.of(instruction).<FieldWithMetaDate>map("getTradeDate", executionInstruction -> executionInstruction.getTradeDate()).<Date>map("getValue", _f->_f.getValue()).get());
			
			execution
				.getOrCreateTrade()
				.addTradeIdentifier(MapperS.of(instruction).<TradeIdentifier>mapC("getTradeIdentifier", executionInstruction -> executionInstruction.getTradeIdentifier()).getMulti());
			
			execution
				.getOrCreateState()
				.setPositionState(PositionStatusEnum.EXECUTED);
			
			execution
				.getOrCreateTrade()
				.setCollateral(MapperS.of(instruction).<Collateral>map("getCollateral", executionInstruction -> executionInstruction.getCollateral()).get());
			
			return Optional.ofNullable(execution)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
