package cdm.event.position.functions;

import cdm.event.position.Portfolio;
import cdm.event.position.PortfolioState;
import cdm.event.position.PortfolioState.PortfolioStateBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(EvaluatePortfolioState.EvaluatePortfolioStateDefault.class)
public abstract class EvaluatePortfolioState implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param portfolio Portfolio containing the aggregation parameters to be used to calculate the new portfolio state.
	* @return portfolioState Portfolio state containing the aggregated positions based on the input aggregation parameters.
	*/
	public PortfolioState evaluate(Portfolio portfolio) {
		PortfolioState.PortfolioStateBuilder portfolioStateBuilder = doEvaluate(portfolio);
		
		final PortfolioState portfolioState;
		if (portfolioStateBuilder == null) {
			portfolioState = null;
		} else {
			portfolioState = portfolioStateBuilder.build();
			objectValidator.validate(PortfolioState.class, portfolioState);
		}
		
		return portfolioState;
	}

	protected abstract PortfolioState.PortfolioStateBuilder doEvaluate(Portfolio portfolio);

	public static class EvaluatePortfolioStateDefault extends EvaluatePortfolioState {
		@Override
		protected PortfolioState.PortfolioStateBuilder doEvaluate(Portfolio portfolio) {
			PortfolioState.PortfolioStateBuilder portfolioState = PortfolioState.builder();
			return assignOutput(portfolioState, portfolio);
		}
		
		protected PortfolioState.PortfolioStateBuilder assignOutput(PortfolioState.PortfolioStateBuilder portfolioState, Portfolio portfolio) {
			return Optional.ofNullable(portfolioState)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
