package cdm.base.datetime.daycount.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(YearFractionForOneDay.YearFractionForOneDayDefault.class)
public abstract class YearFractionForOneDay implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected DayCountBasis dayCountBasis;

	/**
	* @param dcf Supplied Day count fraction.
	* @return yearFrac Corresponding year fraction.
	*/
	public BigDecimal evaluate(DayCountFractionEnum dcf) {
		BigDecimal yearFrac = doEvaluate(dcf);
		
		return yearFrac;
	}

	protected abstract BigDecimal doEvaluate(DayCountFractionEnum dcf);

	public static class YearFractionForOneDayDefault extends YearFractionForOneDay {
		@Override
		protected BigDecimal doEvaluate(DayCountFractionEnum dcf) {
			BigDecimal yearFrac = null;
			return assignOutput(yearFrac, dcf);
		}
		
		protected BigDecimal assignOutput(BigDecimal yearFrac, DayCountFractionEnum dcf) {
			yearFrac = MapperMaths.<BigDecimal, Integer, Integer>divide(MapperS.of(1), MapperS.of(dayCountBasis.evaluate(dcf))).get();
			
			return yearFrac;
		}
	}
}
