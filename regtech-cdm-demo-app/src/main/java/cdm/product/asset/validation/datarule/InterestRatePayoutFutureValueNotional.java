package cdm.product.asset.validation.datarule;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FutureValueAmount;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.common.settlement.ResolvablePriceQuantity;
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
@RosettaDataRule("InterestRatePayoutFutureValueNotional")
@ImplementedBy(InterestRatePayoutFutureValueNotional.Default.class)
public interface InterestRatePayoutFutureValueNotional extends Validator<InterestRatePayout> {
	
	String NAME = "InterestRatePayoutFutureValueNotional";
	String DEFINITION = "if rateSpecification -> fixedRate is absent then priceQuantity -> futureValueNotional is absent";
	
	ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout);
	
	class Default implements InterestRatePayoutFutureValueNotional {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			ComparisonResult result = executeDataRule(interestRatePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InterestRatePayout interestRatePayout) {
			try {
				if (notExists(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FixedRateSpecification>map("getFixedRate", rateSpecification -> rateSpecification.getFixedRate())).getOrDefault(false)) {
					return notExists(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<FutureValueAmount>map("getFutureValueNotional", resolvablePriceQuantity -> resolvablePriceQuantity.getFutureValueNotional()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InterestRatePayoutFutureValueNotional {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
		}
	}
}
