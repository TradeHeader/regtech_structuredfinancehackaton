package cdm.product.asset.validation.datarule;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.asset.FutureValueAmount;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.settlement.ResolvablePriceQuantity;
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
import com.rosetta.model.metafields.FieldWithMetaDate;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("InterestRatePayoutTerminationDate")
@ImplementedBy(InterestRatePayoutTerminationDate.Default.class)
public interface InterestRatePayoutTerminationDate extends Validator<InterestRatePayout> {
	
	String NAME = "InterestRatePayoutTerminationDate";
	String DEFINITION = "if priceQuantity -> futureValueNotional exists then priceQuantity -> futureValueNotional -> valueDate = calculationPeriodDates -> terminationDate -> adjustableDate -> adjustedDate";
	
	ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout);
	
	class Default implements InterestRatePayoutTerminationDate {
	
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
				if (exists(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<FutureValueAmount>map("getFutureValueNotional", resolvablePriceQuantity -> resolvablePriceQuantity.getFutureValueNotional())).getOrDefault(false)) {
					return areEqual(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<FutureValueAmount>map("getFutureValueNotional", resolvablePriceQuantity -> resolvablePriceQuantity.getFutureValueNotional()).<Date>map("getValueDate", futureValueAmount -> futureValueAmount.getValueDate()), MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).<AdjustableOrRelativeDate>map("getTerminationDate", calculationPeriodDates -> calculationPeriodDates.getTerminationDate()).<AdjustableDate>map("getAdjustableDate", adjustableOrRelativeDate -> adjustableOrRelativeDate.getAdjustableDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableDate -> adjustableDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InterestRatePayoutTerminationDate {
	
		@Override
		public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout interestRatePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InterestRatePayout", path, DEFINITION);
		}
	}
}
