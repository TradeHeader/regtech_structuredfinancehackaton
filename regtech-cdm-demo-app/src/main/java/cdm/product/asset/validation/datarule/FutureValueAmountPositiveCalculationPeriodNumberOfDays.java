package cdm.product.asset.validation.datarule;

import cdm.product.asset.FutureValueAmount;
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
@RosettaDataRule("FutureValueAmountPositiveCalculationPeriodNumberOfDays")
@ImplementedBy(FutureValueAmountPositiveCalculationPeriodNumberOfDays.Default.class)
public interface FutureValueAmountPositiveCalculationPeriodNumberOfDays extends Validator<FutureValueAmount> {
	
	String NAME = "FutureValueAmountPositiveCalculationPeriodNumberOfDays";
	String DEFINITION = "calculationPeriodNumberOfDays >= 0";
	
	ValidationResult<FutureValueAmount> validate(RosettaPath path, FutureValueAmount futureValueAmount);
	
	class Default implements FutureValueAmountPositiveCalculationPeriodNumberOfDays {
	
		@Override
		public ValidationResult<FutureValueAmount> validate(RosettaPath path, FutureValueAmount futureValueAmount) {
			ComparisonResult result = executeDataRule(futureValueAmount);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FutureValueAmount", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FutureValueAmount", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FutureValueAmount futureValueAmount) {
			try {
				return greaterThanEquals(MapperS.of(futureValueAmount).<Integer>map("getCalculationPeriodNumberOfDays", _futureValueAmount -> _futureValueAmount.getCalculationPeriodNumberOfDays()), MapperS.of(0), CardinalityOperator.All);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FutureValueAmountPositiveCalculationPeriodNumberOfDays {
	
		@Override
		public ValidationResult<FutureValueAmount> validate(RosettaPath path, FutureValueAmount futureValueAmount) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FutureValueAmount", path, DEFINITION);
		}
	}
}
