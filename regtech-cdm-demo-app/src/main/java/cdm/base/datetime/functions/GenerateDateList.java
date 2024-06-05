package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GenerateDateList.GenerateDateListDefault.class)
public abstract class GenerateDateList implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected AddBusinessDays addBusinessDays;
	@Inject protected AppendDateToList appendDateToList;
	@Inject protected cdm.base.datetime.functions.GenerateDateList generateDateList;
	@Inject protected IsBusinessDay isBusinessDay;

	/**
	* @param startDate Start of the date range to be generated.
	* @param endDate End of the date range to be generated
	* @param businessCenters Business centers to be used to generate the list of good business days
	* @return dateList Resulting list of good business days.
	*/
	public List<Date> evaluate(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
		List<Date> dateList = doEvaluate(startDate, endDate, businessCenters);
		
		return dateList;
	}

	protected abstract List<Date> doEvaluate(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> active(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> isGoodBusinessDay(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Date> priorDate(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperC<Date> priorList(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperC<Date> newList(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters);

	public static class GenerateDateListDefault extends GenerateDateList {
		@Override
		protected List<Date> doEvaluate(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			List<Date> dateList = new ArrayList<>();
			return assignOutput(dateList, startDate, endDate, businessCenters);
		}
		
		protected List<Date> assignOutput(List<Date> dateList, Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			if (active(startDate, endDate, businessCenters).getOrDefault(false)) {
				dateList.addAll(newList(startDate, endDate, businessCenters).getMulti());
			} else {
				dateList.addAll(Collections.<Date>emptyList());
			}
			
			return dateList;
		}
		
		@Override
		protected MapperS<Boolean> active(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			return lessThanEquals(MapperS.of(startDate), MapperS.of(endDate), CardinalityOperator.All).asMapper();
		}
		
		@Override
		protected MapperS<Boolean> isGoodBusinessDay(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(isBusinessDay.evaluate(endDate, businessCenters));
		}
		
		@Override
		protected MapperS<Date> priorDate(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(addBusinessDays.evaluate(endDate, -1, businessCenters));
		}
		
		@Override
		protected MapperC<Date> priorList(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			return MapperC.<Date>of(generateDateList.evaluate(startDate, priorDate(startDate, endDate, businessCenters).get(), businessCenters));
		}
		
		@Override
		protected MapperC<Date> newList(Date startDate, Date endDate, List<BusinessCenterEnum> businessCenters) {
			if (isGoodBusinessDay(startDate, endDate, businessCenters).getOrDefault(false)) {
				return MapperC.<Date>of(appendDateToList.evaluate(priorList(startDate, endDate, businessCenters).getMulti(), endDate));
			}
			return priorList(startDate, endDate, businessCenters);
		}
	}
}
