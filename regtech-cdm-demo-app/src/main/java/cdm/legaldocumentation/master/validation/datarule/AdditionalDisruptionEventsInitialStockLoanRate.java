package cdm.legaldocumentation.master.validation.datarule;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("AdditionalDisruptionEventsInitialStockLoanRate")
@ImplementedBy(AdditionalDisruptionEventsInitialStockLoanRate.Default.class)
public interface AdditionalDisruptionEventsInitialStockLoanRate extends Validator<AdditionalDisruptionEvents> {
	
	String NAME = "AdditionalDisruptionEventsInitialStockLoanRate";
	String DEFINITION = "if initialStockLoanRate exists then initialStockLoanRate >= 0 and initialStockLoanRate <= 1";
	
	ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents);
	
	class Default implements AdditionalDisruptionEventsInitialStockLoanRate {
	
		@Override
		public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents) {
			ComparisonResult result = executeDataRule(additionalDisruptionEvents);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdditionalDisruptionEvents additionalDisruptionEvents) {
			try {
				if (exists(MapperS.of(additionalDisruptionEvents).<BigDecimal>map("getInitialStockLoanRate", _additionalDisruptionEvents -> _additionalDisruptionEvents.getInitialStockLoanRate())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(additionalDisruptionEvents).<BigDecimal>map("getInitialStockLoanRate", _additionalDisruptionEvents -> _additionalDisruptionEvents.getInitialStockLoanRate()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).and(lessThanEquals(MapperS.of(additionalDisruptionEvents).<BigDecimal>map("getInitialStockLoanRate", _additionalDisruptionEvents -> _additionalDisruptionEvents.getInitialStockLoanRate()), MapperS.of(BigDecimal.valueOf(1)), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdditionalDisruptionEventsInitialStockLoanRate {
	
		@Override
		public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents additionalDisruptionEvents) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdditionalDisruptionEvents", path, DEFINITION);
		}
	}
}
