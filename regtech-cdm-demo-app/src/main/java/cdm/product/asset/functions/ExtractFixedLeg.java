package cdm.product.asset.functions;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.InterestRatePayout.InterestRatePayoutBuilder;
import cdm.product.asset.RateSpecification;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ExtractFixedLeg.ExtractFixedLegDefault.class)
public abstract class ExtractFixedLeg implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param interestRatePayouts 
	* @return fixedRatePayout 
	*/
	public InterestRatePayout evaluate(List<? extends InterestRatePayout> interestRatePayouts) {
		InterestRatePayout.InterestRatePayoutBuilder fixedRatePayoutBuilder = doEvaluate(interestRatePayouts);
		
		final InterestRatePayout fixedRatePayout;
		if (fixedRatePayoutBuilder == null) {
			fixedRatePayout = null;
		} else {
			fixedRatePayout = fixedRatePayoutBuilder.build();
			objectValidator.validate(InterestRatePayout.class, fixedRatePayout);
		}
		
		return fixedRatePayout;
	}

	protected abstract InterestRatePayout.InterestRatePayoutBuilder doEvaluate(List<? extends InterestRatePayout> interestRatePayouts);

	public static class ExtractFixedLegDefault extends ExtractFixedLeg {
		@Override
		protected InterestRatePayout.InterestRatePayoutBuilder doEvaluate(List<? extends InterestRatePayout> interestRatePayouts) {
			if (interestRatePayouts == null) {
				interestRatePayouts = Collections.emptyList();
			}
			InterestRatePayout.InterestRatePayoutBuilder fixedRatePayout = InterestRatePayout.builder();
			return assignOutput(fixedRatePayout, interestRatePayouts);
		}
		
		protected InterestRatePayout.InterestRatePayoutBuilder assignOutput(InterestRatePayout.InterestRatePayoutBuilder fixedRatePayout, List<? extends InterestRatePayout> interestRatePayouts) {
			final MapperC<InterestRatePayout> thenResult = MapperC.<InterestRatePayout>of(interestRatePayouts)
				.filterItemNullSafe(item -> exists(item.<RateSpecification>map("getRateSpecification", interestRatePayout -> interestRatePayout.getRateSpecification()).<FixedRateSpecification>map("getFixedRate", rateSpecification -> rateSpecification.getFixedRate())).get());
			fixedRatePayout = toBuilder(MapperS.of(thenResult.get()).get());
			
			return Optional.ofNullable(fixedRatePayout)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
