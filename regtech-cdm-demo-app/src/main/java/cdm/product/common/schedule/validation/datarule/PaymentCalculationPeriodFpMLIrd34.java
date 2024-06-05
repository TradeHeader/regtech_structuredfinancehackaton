package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.PaymentCalculationPeriod;
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
@RosettaDataRule("PaymentCalculationPeriodFpML_ird_34")
@ImplementedBy(PaymentCalculationPeriodFpMLIrd34.Default.class)
public interface PaymentCalculationPeriodFpMLIrd34 extends Validator<PaymentCalculationPeriod> {
	
	String NAME = "PaymentCalculationPeriodFpML_ird_34";
	String DEFINITION = "unadjustedPaymentDate exists or adjustedPaymentDate exists";
	
	ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod paymentCalculationPeriod);
	
	class Default implements PaymentCalculationPeriodFpMLIrd34 {
	
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
				return exists(MapperS.of(paymentCalculationPeriod).<Date>map("getUnadjustedPaymentDate", _paymentCalculationPeriod -> _paymentCalculationPeriod.getUnadjustedPaymentDate())).or(exists(MapperS.of(paymentCalculationPeriod).<Date>map("getAdjustedPaymentDate", _paymentCalculationPeriod -> _paymentCalculationPeriod.getAdjustedPaymentDate())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PaymentCalculationPeriodFpMLIrd34 {
	
		@Override
		public ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod paymentCalculationPeriod) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentCalculationPeriod", path, DEFINITION);
		}
	}
}
