package cdm.observable.asset.validation.datarule;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceSchedule;
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
@RosettaDataRule("PriceScheduleArithmeticOperator")
@ImplementedBy(PriceScheduleArithmeticOperator.Default.class)
public interface PriceScheduleArithmeticOperator extends Validator<PriceSchedule> {
	
	String NAME = "PriceScheduleArithmeticOperator";
	String DEFINITION = "arithmeticOperator <> ArithmeticOperationEnum -> Subtract and arithmeticOperator <> ArithmeticOperationEnum -> Divide";
	
	ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule);
	
	class Default implements PriceScheduleArithmeticOperator {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			ComparisonResult result = executeDataRule(priceSchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceSchedule priceSchedule) {
			try {
				return notEqual(MapperS.of(priceSchedule).<ArithmeticOperationEnum>map("getArithmeticOperator", _priceSchedule -> _priceSchedule.getArithmeticOperator()), MapperS.of(ArithmeticOperationEnum.SUBTRACT), CardinalityOperator.Any).and(notEqual(MapperS.of(priceSchedule).<ArithmeticOperationEnum>map("getArithmeticOperator", _priceSchedule -> _priceSchedule.getArithmeticOperator()), MapperS.of(ArithmeticOperationEnum.DIVIDE), CardinalityOperator.Any));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceScheduleArithmeticOperator {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
		}
	}
}
