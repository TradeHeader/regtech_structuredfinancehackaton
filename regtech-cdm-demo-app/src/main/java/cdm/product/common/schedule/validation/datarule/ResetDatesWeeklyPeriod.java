package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.PeriodExtendedEnum;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.ResetFrequency;
import cdm.product.common.schedule.WeeklyRollConventionEnum;
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
@RosettaDataRule("ResetDatesWeeklyPeriod")
@ImplementedBy(ResetDatesWeeklyPeriod.Default.class)
public interface ResetDatesWeeklyPeriod extends Validator<ResetDates> {
	
	String NAME = "ResetDatesWeeklyPeriod";
	String DEFINITION = "if resetFrequency -> period = PeriodExtendedEnum -> W then resetFrequency -> weeklyRollConvention exists";
	
	ValidationResult<ResetDates> validate(RosettaPath path, ResetDates resetDates);
	
	class Default implements ResetDatesWeeklyPeriod {
	
		@Override
		public ValidationResult<ResetDates> validate(RosettaPath path, ResetDates resetDates) {
			ComparisonResult result = executeDataRule(resetDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResetDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ResetDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ResetDates resetDates) {
			try {
				if (areEqual(MapperS.of(resetDates).<ResetFrequency>map("getResetFrequency", _resetDates -> _resetDates.getResetFrequency()).<PeriodExtendedEnum>map("getPeriod", frequency -> frequency.getPeriod()), MapperS.of(PeriodExtendedEnum.W), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(resetDates).<ResetFrequency>map("getResetFrequency", _resetDates -> _resetDates.getResetFrequency()).<WeeklyRollConventionEnum>map("getWeeklyRollConvention", resetFrequency -> resetFrequency.getWeeklyRollConvention()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResetDatesWeeklyPeriod {
	
		@Override
		public ValidationResult<ResetDates> validate(RosettaPath path, ResetDates resetDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResetDates", path, DEFINITION);
		}
	}
}
