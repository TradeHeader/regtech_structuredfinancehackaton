package cdm.event.common.functions;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.ResetInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.State;
import cdm.event.common.StockSplitInstruction;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.common.TransferInstruction;
import cdm.event.common.ValuationInstruction;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.ClosedState;
import cdm.legaldocumentation.common.ClosedStateEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_TradeState.Create_TradeStateDefault.class)
public abstract class Create_TradeState implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_ContractFormation create_ContractFormation;
	@Inject protected Create_Execution create_Execution;
	@Inject protected Create_IndexTransitionTermsChange create_IndexTransitionTermsChange;
	@Inject protected Create_Observation create_Observation;
	@Inject protected Create_PartyChange create_PartyChange;
	@Inject protected Create_QuantityChange create_QuantityChange;
	@Inject protected Create_Reset create_Reset;
	@Inject protected Create_StockSplit create_StockSplit;
	@Inject protected Create_TermsChange create_TermsChange;
	@Inject protected Create_Transfer create_Transfer;
	@Inject protected Create_Valuation create_Valuation;

	/**
	* @param primitiveInstruction The set of primitive instructions to apply to the trade.
	* @param before The original trade on which the primitive instructions are applied
	* @return after The returned trade state must be of single cardinality. Where a different trade is created and the original trade must be persisted (for instance showing as &#39;closed&#39;), it should be preceded by a split instruction.
	*/
	public TradeState evaluate(PrimitiveInstruction primitiveInstruction, TradeState before) {
		// pre-conditions
		conditionValidator.validate(() -> notExists(MapperS.of(primitiveInstruction).<SplitInstruction>map("getSplit", _primitiveInstruction -> _primitiveInstruction.getSplit())),
			"The primitive instruction cannot contain a split, as this function is designed to return a single trade state.");
		
		TradeState.TradeStateBuilder afterBuilder = doEvaluate(primitiveInstruction, before);
		
		final TradeState after;
		if (afterBuilder == null) {
			after = null;
		} else {
			after = afterBuilder.build();
			objectValidator.validate(TradeState.class, after);
		}
		
		return after;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> execution(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> quantityChange(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> termsChange(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> partyChange(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> contractFormation(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> transfer(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> reset(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> indexTransition(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> observation(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> valuation(PrimitiveInstruction primitiveInstruction, TradeState before);

	protected abstract MapperS<? extends TradeState> stockSplit(PrimitiveInstruction primitiveInstruction, TradeState before);

	public static class Create_TradeStateDefault extends Create_TradeState {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(PrimitiveInstruction primitiveInstruction, TradeState before) {
			TradeState.TradeStateBuilder after = TradeState.builder();
			return assignOutput(after, primitiveInstruction, before);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder after, PrimitiveInstruction primitiveInstruction, TradeState before) {
			after = toBuilder(stockSplit(primitiveInstruction, before).get());
			
			ClosedState ifThenElseResult = null;
			if (areEqual(contractFormation(primitiveInstruction, before).<State>map("getState", tradeState -> tradeState.getState()).<PositionStatusEnum>map("getPositionState", state -> state.getPositionState()), MapperS.of(PositionStatusEnum.CLOSED), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult = ClosedState.builder()
					.setState(ClosedStateEnum.TERMINATED)
					.setActivityDate(null)
					.build()
				;
			}
			after
				.getOrCreateState()
				.setClosedState(ifThenElseResult);
			
			return Optional.ofNullable(after)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends TradeState> execution(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution())).getOrDefault(false)) {
				return MapperS.of(before);
			}
			return MapperS.of(create_Execution.evaluate(MapperS.of(primitiveInstruction).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> quantityChange(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange())).getOrDefault(false)) {
				return execution(primitiveInstruction, before);
			}
			return MapperS.of(create_QuantityChange.evaluate(MapperS.of(primitiveInstruction).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange()).get(), execution(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> termsChange(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<TermsChangeInstruction>map("getTermsChange", _primitiveInstruction -> _primitiveInstruction.getTermsChange())).getOrDefault(false)) {
				return quantityChange(primitiveInstruction, before);
			}
			return MapperS.of(create_TermsChange.evaluate(MapperS.of(primitiveInstruction).<TermsChangeInstruction>map("getTermsChange", _primitiveInstruction -> _primitiveInstruction.getTermsChange()).get(), quantityChange(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> partyChange(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<PartyChangeInstruction>map("getPartyChange", _primitiveInstruction -> _primitiveInstruction.getPartyChange())).getOrDefault(false)) {
				return termsChange(primitiveInstruction, before);
			}
			return MapperS.of(create_PartyChange.evaluate(MapperS.of(primitiveInstruction).<PartyChangeInstruction>map("getPartyChange", _primitiveInstruction -> _primitiveInstruction.getPartyChange()).<Counterparty>map("getCounterparty", partyChangeInstruction -> partyChangeInstruction.getCounterparty()).get(), MapperS.of(primitiveInstruction).<PartyChangeInstruction>map("getPartyChange", _primitiveInstruction -> _primitiveInstruction.getPartyChange()).<AncillaryParty>map("getAncillaryParty", partyChangeInstruction -> partyChangeInstruction.getAncillaryParty()).get(), MapperS.of(primitiveInstruction).<PartyChangeInstruction>map("getPartyChange", _primitiveInstruction -> _primitiveInstruction.getPartyChange()).<PartyRole>map("getPartyRole", partyChangeInstruction -> partyChangeInstruction.getPartyRole()).get(), MapperS.of(primitiveInstruction).<PartyChangeInstruction>map("getPartyChange", _primitiveInstruction -> _primitiveInstruction.getPartyChange()).<TradeIdentifier>mapC("getTradeId", partyChangeInstruction -> partyChangeInstruction.getTradeId()).getMulti(), termsChange(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> contractFormation(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (exists(MapperS.of(primitiveInstruction)).and(notExists(MapperS.of(primitiveInstruction).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation()))).getOrDefault(false)) {
				return partyChange(primitiveInstruction, before);
			}
			return MapperS.of(create_ContractFormation.evaluate(MapperS.of(primitiveInstruction).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation()).get(), partyChange(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> transfer(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer())).getOrDefault(false)) {
				return contractFormation(primitiveInstruction, before);
			}
			return MapperS.of(create_Transfer.evaluate(MapperS.of(primitiveInstruction).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer()).get(), contractFormation(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> reset(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<ResetInstruction>map("getReset", _primitiveInstruction -> _primitiveInstruction.getReset())).getOrDefault(false)) {
				return transfer(primitiveInstruction, before);
			}
			return MapperS.of(create_Reset.evaluate(MapperS.of(primitiveInstruction).<ResetInstruction>map("getReset", _primitiveInstruction -> _primitiveInstruction.getReset()).get(), transfer(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> indexTransition(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<IndexTransitionInstruction>map("getIndexTransition", _primitiveInstruction -> _primitiveInstruction.getIndexTransition())).getOrDefault(false)) {
				return reset(primitiveInstruction, before);
			}
			return MapperS.of(create_IndexTransitionTermsChange.evaluate(MapperS.of(primitiveInstruction).<IndexTransitionInstruction>map("getIndexTransition", _primitiveInstruction -> _primitiveInstruction.getIndexTransition()).get(), reset(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> observation(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<ObservationInstruction>map("getObservation", _primitiveInstruction -> _primitiveInstruction.getObservation())).getOrDefault(false)) {
				return indexTransition(primitiveInstruction, before);
			}
			return MapperS.of(create_Observation.evaluate(MapperS.of(primitiveInstruction).<ObservationInstruction>map("getObservation", _primitiveInstruction -> _primitiveInstruction.getObservation()).get(), indexTransition(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> valuation(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<ValuationInstruction>map("getValuation", _primitiveInstruction -> _primitiveInstruction.getValuation())).getOrDefault(false)) {
				return observation(primitiveInstruction, before);
			}
			return MapperS.of(create_Valuation.evaluate(MapperS.of(primitiveInstruction).<ValuationInstruction>map("getValuation", _primitiveInstruction -> _primitiveInstruction.getValuation()).get(), observation(primitiveInstruction, before).get()));
		}
		
		@Override
		protected MapperS<? extends TradeState> stockSplit(PrimitiveInstruction primitiveInstruction, TradeState before) {
			if (notExists(MapperS.of(primitiveInstruction).<StockSplitInstruction>map("getStockSplit", _primitiveInstruction -> _primitiveInstruction.getStockSplit())).getOrDefault(false)) {
				return valuation(primitiveInstruction, before);
			}
			return MapperS.of(create_StockSplit.evaluate(MapperS.of(primitiveInstruction).<StockSplitInstruction>map("getStockSplit", _primitiveInstruction -> _primitiveInstruction.getStockSplit()).get(), valuation(primitiveInstruction, before).get()));
		}
	}
}
