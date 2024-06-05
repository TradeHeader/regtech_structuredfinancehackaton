package cdm.event.common.functions;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ContractDetails;
import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilder;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.State;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_ShapingInstruction.Create_ShapingInstructionDefault.class)
public abstract class Create_ShapingInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_ContractFormationInstruction create_ContractFormationInstruction;
	@Inject protected Create_PackageExecutionDetails create_PackageExecutionDetails;
	@Inject protected Create_TerminationInstruction create_TerminationInstruction;

	/**
	* @param tradeState The original trade to be shaped.
	* @param tradeLots The shaped quantities provided as full set of trade lots with price and quantity. Each trade lot also contains an identifier to associate to the corresponding shaped trade. Shaping must result in at least 2 shaped trades.
	* @param shapeIdentifier The package ID of the shaped trades.
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier) {
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, tradeLots, shapeIdentifier);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier);

	protected abstract MapperC<? extends Identifier> componentId(TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier);

	public static class Create_ShapingInstructionDefault extends Create_ShapingInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier) {
			if (tradeLots == null) {
				tradeLots = Collections.emptyList();
			}
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, tradeLots, shapeIdentifier);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier) {
			instruction
				.getOrCreateSplit()
				.setBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(create_TerminationInstruction.evaluate(tradeState))).getMulti());
			
			final MapperListOfLists<PriceQuantity> thenResult = MapperC.<TradeLot>of(tradeLots)
				.mapItemToList(item -> item.<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()));
			instruction
				.getOrCreateSplit()
				.addBreakdown(thenResult
					.mapListToItem(item -> {
						ContractFormationInstruction ifThenElseResult = null;
						if (areEqual(MapperS.of(tradeState).<State>map("getState", _tradeState -> _tradeState.getState()).<PositionStatusEnum>map("getPositionState", state -> state.getPositionState()), MapperS.of(PositionStatusEnum.FORMED), CardinalityOperator.All).getOrDefault(false)) {
							ifThenElseResult = create_ContractFormationInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ContractDetails>map("getContractDetails", trade -> trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).getMulti());
						}
						return MapperS.of(PrimitiveInstruction.builder()
							.setContractFormation(ifThenElseResult)
							.setExecution(ExecutionInstruction.builder()
								.setProduct(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).get())
								.setPriceQuantity(MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).getMulti())
								.setCounterparty(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti())
								.setAncillaryParty(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<AncillaryParty>mapC("getAncillaryParty", tradableProduct -> tradableProduct.getAncillaryParty()).getMulti())
								.setParties(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<Party>mapC("getParty", trade -> trade.getParty()).getMulti())
								.setPartyRoles(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<PartyRole>mapC("getPartyRole", trade -> trade.getPartyRole()).getMulti())
								.setExecutionDetails(create_PackageExecutionDetails.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ExecutionDetails>map("getExecutionDetails", trade -> trade.getExecutionDetails()).get(), shapeIdentifier, componentId(tradeState, tradeLots, shapeIdentifier).getMulti()))
								.setTradeDateValue(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<FieldWithMetaDate>map("getTradeDate", trade -> trade.getTradeDate()).<Date>map("getValue", _f->_f.getValue()).get())
								.setTradeIdentifier(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()).getMulti())
								.build()
							)
							.setQuantityChange(QuantityChangeInstruction.builder()
								.setChange(item.getMulti())
								.setDirection(QuantityChangeDirectionEnum.REPLACE)
								.setLotIdentifier(Collections.<Identifier>emptyList())
								.build()
							)
							.build()
						);
					}).getMulti());
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends Identifier> componentId(TradeState tradeState, List<? extends TradeLot> tradeLots, Identifier shapeIdentifier) {
			return MapperC.<TradeLot>of(tradeLots)
				.mapItem(item -> MapperS.of(item.<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier()).get()));
		}
	}
}
