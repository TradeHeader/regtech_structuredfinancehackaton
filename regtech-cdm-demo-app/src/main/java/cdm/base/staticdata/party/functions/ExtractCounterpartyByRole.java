package cdm.base.staticdata.party.functions;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Counterparty.CounterpartyBuilder;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ExtractCounterpartyByRole.ExtractCounterpartyByRoleDefault.class)
public abstract class ExtractCounterpartyByRole implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param counterparties The list of counterparties to filter.
	* @param roleEnumToExtract The counterparty role enum to filter by.
	* @return counterparty The counterparty with specified counterparty role.
	*/
	public Counterparty evaluate(List<? extends Counterparty> counterparties, CounterpartyRoleEnum roleEnumToExtract) {
		Counterparty.CounterpartyBuilder counterpartyBuilder = doEvaluate(counterparties, roleEnumToExtract);
		
		final Counterparty counterparty;
		if (counterpartyBuilder == null) {
			counterparty = null;
		} else {
			counterparty = counterpartyBuilder.build();
			objectValidator.validate(Counterparty.class, counterparty);
		}
		
		return counterparty;
	}

	protected abstract Counterparty.CounterpartyBuilder doEvaluate(List<? extends Counterparty> counterparties, CounterpartyRoleEnum roleEnumToExtract);

	public static class ExtractCounterpartyByRoleDefault extends ExtractCounterpartyByRole {
		@Override
		protected Counterparty.CounterpartyBuilder doEvaluate(List<? extends Counterparty> counterparties, CounterpartyRoleEnum roleEnumToExtract) {
			if (counterparties == null) {
				counterparties = Collections.emptyList();
			}
			Counterparty.CounterpartyBuilder counterparty = Counterparty.builder();
			return assignOutput(counterparty, counterparties, roleEnumToExtract);
		}
		
		protected Counterparty.CounterpartyBuilder assignOutput(Counterparty.CounterpartyBuilder counterparty, List<? extends Counterparty> counterparties, CounterpartyRoleEnum roleEnumToExtract) {
			final MapperC<Counterparty> thenResult = MapperC.<Counterparty>of(counterparties)
				.filterItemNullSafe(item -> areEqual(item.<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()), MapperS.of(roleEnumToExtract), CardinalityOperator.All).get());
			counterparty = toBuilder(MapperS.of(thenResult.get()).get());
			
			return Optional.ofNullable(counterparty)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
