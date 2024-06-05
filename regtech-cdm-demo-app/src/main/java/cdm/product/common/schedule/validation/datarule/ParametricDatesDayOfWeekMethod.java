package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.DayOfWeekEnum;
import cdm.product.common.schedule.ParametricDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("ParametricDatesDayOfWeekMethod")
@ImplementedBy(ParametricDatesDayOfWeekMethod.Default.class)
public interface ParametricDatesDayOfWeekMethod extends Validator<ParametricDates> {
	
	String NAME = "ParametricDatesDayOfWeekMethod";
	String DEFINITION = "if dayOfWeek exists then dayFrequency exists";
	
	ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates);
	
	class Default implements ParametricDatesDayOfWeekMethod {
	
		@Override
		public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates) {
			ComparisonResult result = executeDataRule(parametricDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ParametricDates parametricDates) {
			try {
				if (exists(MapperS.of(parametricDates).<DayOfWeekEnum>mapC("getDayOfWeek", _parametricDates -> _parametricDates.getDayOfWeek())).getOrDefault(false)) {
					return exists(MapperS.of(parametricDates).<BigDecimal>map("getDayFrequency", _parametricDates -> _parametricDates.getDayFrequency()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ParametricDatesDayOfWeekMethod {
	
		@Override
		public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates parametricDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ParametricDates", path, DEFINITION);
		}
	}
}
