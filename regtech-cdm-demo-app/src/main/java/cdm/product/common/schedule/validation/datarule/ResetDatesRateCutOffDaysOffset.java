package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.Offset;
import cdm.product.common.schedule.ResetDates;
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
@RosettaDataRule("ResetDatesRateCutOffDaysOffset")
@ImplementedBy(ResetDatesRateCutOffDaysOffset.Default.class)
public interface ResetDatesRateCutOffDaysOffset extends Validator<ResetDates> {
	
	String NAME = "ResetDatesRateCutOffDaysOffset";
	String DEFINITION = "if rateCutOffDaysOffset exists then rateCutOffDaysOffset -> periodMultiplier < 0";
	
	ValidationResult<ResetDates> validate(RosettaPath path, ResetDates resetDates);
	
	class Default implements ResetDatesRateCutOffDaysOffset {
	
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
				if (exists(MapperS.of(resetDates).<Offset>map("getRateCutOffDaysOffset", _resetDates -> _resetDates.getRateCutOffDaysOffset())).getOrDefault(false)) {
					return lessThan(MapperS.of(resetDates).<Offset>map("getRateCutOffDaysOffset", _resetDates -> _resetDates.getRateCutOffDaysOffset()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResetDatesRateCutOffDaysOffset {
	
		@Override
		public ValidationResult<ResetDates> validate(RosettaPath path, ResetDates resetDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ResetDates", path, DEFINITION);
		}
	}
}
