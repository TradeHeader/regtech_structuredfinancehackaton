package cdm.base.staticdata.party.functions;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.Party.PartyBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ReplaceParty.ReplacePartyDefault.class)
public abstract class ReplaceParty implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param parties Specifies the list of parties to update.
	* @param oldParty Specifies the party to be removed.
	* @param newParty Specifies the party to be added.
	* @return updatedParties The updated list of parties.
	*/
	public List<? extends Party> evaluate(List<? extends Party> parties, Party oldParty, Party newParty) {
		List<Party.PartyBuilder> updatedPartiesBuilder = doEvaluate(parties, oldParty, newParty);
		
		final List<? extends Party> updatedParties;
		if (updatedPartiesBuilder == null) {
			updatedParties = null;
		} else {
			updatedParties = updatedPartiesBuilder.stream().map(Party::build).collect(Collectors.toList());
			objectValidator.validate(Party.class, updatedParties);
		}
		
		return updatedParties;
	}

	protected abstract List<Party.PartyBuilder> doEvaluate(List<? extends Party> parties, Party oldParty, Party newParty);

	public static class ReplacePartyDefault extends ReplaceParty {
		@Override
		protected List<Party.PartyBuilder> doEvaluate(List<? extends Party> parties, Party oldParty, Party newParty) {
			if (parties == null) {
				parties = Collections.emptyList();
			}
			List<Party.PartyBuilder> updatedParties = new ArrayList<>();
			return assignOutput(updatedParties, parties, oldParty, newParty);
		}
		
		protected List<Party.PartyBuilder> assignOutput(List<Party.PartyBuilder> updatedParties, List<? extends Party> parties, Party oldParty, Party newParty) {
			final MapperC<Party> thenResult = MapperC.<Party>of(parties)
				.mapItem(item -> {
					if (areEqual(item, MapperS.of(oldParty), CardinalityOperator.All).getOrDefault(false)) {
						return MapperS.of(newParty);
					}
					return item;
				});
			updatedParties.addAll(toBuilder(distinct(thenResult).getMulti()));
			
			return Optional.ofNullable(updatedParties)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
