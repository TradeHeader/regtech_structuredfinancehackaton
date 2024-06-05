package cdm.base.staticdata.party.functions;

import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.RelatedParty;
import cdm.base.staticdata.party.RelatedParty.RelatedPartyBuilder;
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

@ImplementedBy(FilterRelatedPartyByRole.FilterRelatedPartyByRoleDefault.class)
public abstract class FilterRelatedPartyByRole implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param relatedParties 
	* @param partyRoleEnum 
	* @return filteredRelatedParties 
	*/
	public List<? extends RelatedParty> evaluate(List<? extends RelatedParty> relatedParties, PartyRoleEnum partyRoleEnum) {
		List<RelatedParty.RelatedPartyBuilder> filteredRelatedPartiesBuilder = doEvaluate(relatedParties, partyRoleEnum);
		
		final List<? extends RelatedParty> filteredRelatedParties;
		if (filteredRelatedPartiesBuilder == null) {
			filteredRelatedParties = null;
		} else {
			filteredRelatedParties = filteredRelatedPartiesBuilder.stream().map(RelatedParty::build).collect(Collectors.toList());
			objectValidator.validate(RelatedParty.class, filteredRelatedParties);
		}
		
		return filteredRelatedParties;
	}

	protected abstract List<RelatedParty.RelatedPartyBuilder> doEvaluate(List<? extends RelatedParty> relatedParties, PartyRoleEnum partyRoleEnum);

	public static class FilterRelatedPartyByRoleDefault extends FilterRelatedPartyByRole {
		@Override
		protected List<RelatedParty.RelatedPartyBuilder> doEvaluate(List<? extends RelatedParty> relatedParties, PartyRoleEnum partyRoleEnum) {
			if (relatedParties == null) {
				relatedParties = Collections.emptyList();
			}
			List<RelatedParty.RelatedPartyBuilder> filteredRelatedParties = new ArrayList<>();
			return assignOutput(filteredRelatedParties, relatedParties, partyRoleEnum);
		}
		
		protected List<RelatedParty.RelatedPartyBuilder> assignOutput(List<RelatedParty.RelatedPartyBuilder> filteredRelatedParties, List<? extends RelatedParty> relatedParties, PartyRoleEnum partyRoleEnum) {
			filteredRelatedParties.addAll(toBuilder(MapperC.<RelatedParty>of(relatedParties)
				.filterItemNullSafe(item -> areEqual(item.<PartyRoleEnum>map("getRole", relatedParty -> relatedParty.getRole()), MapperS.of(partyRoleEnum), CardinalityOperator.All).get()).getMulti()));
			
			return Optional.ofNullable(filteredRelatedParties)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
