package cdm.product.template.validation.datarule;

import cdm.product.template.EarlyTerminationEvent;
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
@RosettaDataRule("EarlyTerminationEventFpML_ird_39")
@ImplementedBy(EarlyTerminationEventFpMLIrd39.Default.class)
public interface EarlyTerminationEventFpMLIrd39 extends Validator<EarlyTerminationEvent> {
	
	String NAME = "EarlyTerminationEventFpML_ird_39";
	String DEFINITION = "adjustedExerciseDate <= adjustedEarlyTerminationDate";
	
	ValidationResult<EarlyTerminationEvent> validate(RosettaPath path, EarlyTerminationEvent earlyTerminationEvent);
	
	class Default implements EarlyTerminationEventFpMLIrd39 {
	
		@Override
		public ValidationResult<EarlyTerminationEvent> validate(RosettaPath path, EarlyTerminationEvent earlyTerminationEvent) {
			ComparisonResult result = executeDataRule(earlyTerminationEvent);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EarlyTerminationEvent", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "EarlyTerminationEvent", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(EarlyTerminationEvent earlyTerminationEvent) {
			try {
				return lessThanEquals(MapperS.of(earlyTerminationEvent).<Date>map("getAdjustedExerciseDate", _earlyTerminationEvent -> _earlyTerminationEvent.getAdjustedExerciseDate()), MapperS.of(earlyTerminationEvent).<Date>map("getAdjustedEarlyTerminationDate", _earlyTerminationEvent -> _earlyTerminationEvent.getAdjustedEarlyTerminationDate()), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements EarlyTerminationEventFpMLIrd39 {
	
		@Override
		public ValidationResult<EarlyTerminationEvent> validate(RosettaPath path, EarlyTerminationEvent earlyTerminationEvent) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EarlyTerminationEvent", path, DEFINITION);
		}
	}
}
