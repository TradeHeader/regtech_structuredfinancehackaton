package cdm.legaldocumentation.master.validation.datarule;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
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
@RosettaDataRule("AdditionalDisruptionEventsDisruptionEventsDeterminingParty")
@ImplementedBy(AdditionalDisruptionEventsDisruptionEventsDeterminingParty.Default.class)
public interface AdditionalDisruptionEventsDisruptionEventsDeterminingParty extends Validator<AdditionalDisruptionEvents> {
	
	String NAME = "AdditionalDisruptionEventsDisruptionEventsDeterminingParty";
	String DEFINITION = "if determiningParty exists then determiningParty = AncillaryRoleEnum -> DisruptionEventsDeterminingParty";
	
	ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents);
	
	class Default implements AdditionalDisruptionEventsDisruptionEventsDeterminingParty {
	
		@Override
		public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents) {
			ComparisonResult result = executeDataRule(additionalDisruptionEvents);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdditionalDisruptionEvents additionalDisruptionEvents) {
			try {
				if (exists(MapperS.of(additionalDisruptionEvents).<AncillaryRoleEnum>map("getDeterminingParty", _additionalDisruptionEvents -> _additionalDisruptionEvents.getDeterminingParty())).getOrDefault(false)) {
					return areEqual(MapperS.of(additionalDisruptionEvents).<AncillaryRoleEnum>map("getDeterminingParty", _additionalDisruptionEvents -> _additionalDisruptionEvents.getDeterminingParty()), MapperS.of(AncillaryRoleEnum.DISRUPTION_EVENTS_DETERMINING_PARTY), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdditionalDisruptionEventsDisruptionEventsDeterminingParty {
	
		@Override
		public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION);
		}
	}
}
