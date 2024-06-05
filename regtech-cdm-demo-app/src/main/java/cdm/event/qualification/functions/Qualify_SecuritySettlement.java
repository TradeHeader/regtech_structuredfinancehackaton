package cdm.event.qualification.functions;

import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.metafields.FieldWithMetaProductIdentifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.functions.FilterCashTransfers;
import cdm.event.common.functions.FilterSecurityTransfers;
import cdm.event.common.functions.TransfersForDate;
import cdm.observable.asset.Observable;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_SecuritySettlement.Qualify_SecuritySettlementDefault.class)
public abstract class Qualify_SecuritySettlement implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterCashTransfers filterCashTransfers;
	@Inject protected FilterSecurityTransfers filterSecurityTransfers;
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

	protected abstract MapperC<? extends Transfer> transfers(BusinessEvent businessEvent);

	public static class Qualify_SecuritySettlementDefault extends Qualify_SecuritySettlement {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = onlyExists(Arrays.asList(transfers(businessEvent).<Observable>map("getObservable", transferBase -> transferBase.getObservable()).<FieldWithMetaProductIdentifier>mapC("getProductIdentifier", observable -> observable.getProductIdentifier()).<ProductIdentifier>map("getValue", _f->_f.getValue()))).and(onlyExists(Arrays.asList(transfers(businessEvent).<NonNegativeQuantity>map("getQuantity", transferBase -> transferBase.getQuantity()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue())))).and(areEqual(MapperS.of(MapperC.of(filterCashTransfers.evaluate(transfers(businessEvent).getMulti())).get()).<PartyReferencePayerReceiver>map("getPayerReceiver", transferBase -> transferBase.getPayerReceiver()).<ReferenceWithMetaParty>map("getPayerPartyReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getPayerPartyReference()).<Party>map("getValue", _f->_f.getValue()), MapperS.of(MapperC.of(filterSecurityTransfers.evaluate(transfers(businessEvent).getMulti())).get()).<PartyReferencePayerReceiver>map("getPayerReceiver", transferBase -> transferBase.getPayerReceiver()).<ReferenceWithMetaParty>map("getReceiverPartyReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getReceiverPartyReference()).<Party>map("getValue", _f->_f.getValue()), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends Transfer> transfers(BusinessEvent businessEvent) {
			return MapperC.<Transfer>of(transfersForDate.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<TransferState>mapC("getTransferHistory", tradeState -> tradeState.getTransferHistory()).<Transfer>map("getTransfer", transferState -> transferState.getTransfer()).getMulti(), MapperS.of(businessEvent).<Date>map("getEventDate", eventInstruction -> eventInstruction.getEventDate()).get()));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
