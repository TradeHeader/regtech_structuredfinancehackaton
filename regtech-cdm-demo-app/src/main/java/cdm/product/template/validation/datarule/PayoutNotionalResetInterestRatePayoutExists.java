package cdm.product.template.validation.datarule;

import cdm.product.asset.InterestRatePayout;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("PayoutNotionalResetInterestRatePayoutExists")
@ImplementedBy(PayoutNotionalResetInterestRatePayoutExists.Default.class)
public interface PayoutNotionalResetInterestRatePayoutExists extends Validator<Payout> {
	
	String NAME = "PayoutNotionalResetInterestRatePayoutExists";
	String DEFINITION = "if performancePayout -> priceQuantity -> reset contains True then interestRatePayout exists";
	
	ValidationResult<Payout> validate(RosettaPath path, Payout payout);
	
	class Default implements PayoutNotionalResetInterestRatePayoutExists {
	
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
				if (contains(MapperS.of(payout).<PerformancePayout>mapC("getPerformancePayout", _payout -> _payout.getPerformancePayout()).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<Boolean>map("getReset", resolvablePriceQuantity -> resolvablePriceQuantity.getReset()), MapperS.of(true)).getOrDefault(false)) {
					return exists(MapperS.of(payout).<InterestRatePayout>mapC("getInterestRatePayout", _payout -> _payout.getInterestRatePayout()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PayoutNotionalResetInterestRatePayoutExists {
	
		@Override
		public ValidationResult<Payout> validate(RosettaPath path, Payout payout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Payout", path, DEFINITION);
		}
	}
}
