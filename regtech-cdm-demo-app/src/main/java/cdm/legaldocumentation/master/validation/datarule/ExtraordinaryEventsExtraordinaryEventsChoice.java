package cdm.legaldocumentation.master.validation.datarule;

import cdm.legaldocumentation.master.ExtraordinaryEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ExtraordinaryEventsExtraordinaryEventsChoice")
@ImplementedBy(ExtraordinaryEventsExtraordinaryEventsChoice.Default.class)
public interface ExtraordinaryEventsExtraordinaryEventsChoice extends Validator<ExtraordinaryEvents> {
	
	String NAME = "ExtraordinaryEventsExtraordinaryEventsChoice";
	String DEFINITION = "required choice additionalDisruptionEvents, failureToDeliver";
	
	ValidationResult<ExtraordinaryEvents> validate(RosettaPath path, ExtraordinaryEvents extraordinaryEvents);
	
	class Default implements ExtraordinaryEventsExtraordinaryEventsChoice {
	
		@Override
		public ValidationResult<ExtraordinaryEvents> validate(RosettaPath path, ExtraordinaryEvents extraordinaryEvents) {
			ComparisonResult result = executeDataRule(extraordinaryEvents);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtraordinaryEvents", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ExtraordinaryEvents", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ExtraordinaryEvents extraordinaryEvents) {
			try {
				return choice(MapperS.of(extraordinaryEvents), Arrays.asList("additionalDisruptionEvents", "failureToDeliver"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ExtraordinaryEventsExtraordinaryEventsChoice {
	
		@Override
		public ValidationResult<ExtraordinaryEvents> validate(RosettaPath path, ExtraordinaryEvents extraordinaryEvents) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtraordinaryEvents", path, DEFINITION);
		}
	}
}
