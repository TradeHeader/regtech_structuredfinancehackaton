package cdm.event.qualification.functions;

import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.event.common.BusinessEvent;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.functions.TransfersForDate;
import cdm.observable.asset.Observable;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_SecurityTransfer.Qualify_SecurityTransferDefault.class)
public abstract class Qualify_SecurityTransfer implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected TransfersForDate transfersForDate;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperS<? extends Transfer> transfer(BusinessEvent businessEvent);

	public static class Qualify_SecurityTransferDefault extends Qualify_SecurityTransfer {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(transfer(businessEvent).<Observable>map("getObservable", transferBase -> transferBase.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue())).and(onlyExists(Arrays.asList(transfer(businessEvent).<NonNegativeQuantity>map("getQuantity", transferBase -> transferBase.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FinancialUnitEnum>map("getFinancialUnit", unitType -> unitType.getFinancialUnit())))).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends Transfer> transfer(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(transfersForDate.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<TransferState>mapC("getTransferHistory", tradeState -> tradeState.getTransferHistory()).<Transfer>map("getTransfer", transferState -> transferState.getTransfer()).getMulti(), MapperS.of(businessEvent).<Date>map("getEventDate", eventInstruction -> eventInstruction.getEventDate()).get())).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
