package cdm.product.common.schedule.functions;

import cdm.base.datetime.AdjustableDate;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;


@ImplementedBy(PaymentDate.PaymentDateDefault.class)
public abstract class PaymentDate implements RosettaFunction {

	/**
	* @param economicTerms 
	* @return result 
	*/
	public Date evaluate(EconomicTerms economicTerms) {
		Date result = doEvaluate(economicTerms);
		
		return result;
	}

	protected abstract Date doEvaluate(EconomicTerms economicTerms);

	public static class PaymentDateDefault extends PaymentDate {
		@Override
		protected Date doEvaluate(EconomicTerms economicTerms) {
			Date result = null;
			return assignOutput(result, economicTerms);
		}
		
		protected Date assignOutput(Date result, EconomicTerms economicTerms) {
			result = MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()).get()).<AdjustableDate>map("getPaymentDate", interestRatePayout -> interestRatePayout.getPaymentDate()).<FieldWithMetaDate>map("getAdjustedDate", adjustableDate -> adjustableDate.getAdjustedDate()).<Date>map("getValue", _f->_f.getValue()).get();
			
			return result;
		}
	}
}
