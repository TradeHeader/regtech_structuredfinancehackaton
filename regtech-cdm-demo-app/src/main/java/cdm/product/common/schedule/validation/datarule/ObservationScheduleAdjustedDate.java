package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.BusinessDayAdjustments;
import cdm.product.common.schedule.ObservationDate;
import cdm.product.common.schedule.ObservationSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("ObservationScheduleAdjustedDate")
@ImplementedBy(ObservationScheduleAdjustedDate.Default.class)
public interface ObservationScheduleAdjustedDate extends Validator<ObservationSchedule> {
	
	String NAME = "ObservationScheduleAdjustedDate";
	String DEFINITION = "if observationDate -> adjustedDate is absent then observationDate -> unadjustedDate exists and dateAdjustments exists";
	
	ValidationResult<ObservationSchedule> validate(RosettaPath path, ObservationSchedule observationSchedule);
	
	class Default implements ObservationScheduleAdjustedDate {
	
		@Override
		public ValidationResult<ObservationSchedule> validate(RosettaPath path, ObservationSchedule observationSchedule) {
			ComparisonResult result = executeDataRule(observationSchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationSchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ObservationSchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ObservationSchedule observationSchedule) {
			try {
				if (notExists(MapperS.of(observationSchedule).<ObservationDate>mapC("getObservationDate", _observationSchedule -> _observationSchedule.getObservationDate()).<Date>map("getAdjustedDate", observationDate -> observationDate.getAdjustedDate())).getOrDefault(false)) {
					return exists(MapperS.of(observationSchedule).<ObservationDate>mapC("getObservationDate", _observationSchedule -> _observationSchedule.getObservationDate()).<Date>map("getUnadjustedDate", observationDate -> observationDate.getUnadjustedDate())).and(exists(MapperS.of(observationSchedule).<BusinessDayAdjustments>map("getDateAdjustments", _observationSchedule -> _observationSchedule.getDateAdjustments())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ObservationScheduleAdjustedDate {
	
		@Override
		public ValidationResult<ObservationSchedule> validate(RosettaPath path, ObservationSchedule observationSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationSchedule", path, DEFINITION);
		}
	}
}
