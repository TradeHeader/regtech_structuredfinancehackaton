package cdm.event.common.functions;

import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.common.Valuation;
import cdm.event.common.ValuationInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_Valuation.Create_ValuationDefault.class)
public abstract class Create_Valuation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instruction 
	* @param before Specifies the trade to be updated.
	* @return after Specifies the resulting trade state incorporating the valuation update in the valuation history.
	*/
	public TradeState evaluate(ValuationInstruction instruction, TradeState before) {
		TradeState.TradeStateBuilder afterBuilder = doEvaluate(instruction, before);
		
		final TradeState after;
		if (afterBuilder == null) {
			after = null;
		} else {
			after = afterBuilder.build();
			objectValidator.validate(TradeState.class, after);
		}
		
		return after;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(ValuationInstruction instruction, TradeState before);

	protected abstract MapperC<? extends Valuation> beforeValuationHistory(ValuationInstruction instruction, TradeState before);

	public static class Create_ValuationDefault extends Create_Valuation {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(ValuationInstruction instruction, TradeState before) {
			TradeState.TradeStateBuilder after = TradeState.builder();
			return assignOutput(after, instruction, before);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder after, ValuationInstruction instruction, TradeState before) {
			after = toBuilder(before);
			
			after
				.setValuationHistory(beforeValuationHistory(instruction, before).getMulti());
			
			after
				.addValuationHistory(MapperS.of(instruction).<Valuation>mapC("getValuation", valuationInstruction -> valuationInstruction.getValuation()).getMulti());
			
			return Optional.ofNullable(after)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends Valuation> beforeValuationHistory(ValuationInstruction instruction, TradeState before) {
			if (areEqual(MapperS.of(instruction).<Boolean>map("getReplace", valuationInstruction -> valuationInstruction.getReplace()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				return MapperC.<Valuation>ofNull();
			}
			return MapperS.of(before).<Valuation>mapC("getValuationHistory", tradeState -> tradeState.getValuationHistory());
		}
	}
}
