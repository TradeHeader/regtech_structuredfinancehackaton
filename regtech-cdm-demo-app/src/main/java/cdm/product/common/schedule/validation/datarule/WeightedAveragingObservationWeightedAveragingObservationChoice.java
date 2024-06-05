package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.WeightedAveragingObservation;
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
@RosettaDataRule("WeightedAveragingObservationWeightedAveragingObservationChoice")
@ImplementedBy(WeightedAveragingObservationWeightedAveragingObservationChoice.Default.class)
public interface WeightedAveragingObservationWeightedAveragingObservationChoice extends Validator<WeightedAveragingObservation> {
	
	String NAME = "WeightedAveragingObservationWeightedAveragingObservationChoice";
	String DEFINITION = "required choice dateTime, observationNumber";
	
	ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation weightedAveragingObservation);
	
	class Default implements WeightedAveragingObservationWeightedAveragingObservationChoice {
	
		@Override
		public ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation weightedAveragingObservation) {
			ComparisonResult result = executeDataRule(weightedAveragingObservation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "WeightedAveragingObservation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "WeightedAveragingObservation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(WeightedAveragingObservation weightedAveragingObservation) {
			try {
				return choice(MapperS.of(weightedAveragingObservation), Arrays.asList("dateTime", "observationNumber"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements WeightedAveragingObservationWeightedAveragingObservationChoice {
	
		@Override
		public ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation weightedAveragingObservation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "WeightedAveragingObservation", path, DEFINITION);
		}
	}
}
