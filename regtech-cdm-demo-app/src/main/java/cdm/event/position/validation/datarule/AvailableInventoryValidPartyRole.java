package cdm.event.position.validation.datarule;

import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.event.position.AvailableInventory;
import cdm.event.position.functions.IsValidPartyRole;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import javax.inject.Inject;


/**
 * @version ${project.version}
 */
@RosettaDataRule("AvailableInventoryValidPartyRole")
@ImplementedBy(AvailableInventoryValidPartyRole.Default.class)
public interface AvailableInventoryValidPartyRole extends Validator<AvailableInventory> {
	
	String NAME = "AvailableInventoryValidPartyRole";
	String DEFINITION = "IsValidPartyRole( partyRole, [PartyRoleEnum -> AgentLender, PartyRoleEnum -> BeneficialOwner, PartyRoleEnum -> Borrower, PartyRoleEnum -> Custodian, PartyRoleEnum -> Lender] )";
	
	ValidationResult<AvailableInventory> validate(RosettaPath path, AvailableInventory availableInventory);
	
	class Default implements AvailableInventoryValidPartyRole {
	
		@Inject protected IsValidPartyRole isValidPartyRole;
		
		@Override
		public ValidationResult<AvailableInventory> validate(RosettaPath path, AvailableInventory availableInventory) {
			ComparisonResult result = executeDataRule(availableInventory);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AvailableInventory", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AvailableInventory", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AvailableInventory availableInventory) {
			try {
				return ComparisonResult.of(MapperS.of(isValidPartyRole.evaluate(MapperS.of(availableInventory).<PartyRole>mapC("getPartyRole", _availableInventory -> _availableInventory.getPartyRole()).getMulti(), MapperC.<PartyRoleEnum>of(MapperS.of(PartyRoleEnum.AGENT_LENDER), MapperS.of(PartyRoleEnum.BENEFICIAL_OWNER), MapperS.of(PartyRoleEnum.BORROWER), MapperS.of(PartyRoleEnum.CUSTODIAN), MapperS.of(PartyRoleEnum.LENDER)).getMulti())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AvailableInventoryValidPartyRole {
	
		@Override
		public ValidationResult<AvailableInventory> validate(RosettaPath path, AvailableInventory availableInventory) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AvailableInventory", path, DEFINITION);
		}
	}
}
