package cdm.base.staticdata.party.functions;

import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRole.PartyRoleBuilder;
import cdm.base.staticdata.party.PartyRoleEnum;
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

@ImplementedBy(FilterPartyRole.FilterPartyRoleDefault.class)
public abstract class FilterPartyRole implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param partyRoles 
	* @param partyRoleEnum 
	* @return filteredPartyRoles 
	*/
	public List<? extends PartyRole> evaluate(List<? extends PartyRole> partyRoles, PartyRoleEnum partyRoleEnum) {
		List<PartyRole.PartyRoleBuilder> filteredPartyRolesBuilder = doEvaluate(partyRoles, partyRoleEnum);
		
		final List<? extends PartyRole> filteredPartyRoles;
		if (filteredPartyRolesBuilder == null) {
			filteredPartyRoles = null;
		} else {
			filteredPartyRoles = filteredPartyRolesBuilder.stream().map(PartyRole::build).collect(Collectors.toList());
			objectValidator.validate(PartyRole.class, filteredPartyRoles);
		}
		
		return filteredPartyRoles;
	}

	protected abstract List<PartyRole.PartyRoleBuilder> doEvaluate(List<? extends PartyRole> partyRoles, PartyRoleEnum partyRoleEnum);

	public static class FilterPartyRoleDefault extends FilterPartyRole {
		@Override
		protected List<PartyRole.PartyRoleBuilder> doEvaluate(List<? extends PartyRole> partyRoles, PartyRoleEnum partyRoleEnum) {
			if (partyRoles == null) {
				partyRoles = Collections.emptyList();
			}
			List<PartyRole.PartyRoleBuilder> filteredPartyRoles = new ArrayList<>();
			return assignOutput(filteredPartyRoles, partyRoles, partyRoleEnum);
		}
		
		protected List<PartyRole.PartyRoleBuilder> assignOutput(List<PartyRole.PartyRoleBuilder> filteredPartyRoles, List<? extends PartyRole> partyRoles, PartyRoleEnum partyRoleEnum) {
			filteredPartyRoles.addAll(toBuilder(MapperC.<PartyRole>of(partyRoles)
				.filterItemNullSafe(item -> areEqual(item.<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(partyRoleEnum), CardinalityOperator.All).get()).getMulti()));
			
			return Optional.ofNullable(filteredPartyRoles)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
