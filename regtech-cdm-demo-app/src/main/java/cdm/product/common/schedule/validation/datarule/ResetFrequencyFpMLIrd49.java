package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.PeriodExtendedEnum;
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
@RosettaDataRule("ResetFrequencyFpML_ird_49")
@ImplementedBy(ResetFrequencyFpMLIrd49.Default.class)
public interface ResetFrequencyFpMLIrd49 extends Validator<ResetFrequency> {
	
	String NAME = "ResetFrequencyFpML_ird_49";
	String DEFINITION = "if weeklyRollConvention exists then period = PeriodExtendedEnum -> W";
	
	ValidationResult<ResetFrequency> validate(RosettaPath path, ResetFrequency resetFrequency);
	
	class Default implements ResetFrequencyFpMLIrd49 {
	
		@Override
		public ValidationResult<ResetFrequency> validate(RosettaPath path, ResetFrequency resetFrequency) {
			ComparisonResult result = executeDataRule(resetFrequency);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResetFrequency", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ResetFrequency", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ResetFrequency resetFrequency) {
			try {
				if (exists(MapperS.of(resetFrequency).<WeeklyRollConventionEnum>map("getWeeklyRollConvention", _resetFrequency -> _resetFrequency.getWeeklyRollConvention())).getOrDefault(false)) {
					return areEqual(MapperS.of(resetFrequency).<PeriodExtendedEnum>map("getPeriod", frequency -> frequency.getPeriod()), MapperS.of(PeriodExtendedEnum.W), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResetFrequencyFpMLIrd49 {
	
		@Override
		public ValidationResult<ResetFrequency> validate(RosettaPath path, ResetFrequency resetFrequency) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResetFrequency", path, DEFINITION);
		}
	}
}
