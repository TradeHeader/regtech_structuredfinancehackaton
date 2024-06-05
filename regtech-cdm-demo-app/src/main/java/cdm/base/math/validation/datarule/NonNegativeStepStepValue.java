package cdm.base.math.validation.datarule;

import cdm.base.math.NonNegativeStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("NonNegativeStepStepValue")
@ImplementedBy(NonNegativeStepStepValue.Default.class)
public interface NonNegativeStepStepValue extends Validator<NonNegativeStep> {
	
	String NAME = "NonNegativeStepStepValue";
	String DEFINITION = "stepValue >= 0.0";
	
	ValidationResult<NonNegativeStep> validate(RosettaPath path, NonNegativeStep nonNegativeStep);
	
	class Default implements NonNegativeStepStepValue {
	
		@Override
		public ValidationResult<NonNegativeStep> validate(RosettaPath path, NonNegativeStep nonNegativeStep) {
			ComparisonResult result = executeDataRule(nonNegativeStep);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeStep", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NonNegativeStep", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NonNegativeStep nonNegativeStep) {
			try {
				return greaterThanEquals(MapperS.of(nonNegativeStep).<BigDecimal>map("getStepValue", _nonNegativeStep -> _nonNegativeStep.getStepValue()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NonNegativeStepStepValue {
	
		@Override
		public ValidationResult<NonNegativeStep> validate(RosettaPath path, NonNegativeStep nonNegativeStep) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NonNegativeStep", path, DEFINITION);
		}
	}
}
