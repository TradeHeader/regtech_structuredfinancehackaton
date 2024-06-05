package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.PaymentDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("PaymentDatesFpML_ird_35_cd_31")
@ImplementedBy(PaymentDatesFpMLIrd35Cd31.Default.class)
public interface PaymentDatesFpMLIrd35Cd31 extends Validator<PaymentDates> {
	
	String NAME = "PaymentDatesFpML_ird_35_cd_31";
	String DEFINITION = "if firstPaymentDate exists and lastRegularPaymentDate exists then firstPaymentDate < lastRegularPaymentDate";
	
	ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates paymentDates);
	
	class Default implements PaymentDatesFpMLIrd35Cd31 {
	
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
				if (exists(MapperS.of(paymentDates).<Date>map("getFirstPaymentDate", _paymentDates -> _paymentDates.getFirstPaymentDate())).and(exists(MapperS.of(paymentDates).<Date>map("getLastRegularPaymentDate", _paymentDates -> _paymentDates.getLastRegularPaymentDate()))).getOrDefault(false)) {
					return lessThan(MapperS.of(paymentDates).<Date>map("getFirstPaymentDate", _paymentDates -> _paymentDates.getFirstPaymentDate()), MapperS.of(paymentDates).<Date>map("getLastRegularPaymentDate", _paymentDates -> _paymentDates.getLastRegularPaymentDate()), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PaymentDatesFpMLIrd35Cd31 {
	
		@Override
		public ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates paymentDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PaymentDates", path, DEFINITION);
		}
	}
}
