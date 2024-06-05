package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.WeightedAveragingObservation;
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
@RosettaDataRule("WeightedAveragingObservationPositiveObservationNumber")
@ImplementedBy(WeightedAveragingObservationPositiveObservationNumber.Default.class)
public interface WeightedAveragingObservationPositiveObservationNumber extends Validator<WeightedAveragingObservation> {
	
	String NAME = "WeightedAveragingObservationPositiveObservationNumber";
	String DEFINITION = "if observationNumber exists then observationNumber >= 0";
	
	ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation weightedAveragingObservation);
	
	class Default implements WeightedAveragingObservationPositiveObservationNumber {
	
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
				if (exists(MapperS.of(weightedAveragingObservation).<Integer>map("getObservationNumber", _weightedAveragingObservation -> _weightedAveragingObservation.getObservationNumber())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(weightedAveragingObservation).<Integer>map("getObservationNumber", _weightedAveragingObservation -> _weightedAveragingObservation.getObservationNumber()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements WeightedAveragingObservationPositiveObservationNumber {
	
		@Override
		public ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation weightedAveragingObservation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "WeightedAveragingObservation", path, DEFINITION);
		}
	}
}
