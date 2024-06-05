package cdm.product.qualification.functions;

import cdm.base.datetime.Frequency;
import cdm.base.datetime.PeriodExtendedEnum;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Transaction_ZeroCoupon_KnownAmount.Qualify_Transaction_ZeroCoupon_KnownAmountDefault.class)
public abstract class Qualify_Transaction_ZeroCoupon_KnownAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_SubProduct_FixedFloat qualify_SubProduct_FixedFloat;
	@Inject protected Qualify_Transaction_ZeroCoupon qualify_Transaction_ZeroCoupon;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	public static class Qualify_Transaction_ZeroCoupon_KnownAmountDefault extends Qualify_Transaction_ZeroCoupon_KnownAmount {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			final MapperC<InterestRatePayout> thenResult = MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout())
				.filterItemNullSafe(item -> exists(item.<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity())).and(notExists(item.<RateSpecification>map("getRateSpecification", interestRatePayout -> interestRatePayout.getRateSpecification()))).and(areEqual(item.<PaymentDates>map("getPaymentDates", interestRatePayout -> interestRatePayout.getPaymentDates()).<Frequency>map("getPaymentFrequency", paymentDates -> paymentDates.getPaymentFrequency()).<Integer>map("getPeriodMultiplier", frequency -> frequency.getPeriodMultiplier()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(item.<PaymentDates>map("getPaymentDates", interestRatePayout -> interestRatePayout.getPaymentDates()).<Frequency>map("getPaymentFrequency", paymentDates -> paymentDates.getPaymentFrequency()).<PeriodExtendedEnum>map("getPeriod", frequency -> frequency.getPeriod()), MapperS.of(PeriodExtendedEnum.T), CardinalityOperator.All)).get());
			is_product = areEqual(MapperS.of(qualify_SubProduct_FixedFloat.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(qualify_Transaction_ZeroCoupon.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All)).and(ComparisonResult.of(exists(thenResult).asMapper())).get();
			
			return is_product;
		}
	}
}
