package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodEnum;
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
@RosettaDataRule("PeriodDayPeriod")
@ImplementedBy(PeriodDayPeriod.Default.class)
public interface PeriodDayPeriod extends Validator<Period> {
	
	String NAME = "PeriodDayPeriod";
	String DEFINITION = "if periodMultiplier = 0 then period = PeriodEnum -> D";
	
	ValidationResult<Period> validate(RosettaPath path, Period period);
	
	class Default implements PeriodDayPeriod {
	
		@Override
		public ValidationResult<Period> validate(RosettaPath path, Period period) {
			ComparisonResult result = executeDataRule(period);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Period", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Period", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Period period) {
			try {
				if (areEqual(MapperS.of(period).<Integer>map("getPeriodMultiplier", _period -> _period.getPeriodMultiplier()), MapperS.of(0), CardinalityOperator.All).getOrDefault(false)) {
					return areEqual(MapperS.of(period).<PeriodEnum>map("getPeriod", _period -> _period.getPeriod()), MapperS.of(PeriodEnum.D), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PeriodDayPeriod {
	
		@Override
		public ValidationResult<Period> validate(RosettaPath path, Period period) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Period", path, DEFINITION);
		}
	}
}
