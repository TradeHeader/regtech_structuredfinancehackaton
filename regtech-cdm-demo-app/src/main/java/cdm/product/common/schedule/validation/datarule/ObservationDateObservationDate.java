package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.ObservationDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ObservationDateObservationDate")
@ImplementedBy(ObservationDateObservationDate.Default.class)
public interface ObservationDateObservationDate extends Validator<ObservationDate> {
	
	String NAME = "ObservationDateObservationDate";
	String DEFINITION = "required choice unadjustedDate, adjustedDate";
	
	ValidationResult<ObservationDate> validate(RosettaPath path, ObservationDate observationDate);
	
	class Default implements ObservationDateObservationDate {
	
		@Override
		public ValidationResult<ObservationDate> validate(RosettaPath path, ObservationDate observationDate) {
			ComparisonResult result = executeDataRule(observationDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ObservationDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ObservationDate observationDate) {
			try {
				return choice(MapperS.of(observationDate), Arrays.asList("unadjustedDate", "adjustedDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ObservationDateObservationDate {
	
		@Override
		public ValidationResult<ObservationDate> validate(RosettaPath path, ObservationDate observationDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ObservationDate", path, DEFINITION);
		}
	}
}
