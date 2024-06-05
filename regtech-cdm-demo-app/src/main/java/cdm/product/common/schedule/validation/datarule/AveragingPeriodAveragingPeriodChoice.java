package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.AveragingPeriod;
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
@RosettaDataRule("AveragingPeriodAveragingPeriodChoice")
@ImplementedBy(AveragingPeriodAveragingPeriodChoice.Default.class)
public interface AveragingPeriodAveragingPeriodChoice extends Validator<AveragingPeriod> {
	
	String NAME = "AveragingPeriodAveragingPeriodChoice";
	String DEFINITION = "optional choice averagingDateTimes, averagingObservations";
	
	ValidationResult<AveragingPeriod> validate(RosettaPath path, AveragingPeriod averagingPeriod);
	
	class Default implements AveragingPeriodAveragingPeriodChoice {
	
		@Override
		public ValidationResult<AveragingPeriod> validate(RosettaPath path, AveragingPeriod averagingPeriod) {
			ComparisonResult result = executeDataRule(averagingPeriod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AveragingPeriod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AveragingPeriod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AveragingPeriod averagingPeriod) {
			try {
				return choice(MapperS.of(averagingPeriod), Arrays.asList("averagingDateTimes", "averagingObservations"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AveragingPeriodAveragingPeriodChoice {
	
		@Override
		public ValidationResult<AveragingPeriod> validate(RosettaPath path, AveragingPeriod averagingPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AveragingPeriod", path, DEFINITION);
		}
	}
}
