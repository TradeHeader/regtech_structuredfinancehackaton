package cdm.event.common.functions;

import cdm.event.common.Reset;
import cdm.event.common.ResetInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.observable.event.Observation;
import cdm.observable.event.ObservationIdentifier;
import cdm.observable.event.functions.ResolveObservation;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_Reset.Create_ResetDefault.class)
public abstract class Create_Reset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ResolveInterestRateObservationIdentifiers resolveInterestRateObservationIdentifiers;
	@Inject protected ResolveInterestRateReset resolveInterestRateReset;
	@Inject protected ResolveObservation resolveObservation;
	@Inject protected ResolvePerformanceObservationIdentifiers resolvePerformanceObservationIdentifiers;
	@Inject protected ResolvePerformanceReset resolvePerformanceReset;

	/**
	* @param instruction Specifies the reset instructions.
	* @param tradeState Specifies the trade that is resetting.
	* @return reset 
	*/
	public TradeState evaluate(ResetInstruction instruction, TradeState tradeState) {
		TradeState.TradeStateBuilder resetBuilder = doEvaluate(instruction, tradeState);
		
		final TradeState reset;
		if (resetBuilder == null) {
			reset = null;
		} else {
			reset = resetBuilder.build();
			objectValidator.validate(TradeState.class, reset);
		}
		
		return reset;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(ResetInstruction instruction, TradeState tradeState);

	protected abstract MapperS<? extends Payout> payout(ResetInstruction instruction, TradeState tradeState);

	protected abstract MapperS<Date> observationDate(ResetInstruction instruction, TradeState tradeState);

	protected abstract MapperS<? extends ObservationIdentifier> observationIdentifiers(ResetInstruction instruction, TradeState tradeState);

	protected abstract MapperS<? extends Observation> observation(ResetInstruction instruction, TradeState tradeState);

	public static class Create_ResetDefault extends Create_Reset {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(ResetInstruction instruction, TradeState tradeState) {
			TradeState.TradeStateBuilder reset = TradeState.builder();
			return assignOutput(reset, instruction, tradeState);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder reset0, ResetInstruction instruction, TradeState tradeState) {
			reset0 = toBuilder(tradeState);
			
			final List<Reset> ifThenElseResult;
			if (areEqual(MapperS.of(payout(instruction, tradeState).<PerformancePayout>mapC("getPerformancePayout", _payout -> _payout.getPerformancePayout()).resultCount()), MapperS.of(1), CardinalityOperator.All).getOrDefault(false)) {
				final Reset reset1 = resolvePerformanceReset.evaluate(payout(instruction, tradeState).<PerformancePayout>mapC("getPerformancePayout", _payout -> _payout.getPerformancePayout()).get(), observation(instruction, tradeState).get(), MapperS.of(instruction).<Date>map("getResetDate", resetInstruction -> resetInstruction.getResetDate()).get());
				ifThenElseResult = reset1 == null ? Collections.<Reset>emptyList() : Collections.singletonList(reset1);
			} else if (exists(payout(instruction, tradeState).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout())).getOrDefault(false)) {
				final Reset reset2 = resolveInterestRateReset.evaluate(payout(instruction, tradeState).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()).getMulti(), observation(instruction, tradeState).get(), MapperS.of(instruction).<Date>map("getResetDate", resetInstruction -> resetInstruction.getResetDate()).get(), MapperS.of(instruction).<Date>map("getRateRecordDate", resetInstruction -> resetInstruction.getRateRecordDate()).get());
				ifThenElseResult = reset2 == null ? Collections.<Reset>emptyList() : Collections.singletonList(reset2);
			} else {
				ifThenElseResult = Collections.<Reset>emptyList();
			}
			reset0
				.addResetHistory(ifThenElseResult);
			
			return Optional.ofNullable(reset0)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends Payout> payout(ResetInstruction instruction, TradeState tradeState) {
			return MapperS.of(instruction).<ReferenceWithMetaPayout>map("getPayout", resetInstruction -> resetInstruction.getPayout()).<Payout>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<Date> observationDate(ResetInstruction instruction, TradeState tradeState) {
			if (exists(MapperS.of(instruction).<Date>map("getRateRecordDate", resetInstruction -> resetInstruction.getRateRecordDate())).getOrDefault(false)) {
				return MapperS.of(instruction).<Date>map("getRateRecordDate", resetInstruction -> resetInstruction.getRateRecordDate());
			}
			return MapperS.of(instruction).<Date>map("getResetDate", resetInstruction -> resetInstruction.getResetDate());
		}
		
		@Override
		protected MapperS<? extends ObservationIdentifier> observationIdentifiers(ResetInstruction instruction, TradeState tradeState) {
			if (areEqual(MapperS.of(payout(instruction, tradeState).<PerformancePayout>mapC("getPerformancePayout", _payout -> _payout.getPerformancePayout()).resultCount()), MapperS.of(1), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(resolvePerformanceObservationIdentifiers.evaluate(payout(instruction, tradeState).<PerformancePayout>mapC("getPerformancePayout", _payout -> _payout.getPerformancePayout()).get(), MapperS.of(instruction).<Date>map("getResetDate", resetInstruction -> resetInstruction.getResetDate()).get()));
			}
			if (exists(payout(instruction, tradeState).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout())).getOrDefault(false)) {
				return MapperS.of(resolveInterestRateObservationIdentifiers.evaluate(payout(instruction, tradeState).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()).get(), observationDate(instruction, tradeState).get()));
			}
			return MapperS.<ObservationIdentifier>ofNull();
		}
		
		@Override
		protected MapperS<? extends Observation> observation(ResetInstruction instruction, TradeState tradeState) {
			return MapperS.of(resolveObservation.evaluate(MapperC.<ObservationIdentifier>of(observationIdentifiers(instruction, tradeState)).getMulti(), null));
		}
	}
}
