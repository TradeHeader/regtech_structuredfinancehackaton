package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.PaymentCalculationPeriod;
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
@RosettaDataRule("PaymentCalculationPeriodCalculationPeriodNumberOfDays")
@ImplementedBy(PaymentCalculationPeriodCalculationPeriodNumberOfDays.Default.class)
public interface PaymentCalculationPeriodCalculationPeriodNumberOfDays extends Validator<PaymentCalculationPeriod> {
	
	String NAME = "PaymentCalculationPeriodCalculationPeriodNumberOfDays";
	String DEFINITION = "if calculationPeriod -> calculationPeriodNumberOfDays exists then calculationPeriod -> calculationPeriodNumberOfDays all >= 0";
	
	ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod paymentCalculationPeriod);
	
	class Default implements PaymentCalculationPeriodCalculationPeriodNumberOfDays {
	
		@Override
		public ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod paymentCalculationPeriod) {
			ComparisonResult result = executeDataRule(paymentCalculationPeriod);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentCalculationPeriod", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PaymentCalculationPeriod", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PaymentCalculationPeriod paymentCalculationPeriod) {
			try {
				if (exists(MapperS.of(paymentCalculationPeriod).<CalculationPeriod>mapC("getCalculationPeriod", _paymentCalculationPeriod -> _paymentCalculationPeriod.getCalculationPeriod()).<Integer>map("getCalculationPeriodNumberOfDays", calculationPeriod -> calculationPeriod.getCalculationPeriodNumberOfDays())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(paymentCalculationPeriod).<CalculationPeriod>mapC("getCalculationPeriod", _paymentCalculationPeriod -> _paymentCalculationPeriod.getCalculationPeriod()).<Integer>map("getCalculationPeriodNumberOfDays", calculationPeriod -> calculationPeriod.getCalculationPeriodNumberOfDays()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PaymentCalculationPeriodCalculationPeriodNumberOfDays {
	
		@Override
		public ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod paymentCalculationPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentCalculationPeriod", path, DEFINITION);
		}
	}
}
