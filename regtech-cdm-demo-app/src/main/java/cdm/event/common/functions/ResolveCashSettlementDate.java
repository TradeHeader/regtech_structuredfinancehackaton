package cdm.event.common.functions;

import cdm.event.common.TradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(ResolveCashSettlementDate.ResolveCashSettlementDateDefault.class)
public abstract class ResolveCashSettlementDate implements RosettaFunction {

	/**
	* @param tradeState 
	* @return date 
	*/
	public Date evaluate(TradeState tradeState) {
		Date date = doEvaluate(tradeState);
		
		return date;
	}

	protected abstract Date doEvaluate(TradeState tradeState);

	public static class ResolveCashSettlementDateDefault extends ResolveCashSettlementDate {
		@Override
		protected Date doEvaluate(TradeState tradeState) {
			Date date = null;
			return assignOutput(date, tradeState);
		}
		
		protected Date assignOutput(Date date, TradeState tradeState) {
			return date;
		}
	}
}
