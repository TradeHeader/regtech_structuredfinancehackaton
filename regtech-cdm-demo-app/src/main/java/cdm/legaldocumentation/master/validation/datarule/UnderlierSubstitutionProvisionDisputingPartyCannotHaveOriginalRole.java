package cdm.legaldocumentation.master.validation.datarule;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole")
@ImplementedBy(UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole.Default.class)
public interface UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole extends Validator<UnderlierSubstitutionProvision> {
	
	String NAME = "UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole";
	String DEFINITION = "whoMaySubstitute all <> disputingParty";
	
	ValidationResult<UnderlierSubstitutionProvision> validate(RosettaPath path, UnderlierSubstitutionProvision underlierSubstitutionProvision);
	
	class Default implements UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole {
	
		@Override
		public ValidationResult<UnderlierSubstitutionProvision> validate(RosettaPath path, UnderlierSubstitutionProvision underlierSubstitutionProvision) {
			ComparisonResult result = executeDataRule(underlierSubstitutionProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "UnderlierSubstitutionProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "UnderlierSubstitutionProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(UnderlierSubstitutionProvision underlierSubstitutionProvision) {
			try {
				return notEqual(MapperS.of(underlierSubstitutionProvision).<CounterpartyRoleEnum>mapC("getWhoMaySubstitute", _underlierSubstitutionProvision -> _underlierSubstitutionProvision.getWhoMaySubstitute()), MapperS.of(underlierSubstitutionProvision).<CounterpartyRoleEnum>map("getDisputingParty", _underlierSubstitutionProvision -> _underlierSubstitutionProvision.getDisputingParty()), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements UnderlierSubstitutionProvisionDisputingPartyCannotHaveOriginalRole {
	
		@Override
		public ValidationResult<UnderlierSubstitutionProvision> validate(RosettaPath path, UnderlierSubstitutionProvision underlierSubstitutionProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "UnderlierSubstitutionProvision", path, DEFINITION);
		}
	}
}
