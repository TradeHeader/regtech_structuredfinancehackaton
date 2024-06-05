package cdm.base.math.validation.datarule;

import cdm.base.math.DatedValue;
import cdm.base.math.MeasureSchedule;
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
@RosettaDataRule("MeasureScheduleValueExists")
@ImplementedBy(MeasureScheduleValueExists.Default.class)
public interface MeasureScheduleValueExists extends Validator<MeasureSchedule> {
	
	String NAME = "MeasureScheduleValueExists";
	String DEFINITION = "value exists or datedValue exists";
	
	ValidationResult<MeasureSchedule> validate(RosettaPath path, MeasureSchedule measureSchedule);
	
	class Default implements MeasureScheduleValueExists {
	
		@Override
		public ValidationResult<MeasureSchedule> validate(RosettaPath path, MeasureSchedule measureSchedule) {
			ComparisonResult result = executeDataRule(measureSchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MeasureSchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MeasureSchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MeasureSchedule measureSchedule) {
			try {
				return exists(MapperS.of(measureSchedule).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).or(exists(MapperS.of(measureSchedule).<DatedValue>mapC("getDatedValue", _measureSchedule -> _measureSchedule.getDatedValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MeasureScheduleValueExists {
	
		@Override
		public ValidationResult<MeasureSchedule> validate(RosettaPath path, MeasureSchedule measureSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MeasureSchedule", path, DEFINITION);
		}
	}
}
