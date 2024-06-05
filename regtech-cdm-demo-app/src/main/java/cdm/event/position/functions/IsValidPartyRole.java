package cdm.event.position.functions;

import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(IsValidPartyRole.IsValidPartyRoleDefault.class)
public abstract class IsValidPartyRole implements RosettaFunction {

	/**
	* @param partyRoles 
	* @param validRoles 
	* @return isValid 
	*/
	public Boolean evaluate(List<? extends PartyRole> partyRoles, List<PartyRoleEnum> validRoles) {
		Boolean isValid = doEvaluate(partyRoles, validRoles);
		
		return isValid;
	}

	protected abstract Boolean doEvaluate(List<? extends PartyRole> partyRoles, List<PartyRoleEnum> validRoles);

	public static class IsValidPartyRoleDefault extends IsValidPartyRole {
		@Override
		protected Boolean doEvaluate(List<? extends PartyRole> partyRoles, List<PartyRoleEnum> validRoles) {
			if (partyRoles == null) {
				partyRoles = Collections.emptyList();
			}
			if (validRoles == null) {
				validRoles = Collections.emptyList();
			}
			Boolean isValid = null;
			return assignOutput(isValid, partyRoles, validRoles);
		}
		
		protected Boolean assignOutput(Boolean isValid, List<? extends PartyRole> partyRoles, List<PartyRoleEnum> validRoles) {
			final MapperC<Boolean> thenResult = MapperC.<PartyRole>of(partyRoles)
				.mapItem(item -> contains(MapperC.<PartyRoleEnum>of(validRoles), item.<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole())).asMapper());
			isValid = areEqual(thenResult, MapperS.of(true), CardinalityOperator.All).asMapper().get();
			
			return isValid;
		}
	}
}
