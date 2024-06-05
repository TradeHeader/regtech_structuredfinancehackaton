package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.PeriodRange;
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
@RosettaDataRule("PeriodRangeAtLeastOneOf")
@ImplementedBy(PeriodRangeAtLeastOneOf.Default.class)
public interface PeriodRangeAtLeastOneOf extends Validator<PeriodRange> {
	
	String NAME = "PeriodRangeAtLeastOneOf";
	String DEFINITION = "lowerBound exists or upperBound exists";
	
	ValidationResult<PeriodRange> validate(RosettaPath path, PeriodRange periodRange);
	
	class Default implements PeriodRangeAtLeastOneOf {
	
		@Override
		public ValidationResult<PeriodRange> validate(RosettaPath path, PeriodRange periodRange) {
			ComparisonResult result = executeDataRule(periodRange);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PeriodRange", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PeriodRange", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PeriodRange periodRange) {
			try {
				return exists(MapperS.of(periodRange).<PeriodBound>map("getLowerBound", _periodRange -> _periodRange.getLowerBound())).or(exists(MapperS.of(periodRange).<PeriodBound>map("getUpperBound", _periodRange -> _periodRange.getUpperBound())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PeriodRangeAtLeastOneOf {
	
		@Override
		public ValidationResult<PeriodRange> validate(RosettaPath path, PeriodRange periodRange) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PeriodRange", path, DEFINITION);
		}
	}
}
