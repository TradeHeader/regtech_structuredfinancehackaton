package cdm.event.common.functions;

import cdm.event.common.Reset;
import cdm.event.common.Reset.ResetBuilder;
import cdm.observable.asset.Price;
import cdm.observable.event.Observation;
import cdm.product.asset.InterestRatePayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ResolveInterestRateReset.ResolveInterestRateResetDefault.class)
public abstract class ResolveInterestRateReset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param payouts 
	* @param observation 
	* @param resetDate 
	* @param rateRecordDate 
	* @return reset 
	*/
	public Reset evaluate(List<? extends InterestRatePayout> payouts, Observation observation, Date resetDate, Date rateRecordDate) {
		Reset.ResetBuilder resetBuilder = doEvaluate(payouts, observation, resetDate, rateRecordDate);
		
		final Reset reset;
		if (resetBuilder == null) {
			reset = null;
		} else {
			reset = resetBuilder.build();
			objectValidator.validate(Reset.class, reset);
		}
		
		return reset;
	}

	protected abstract Reset.ResetBuilder doEvaluate(List<? extends InterestRatePayout> payouts, Observation observation, Date resetDate, Date rateRecordDate);

	public static class ResolveInterestRateResetDefault extends ResolveInterestRateReset {
		@Override
		protected Reset.ResetBuilder doEvaluate(List<? extends InterestRatePayout> payouts, Observation observation, Date resetDate, Date rateRecordDate) {
			if (payouts == null) {
				payouts = Collections.emptyList();
			}
			Reset.ResetBuilder reset = Reset.builder();
			return assignOutput(reset, payouts, observation, resetDate, rateRecordDate);
		}
		
		protected Reset.ResetBuilder assignOutput(Reset.ResetBuilder reset, List<? extends InterestRatePayout> payouts, Observation observation, Date resetDate, Date rateRecordDate) {
			reset
				.setResetValue(MapperS.of(observation).<Price>map("getObservedValue", _observation -> _observation.getObservedValue()).get());
			
			reset
				.setResetDate(resetDate);
			
			reset
				.setRateRecordDate(rateRecordDate);
			
			reset
				.addObservationsValue((observation == null ? Collections.<Observation>emptyList() : Collections.singletonList(observation)));
			
			return Optional.ofNullable(reset)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
