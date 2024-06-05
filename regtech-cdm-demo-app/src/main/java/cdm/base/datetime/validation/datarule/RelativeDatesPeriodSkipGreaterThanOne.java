package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.RelativeDates;
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
@RosettaDataRule("RelativeDatesPeriodSkipGreaterThanOne")
@ImplementedBy(RelativeDatesPeriodSkipGreaterThanOne.Default.class)
public interface RelativeDatesPeriodSkipGreaterThanOne extends Validator<RelativeDates> {
	
	String NAME = "RelativeDatesPeriodSkipGreaterThanOne";
	String DEFINITION = "if periodSkip exists then periodSkip > 1";
	
	ValidationResult<RelativeDates> validate(RosettaPath path, RelativeDates relativeDates);
	
	class Default implements RelativeDatesPeriodSkipGreaterThanOne {
	
		@Override
		public ValidationResult<RelativeDates> validate(RosettaPath path, RelativeDates relativeDates) {
			ComparisonResult result = executeDataRule(relativeDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "RelativeDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "RelativeDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(RelativeDates relativeDates) {
			try {
				if (exists(MapperS.of(relativeDates).<Integer>map("getPeriodSkip", _relativeDates -> _relativeDates.getPeriodSkip())).getOrDefault(false)) {
					return greaterThan(MapperS.of(relativeDates).<Integer>map("getPeriodSkip", _relativeDates -> _relativeDates.getPeriodSkip()), MapperS.of(1), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements RelativeDatesPeriodSkipGreaterThanOne {
	
		@Override
		public ValidationResult<RelativeDates> validate(RosettaPath path, RelativeDates relativeDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "RelativeDates", path, DEFINITION);
		}
	}
}
