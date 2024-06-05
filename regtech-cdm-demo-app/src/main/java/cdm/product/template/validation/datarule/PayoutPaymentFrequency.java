package cdm.product.template.validation.datarule;

import cdm.base.datetime.Frequency;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.template.Payout;
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
@RosettaDataRule("PayoutPaymentFrequency")
@ImplementedBy(PayoutPaymentFrequency.Default.class)
public interface PayoutPaymentFrequency extends Validator<Payout> {
	
	String NAME = "PayoutPaymentFrequency";
	String DEFINITION = "if interestRatePayout count = 2 and interestRatePayout -> paymentDates exists then interestRatePayout -> paymentDates -> paymentFrequency exists";
	
	ValidationResult<Payout> validate(RosettaPath path, Payout payout);
	
	class Default implements PayoutPaymentFrequency {
	
		@Override
		public ValidationResult<Payout> validate(RosettaPath path, Payout payout) {
			ComparisonResult result = executeDataRule(payout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Payout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Payout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Payout payout) {
			try {
				if (areEqual(MapperS.of(MapperS.of(payout).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()).resultCount()), MapperS.of(2), CardinalityOperator.All).and(exists(MapperS.of(payout).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()).<PaymentDates>map("getPaymentDates", interestRatePayout -> interestRatePayout.getPaymentDates()))).getOrDefault(false)) {
					return exists(MapperS.of(payout).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()).<PaymentDates>map("getPaymentDates", interestRatePayout -> interestRatePayout.getPaymentDates()).<Frequency>map("getPaymentFrequency", paymentDates -> paymentDates.getPaymentFrequency()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PayoutPaymentFrequency {
	
		@Override
		public ValidationResult<Payout> validate(RosettaPath path, Payout payout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Payout", path, DEFINITION);
		}
	}
}
