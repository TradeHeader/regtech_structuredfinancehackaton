package cdm.event.common.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ContractDetails;
import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.Instruction.InstructionBuilder;
import cdm.event.common.PrimitiveInstruction;
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
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_PairOffInstruction.Create_PairOffInstructionDefault.class)
public abstract class Create_PairOffInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_ContractFormationInstruction create_ContractFormationInstruction;
	@Inject protected Create_PackageExecutionDetails create_PackageExecutionDetails;

	/**
	* @param tradeState The trades to pair-off. There must be at least 2.
	* @param pairReference The reference ID of the paired trades.
	* @return instruction 
	*/
	public List<? extends Instruction> evaluate(List<? extends TradeState> tradeState, Identifier pairReference) {
		List<Instruction.InstructionBuilder> instructionBuilder = doEvaluate(tradeState, pairReference);
		
		final List<? extends Instruction> instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.stream().map(Instruction::build).collect(Collectors.toList());
			objectValidator.validate(Instruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract List<Instruction.InstructionBuilder> doEvaluate(List<? extends TradeState> tradeState, Identifier pairReference);

	protected abstract MapperC<? extends TradeIdentifier> componentId(List<? extends TradeState> tradeState, Identifier pairReference);

	public static class Create_PairOffInstructionDefault extends Create_PairOffInstruction {
		@Override
		protected List<Instruction.InstructionBuilder> doEvaluate(List<? extends TradeState> tradeState, Identifier pairReference) {
			if (tradeState == null) {
				tradeState = Collections.emptyList();
			}
			List<Instruction.InstructionBuilder> instruction = new ArrayList<>();
			return assignOutput(instruction, tradeState, pairReference);
		}
		
		protected List<Instruction.InstructionBuilder> assignOutput(List<Instruction.InstructionBuilder> instruction, List<? extends TradeState> tradeState, Identifier pairReference) {
			instruction = toBuilder(MapperC.<TradeState>of(tradeState)
				.mapItem(item -> {
					ContractFormationInstruction ifThenElseResult = null;
					if (areEqual(item.<State>map("getState", _tradeState -> _tradeState.getState()).<PositionStatusEnum>map("getPositionState", state -> state.getPositionState()), MapperS.of(PositionStatusEnum.FORMED), CardinalityOperator.All).getOrDefault(false)) {
						ifThenElseResult = create_ContractFormationInstruction.evaluate(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ContractDetails>map("getContractDetails", trade -> trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).getMulti());
					}
					return MapperS.of(Instruction.builder()
						.setBeforeValue(item.get())
						.setPrimitiveInstruction(PrimitiveInstruction.builder()
							.setContractFormation(ifThenElseResult)
							.setExecution(ExecutionInstruction.builder()
								.setProduct(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).get())
								.setPriceQuantity(MapperS.of(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).getMulti())
								.setCounterparty(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti())
								.setAncillaryParty(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<AncillaryParty>mapC("getAncillaryParty", tradableProduct -> tradableProduct.getAncillaryParty()).getMulti())
								.setParties(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<Party>mapC("getParty", trade -> trade.getParty()).getMulti())
								.setPartyRoles(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<PartyRole>mapC("getPartyRole", trade -> trade.getPartyRole()).getMulti())
								.setExecutionDetails(create_PackageExecutionDetails.evaluate(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<ExecutionDetails>map("getExecutionDetails", trade -> trade.getExecutionDetails()).get(), pairReference, componentId(tradeState, pairReference).getMulti()))
								.setTradeDateValue(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<FieldWithMetaDate>map("getTradeDate", trade -> trade.getTradeDate()).<Date>map("getValue", _f->_f.getValue()).get())
								.setTradeIdentifier(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()).getMulti())
								.build()
							)
							.build()
						)
						.build()
					);
				}).getMulti());
			
			return Optional.ofNullable(instruction)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends TradeIdentifier> componentId(List<? extends TradeState> tradeState, Identifier pairReference) {
			return MapperC.<TradeState>of(tradeState)
				.mapItem(item -> MapperS.of(item.<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()).get()));
		}
	}
}
