package cdm.product.asset.floatingrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.datetime.functions.AddBusinessDays;
import cdm.base.datetime.functions.GetAllBusinessCenters;
import cdm.product.common.schedule.ResetDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import javax.inject.Inject;


@ImplementedBy(DetermineFixingDate.DetermineFixingDateDefault.class)
public abstract class DetermineFixingDate implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected AddBusinessDays addBusinessDays;
	@Inject protected GetAllBusinessCenters getAllBusinessCenters;

	/**
	* @param resetDates Reset date parameters for observing the rate.
	* @param resetDate The date that the rate is needed for.
	* @return fixingDate The date upon which the rate should be observed. .
	*/
	public Date evaluate(ResetDates resetDates, Date resetDate) {
		Date fixingDate = doEvaluate(resetDates, resetDate);
		
		return fixingDate;
	}

	protected abstract Date doEvaluate(ResetDates resetDates, Date resetDate);

	protected abstract MapperS<Integer> fixingOffsetDays(ResetDates resetDates, Date resetDate);

	protected abstract MapperC<BusinessCenterEnum> businessCenters(ResetDates resetDates, Date resetDate);

	protected abstract MapperS<Date> fixDate(ResetDates resetDates, Date resetDate);

	public static class DetermineFixingDateDefault extends DetermineFixingDate {
		@Override
		protected Date doEvaluate(ResetDates resetDates, Date resetDate) {
			Date fixingDate = null;
			return assignOutput(fixingDate, resetDates, resetDate);
		}
		
		protected Date assignOutput(Date fixingDate, ResetDates resetDates, Date resetDate) {
			fixingDate = fixDate(resetDates, resetDate).get();
			
			return fixingDate;
		}
		
		@Override
		protected MapperS<Integer> fixingOffsetDays(ResetDates resetDates, Date resetDate) {
			return MapperS.of(resetDates).<RelativeDateOffset>map("getFixingDates", _resetDates -> _resetDates.getFixingDates()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier());
		}
		
		@Override
		protected MapperC<BusinessCenterEnum> businessCenters(ResetDates resetDates, Date resetDate) {
			return MapperC.<BusinessCenterEnum>of(getAllBusinessCenters.evaluate(MapperS.of(resetDates).<RelativeDateOffset>map("getFixingDates", _resetDates -> _resetDates.getFixingDates()).<BusinessCenters>map("getBusinessCenters", relativeDateOffset -> relativeDateOffset.getBusinessCenters()).get()));
		}
		
		@Override
		protected MapperS<Date> fixDate(ResetDates resetDates, Date resetDate) {
			return MapperS.of(addBusinessDays.evaluate(resetDate, fixingOffsetDays(resetDates, resetDate).get(), businessCenters(resetDates, resetDate).getMulti()));
		}
	}
}
