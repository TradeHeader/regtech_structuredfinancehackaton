package cdm.product.template.validation.datarule;

import cdm.product.template.ExtensionEvent;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ExtensionEventFpML_ird_42")
@ImplementedBy(ExtensionEventFpMLIrd42.Default.class)
public interface ExtensionEventFpMLIrd42 extends Validator<ExtensionEvent> {
	
	String NAME = "ExtensionEventFpML_ird_42";
	String DEFINITION = "adjustedExerciseDate < adjustedExtendedTerminationDate";
	
	ValidationResult<ExtensionEvent> validate(RosettaPath path, ExtensionEvent extensionEvent);
	
	class Default implements ExtensionEventFpMLIrd42 {
	
		@Override
		public ValidationResult<ExtensionEvent> validate(RosettaPath path, ExtensionEvent extensionEvent) {
			ComparisonResult result = executeDataRule(extensionEvent);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtensionEvent", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ExtensionEvent", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ExtensionEvent extensionEvent) {
			try {
				return lessThan(MapperS.of(extensionEvent).<Date>map("getAdjustedExerciseDate", _extensionEvent -> _extensionEvent.getAdjustedExerciseDate()), MapperS.of(extensionEvent).<Date>map("getAdjustedExtendedTerminationDate", _extensionEvent -> _extensionEvent.getAdjustedExtendedTerminationDate()), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ExtensionEventFpMLIrd42 {
	
		@Override
		public ValidationResult<ExtensionEvent> validate(RosettaPath path, ExtensionEvent extensionEvent) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExtensionEvent", path, DEFINITION);
		}
	}
}
