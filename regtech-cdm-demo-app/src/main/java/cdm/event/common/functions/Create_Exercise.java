package cdm.event.common.functions;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
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

@ImplementedBy(Create_Exercise.Create_ExerciseDefault.class)
public abstract class Create_Exercise implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_Execution create_Execution;
	@Inject protected Create_TradeState create_TradeState;

	/**
	* @param exerciseInstruction Instruction containing the terms of the option exercise.
	* @param originalTrade The original trade to be split, which must be of single cardinality.
	* @return exercise 
	*/
	public List<? extends TradeState> evaluate(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
		// pre-conditions
		conditionValidator.validate(() -> exists(optionPayout(exerciseInstruction, originalTrade)),
			"Requires that the original contract contains an option payout.");
		
		List<TradeState.TradeStateBuilder> exerciseBuilder = doEvaluate(exerciseInstruction, originalTrade);
		
		final List<? extends TradeState> exercise;
		if (exerciseBuilder == null) {
			exercise = null;
		} else {
			exercise = exerciseBuilder.stream().map(TradeState::build).collect(Collectors.toList());
			objectValidator.validate(TradeState.class, exercise);
		}
		
		return exercise;
	}

	protected abstract List<TradeState.TradeStateBuilder> doEvaluate(ExerciseInstruction exerciseInstruction, TradeState originalTrade);

	protected abstract MapperS<? extends TradableProduct> tradableProduct(ExerciseInstruction exerciseInstruction, TradeState originalTrade);

	protected abstract MapperS<? extends OptionPayout> optionPayout(ExerciseInstruction exerciseInstruction, TradeState originalTrade);

	protected abstract MapperS<? extends Product> underlier(ExerciseInstruction exerciseInstruction, TradeState originalTrade);

	protected abstract MapperS<? extends TradeState> execution(ExerciseInstruction exerciseInstruction, TradeState originalTrade);

	public static class Create_ExerciseDefault extends Create_Exercise {
		@Override
		protected List<TradeState.TradeStateBuilder> doEvaluate(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			List<TradeState.TradeStateBuilder> exercise = new ArrayList<>();
			return assignOutput(exercise, exerciseInstruction, originalTrade);
		}
		
		protected List<TradeState.TradeStateBuilder> assignOutput(List<TradeState.TradeStateBuilder> exercise, ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			final TradeState tradeState = create_TradeState.evaluate(MapperS.of(exerciseInstruction).<PrimitiveInstruction>map("getExerciseQuantity", _exerciseInstruction -> _exerciseInstruction.getExerciseQuantity()).get(), originalTrade);
			if (tradeState == null) {
				exercise.addAll(toBuilder(Collections.<TradeState>emptyList()));
			} else {
				exercise.addAll(toBuilder(Collections.singletonList(tradeState)));
			}
			
			exercise.addAll(toBuilder(execution(exerciseInstruction, originalTrade).getMulti()));
			
			return Optional.ofNullable(exercise)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends TradableProduct> tradableProduct(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			return MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperS<? extends OptionPayout> optionPayout(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			if (exists(MapperS.of(exerciseInstruction).<ReferenceWithMetaOptionPayout>map("getExerciseOption", _exerciseInstruction -> _exerciseInstruction.getExerciseOption()).<OptionPayout>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
				return MapperS.of(exerciseInstruction).<ReferenceWithMetaOptionPayout>map("getExerciseOption", _exerciseInstruction -> _exerciseInstruction.getExerciseOption()).<OptionPayout>map("getValue", _f->_f.getValue());
			}
			return MapperS.of(tradableProduct(exerciseInstruction, originalTrade).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<OptionPayout>mapC("getOptionPayout", payout -> payout.getOptionPayout()).get());
		}
		
		@Override
		protected MapperS<? extends Product> underlier(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			return optionPayout(exerciseInstruction, originalTrade).<Product>map("getUnderlier", _optionPayout -> _optionPayout.getUnderlier());
		}
		
		@Override
		protected MapperS<? extends TradeState> execution(ExerciseInstruction exerciseInstruction, TradeState originalTrade) {
			return MapperS.of(create_Execution.evaluate(ExecutionInstruction.builder()
				.setProduct(underlier(exerciseInstruction, originalTrade).get())
				.setPriceQuantity(MapperS.of(tradableProduct(exerciseInstruction, originalTrade).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).getMulti())
				.setCounterparty(tradableProduct(exerciseInstruction, originalTrade).<Counterparty>mapC("getCounterparty", _tradableProduct -> _tradableProduct.getCounterparty()).getMulti())
				.setAncillaryParty(tradableProduct(exerciseInstruction, originalTrade).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).getMulti())
				.setParties(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Party>mapC("getParty", trade -> trade.getParty()).getMulti())
				.setPartyRoles(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<PartyRole>mapC("getPartyRole", trade -> trade.getPartyRole()).getMulti())
				.setExecutionDetails(null)
				.setTradeDateValue(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<FieldWithMetaDate>map("getTradeDate", trade -> trade.getTradeDate()).<Date>map("getValue", _f->_f.getValue()).get())
				.setTradeIdentifier(MapperS.of(exerciseInstruction).<TradeIdentifier>mapC("getReplacementTradeIdentifier", _exerciseInstruction -> _exerciseInstruction.getReplacementTradeIdentifier()).getMulti())
				.build()
			));
		}
	}
}
