package cdm.event.common.functions;

import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.product.common.settlement.PriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_IndexTransitionTermsChange.Create_IndexTransitionTermsChangeDefault.class)
public abstract class Create_IndexTransitionTermsChange implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected UpdateSpreadAdjustmentAndRateOptions updateSpreadAdjustmentAndRateOptions;

	/**
	* @param instruction Specifies the instructions containing the floating rate index, spread adjustment for each leg to be updated, and the effective date.
	* @param tradeState Specifies the trade to be updated.
	* @return termsChange Specifies the resulting term change.
	*/
	public TradeState evaluate(IndexTransitionInstruction instruction, TradeState tradeState) {
		TradeState.TradeStateBuilder termsChangeBuilder = doEvaluate(instruction, tradeState);
		
		final TradeState termsChange;
		if (termsChangeBuilder == null) {
			termsChange = null;
		} else {
			termsChange = termsChangeBuilder.build();
			objectValidator.validate(TradeState.class, termsChange);
		}
		
		return termsChange;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(IndexTransitionInstruction instruction, TradeState tradeState);

	public static class Create_IndexTransitionTermsChangeDefault extends Create_IndexTransitionTermsChange {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(IndexTransitionInstruction instruction, TradeState tradeState) {
			TradeState.TradeStateBuilder termsChange = TradeState.builder();
			return assignOutput(termsChange, instruction, tradeState);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder termsChange, IndexTransitionInstruction instruction, TradeState tradeState) {
			termsChange = toBuilder(updateSpreadAdjustmentAndRateOptions.evaluate(tradeState, MapperS.of(instruction).<PriceQuantity>mapC("getPriceQuantity", indexTransitionInstruction -> indexTransitionInstruction.getPriceQuantity()).getMulti()));
			
			return Optional.ofNullable(termsChange)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
