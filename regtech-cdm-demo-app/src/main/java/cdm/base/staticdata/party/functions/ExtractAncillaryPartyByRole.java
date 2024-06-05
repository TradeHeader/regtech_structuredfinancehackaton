package cdm.base.staticdata.party.functions;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.AncillaryParty.AncillaryPartyBuilder;
import cdm.base.staticdata.party.AncillaryRoleEnum;
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

@ImplementedBy(ExtractAncillaryPartyByRole.ExtractAncillaryPartyByRoleDefault.class)
public abstract class ExtractAncillaryPartyByRole implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param ancillaryParties The list of ancillary parties to filter.
	* @param roleEnumToExtract The ancillary role enum to filter by.
	* @return ancillaryParty The ancillary party with specified ancillary role.
	*/
	public AncillaryParty evaluate(List<? extends AncillaryParty> ancillaryParties, AncillaryRoleEnum roleEnumToExtract) {
		AncillaryParty.AncillaryPartyBuilder ancillaryPartyBuilder = doEvaluate(ancillaryParties, roleEnumToExtract);
		
		final AncillaryParty ancillaryParty;
		if (ancillaryPartyBuilder == null) {
			ancillaryParty = null;
		} else {
			ancillaryParty = ancillaryPartyBuilder.build();
			objectValidator.validate(AncillaryParty.class, ancillaryParty);
		}
		
		return ancillaryParty;
	}

	protected abstract AncillaryParty.AncillaryPartyBuilder doEvaluate(List<? extends AncillaryParty> ancillaryParties, AncillaryRoleEnum roleEnumToExtract);

	public static class ExtractAncillaryPartyByRoleDefault extends ExtractAncillaryPartyByRole {
		@Override
		protected AncillaryParty.AncillaryPartyBuilder doEvaluate(List<? extends AncillaryParty> ancillaryParties, AncillaryRoleEnum roleEnumToExtract) {
			if (ancillaryParties == null) {
				ancillaryParties = Collections.emptyList();
			}
			AncillaryParty.AncillaryPartyBuilder ancillaryParty = AncillaryParty.builder();
			return assignOutput(ancillaryParty, ancillaryParties, roleEnumToExtract);
		}
		
		protected AncillaryParty.AncillaryPartyBuilder assignOutput(AncillaryParty.AncillaryPartyBuilder ancillaryParty, List<? extends AncillaryParty> ancillaryParties, AncillaryRoleEnum roleEnumToExtract) {
			final MapperC<AncillaryParty> thenResult = MapperC.<AncillaryParty>of(ancillaryParties)
				.filterItemNullSafe(item -> areEqual(item.<AncillaryRoleEnum>map("getRole", _ancillaryParty -> _ancillaryParty.getRole()), MapperS.of(roleEnumToExtract), CardinalityOperator.All).get());
			ancillaryParty = toBuilder(MapperS.of(thenResult.get()).get());
			
			return Optional.ofNullable(ancillaryParty)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
