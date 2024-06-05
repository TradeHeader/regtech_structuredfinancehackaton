package cdm.product.common.schedule.validation.datarule;

import cdm.base.datetime.Offset;
import cdm.product.common.schedule.PaymentDates;
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
@RosettaDataRule("PaymentDatesNonZeroPeriodMultiplier")
@ImplementedBy(PaymentDatesNonZeroPeriodMultiplier.Default.class)
public interface PaymentDatesNonZeroPeriodMultiplier extends Validator<PaymentDates> {
	
	String NAME = "PaymentDatesNonZeroPeriodMultiplier";
	String DEFINITION = "if paymentDaysOffset exists then paymentDaysOffset -> periodMultiplier <> 0";
	
	ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates paymentDates);
	
	class Default implements PaymentDatesNonZeroPeriodMultiplier {
	
		@Override
		public ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates paymentDates) {
			ComparisonResult result = executeDataRule(paymentDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PaymentDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PaymentDates paymentDates) {
			try {
				if (exists(MapperS.of(paymentDates).<Offset>map("getPaymentDaysOffset", _paymentDates -> _paymentDates.getPaymentDaysOffset())).getOrDefault(false)) {
					return notEqual(MapperS.of(paymentDates).<Offset>map("getPaymentDaysOffset", _paymentDates -> _paymentDates.getPaymentDaysOffset()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()), MapperS.of(0), CardinalityOperator.Any);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PaymentDatesNonZeroPeriodMultiplier {
	
		@Override
		public ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates paymentDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentDates", path, DEFINITION);
		}
	}
}
