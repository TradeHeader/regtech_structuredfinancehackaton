package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.DateRange;
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
@RosettaDataRule("DateRangeDatesOrdered")
@ImplementedBy(DateRangeDatesOrdered.Default.class)
public interface DateRangeDatesOrdered extends Validator<DateRange> {
	
	String NAME = "DateRangeDatesOrdered";
	String DEFINITION = "startDate <= endDate";
	
	ValidationResult<DateRange> validate(RosettaPath path, DateRange dateRange);
	
	class Default implements DateRangeDatesOrdered {
	
		@Override
		public ValidationResult<DateRange> validate(RosettaPath path, DateRange dateRange) {
			ComparisonResult result = executeDataRule(dateRange);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DateRange", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DateRange", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DateRange dateRange) {
			try {
				return lessThanEquals(MapperS.of(dateRange).<Date>map("getStartDate", _dateRange -> _dateRange.getStartDate()), MapperS.of(dateRange).<Date>map("getEndDate", _dateRange -> _dateRange.getEndDate()), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DateRangeDatesOrdered {
	
		@Override
		public ValidationResult<DateRange> validate(RosettaPath path, DateRange dateRange) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DateRange", path, DEFINITION);
		}
	}
}
