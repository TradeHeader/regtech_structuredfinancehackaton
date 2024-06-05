package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.FxFixingDate;
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
@RosettaDataRule("FxFixingDateDateChoice")
@ImplementedBy(FxFixingDateDateChoice.Default.class)
public interface FxFixingDateDateChoice extends Validator<FxFixingDate> {
	
	String NAME = "FxFixingDateDateChoice";
	String DEFINITION = "required choice dateRelativeToPaymentDates, dateRelativeToCalculationPeriodDates, dateRelativeToValuationDates, fxFixingDate";
	
	ValidationResult<FxFixingDate> validate(RosettaPath path, FxFixingDate fxFixingDate);
	
	class Default implements FxFixingDateDateChoice {
	
		@Override
		public ValidationResult<FxFixingDate> validate(RosettaPath path, FxFixingDate fxFixingDate) {
			ComparisonResult result = executeDataRule(fxFixingDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxFixingDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FxFixingDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FxFixingDate fxFixingDate) {
			try {
				return choice(MapperS.of(fxFixingDate), Arrays.asList("dateRelativeToPaymentDates", "dateRelativeToCalculationPeriodDates", "dateRelativeToValuationDates", "fxFixingDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FxFixingDateDateChoice {
	
		@Override
		public ValidationResult<FxFixingDate> validate(RosettaPath path, FxFixingDate fxFixingDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxFixingDate", path, DEFINITION);
		}
	}
}
