package cdm.base.math.validation.datarule;

import cdm.base.math.NumberBound;
import cdm.base.math.NumberRange;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("NumberRangeAtLeastOneOf")
@ImplementedBy(NumberRangeAtLeastOneOf.Default.class)
public interface NumberRangeAtLeastOneOf extends Validator<NumberRange> {
	
	String NAME = "NumberRangeAtLeastOneOf";
	String DEFINITION = "lowerBound exists or upperBound exists";
	
	ValidationResult<NumberRange> validate(RosettaPath path, NumberRange numberRange);
	
	class Default implements NumberRangeAtLeastOneOf {
	
		@Override
		public ValidationResult<NumberRange> validate(RosettaPath path, NumberRange numberRange) {
			ComparisonResult result = executeDataRule(numberRange);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NumberRange", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NumberRange", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NumberRange numberRange) {
			try {
				return exists(MapperS.of(numberRange).<NumberBound>map("getLowerBound", _numberRange -> _numberRange.getLowerBound())).or(exists(MapperS.of(numberRange).<NumberBound>map("getUpperBound", _numberRange -> _numberRange.getUpperBound())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NumberRangeAtLeastOneOf {
	
		@Override
		public ValidationResult<NumberRange> validate(RosettaPath path, NumberRange numberRange) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NumberRange", path, DEFINITION);
		}
	}
}
