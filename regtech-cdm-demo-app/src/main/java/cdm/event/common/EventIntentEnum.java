package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to qualify the intent associated with a transaction event.
 * @version ${project.version}
 */
@RosettaEnum("EventIntentEnum")
public enum EventIntentEnum {

	/**
	 * The intent is to allocate one or more trades as part of an allocated block trade.
	 */
	@RosettaEnumValue(value = "Allocation") ALLOCATION("Allocation"),
	
	/**
	 * The intent is to designate a stand-alone cash transfer as a result of Trade contracual terms e.g. incurred by payout for instance a Performance Amount or a Floating Rate Amount. The particular CashFlow at stake shall be further specified in priceTransferEnum or transferTypeEnum. For clarity, such intentEnum value shall not be used whenever a cash transfer is not stand-alone but is instead embedded in another Event as part of the composable modelling e.g. Decrease with Fees, Cross-Currency Notional Reset, etc. or any other Event whenever including a cash transfer with other features. For clarity, a principal payment related to a Principal Exhange is excluded as well, because a dedicated intentEnum value exists for this event i.e. PrincipalExchange value.
	 */
	@RosettaEnumValue(value = "CashFlow") CASH_FLOW("CashFlow"),
	
	/**
	 * The intent is to clear the contract.
	 */
	@RosettaEnumValue(value = "Clearing") CLEARING("Clearing"),
	
	/**
	 * The intent is to compress multiple trades as part of a netting or compression event.
	 */
	@RosettaEnumValue(value = "Compression") COMPRESSION("Compression"),
	
	/**
	 * The intent is to form a contract from an execution.
	 */
	@RosettaEnumValue(value = "ContractFormation") CONTRACT_FORMATION("ContractFormation"),
	
	/**
	 * The intent is to amend the terms of the contract through renegotiation.
	 */
	@RosettaEnumValue(value = "ContractTermsAmendment") CONTRACT_TERMS_AMENDMENT("ContractTermsAmendment"),
	
	/**
	 * The intent is to take into effect the occurrence of a Corporate Action and the particular Corporate Action at stake shall be further specified in CorporateActionTypeEnum.
	 */
	@RosettaEnumValue(value = "CorporateActionAdjustment") CORPORATE_ACTION_ADJUSTMENT("CorporateActionAdjustment"),
	
	/**
	 * The intent is to take into effect the occurrence of a Credit Event.
	 */
	@RosettaEnumValue(value = "CreditEvent") CREDIT_EVENT("CreditEvent"),
	
	/**
	 * The intent is to Decrease the quantity or notional of the contract.
	 */
	@RosettaEnumValue(value = "Decrease") DECREASE("Decrease"),
	
	/**
	 * The intent is to fully unwind the Trade, as a result of the application of Trade contractual terms (e.g. an obligation to do so before Termination Date as part of any kind of Early Termination terms) as defined within the CDM EarlyTerminationProvision data type. Accordingly, increase and decrease of positions which result from negotiation by the parties shall not be designated by such intentEnum. For clarity, partial exercise of an option before its expiration date is excluded as well, though related to Trade contract terms, because a dedicated intentEnum value exists for this event i.e. OptionExercise value.
	 */
	@RosettaEnumValue(value = "EarlyTerminationProvision") EARLY_TERMINATION_PROVISION("EarlyTerminationProvision"),
	
	/**
	 * The intent is to Increase the quantity or notional of the contract.
	 */
	@RosettaEnumValue(value = "Increase") INCREASE("Increase"),
	
	/**
	 * The intent is to replace an interest rate index by another one during the life of a trade and add a transition spread on top of this index (and on top of the spreads already defined in the trade, if any). 
	 */
	@RosettaEnumValue(value = "IndexTransition") INDEX_TRANSITION("IndexTransition"),
	
	/**
	 * The intent is to increase or to decrease the notional of the Trade, in accordance with Notional Reset features e.g. could apply for Cross Currency Swaps, Equity Performance Swaps, etc.
	 */
	@RosettaEnumValue(value = "NotionalReset") NOTIONAL_RESET("NotionalReset"),
	
	/**
	 * The intent is to increase or to decrease the notional of the Trade, in accordance with Step features attached to a Payout Quantity.
	 */
	@RosettaEnumValue(value = "NotionalStep") NOTIONAL_STEP("NotionalStep"),
	
	/**
	 * The intent is to novate the contract.
	 */
	@RosettaEnumValue(value = "Novation") NOVATION("Novation"),
	
	/**
	 * The intent is to record any kind of stand-alone obervervations e.g. internal data recording, usage of CDM for recording and/or exchanging data as part of pricing &#39;consensus&#39; processing, etc. For clarity, such intentEnum value shall not be used whenever an observation is not stand-alone but is instead embedded in another Event as part of the composable modelling e.g. CashFlow to which an observation of prices is associated, etc.
	 */
	@RosettaEnumValue(value = "ObservationRecord") OBSERVATION_RECORD("ObservationRecord"),
	
	/**
	 * The intent is to Exercise a contract that is made of one or several option payout legs. For clarity, such intentEnum value shall not be used whenever an optional right is exercised in relation with a Trade which composition includes other types of payout legs e.g. right to call or to cancel before Termination Date as part of any kind of Early Termination terms other than genuine bermuda or american style features described in option payout. 
	 */
	@RosettaEnumValue(value = "OptionExercise") OPTION_EXERCISE("OptionExercise"),
	
	/**
	 * The intent is to extend the trade through exercise of an optional right as defined within the CDM OptionProvision data type.
	 */
	@RosettaEnumValue(value = "OptionalExtension") OPTIONAL_EXTENSION("OptionalExtension"),
	
	/**
	 * The intent is to cancel the trade through exercise of an optional right as defined within the CDM OptionProvision data type.
	 */
	@RosettaEnumValue(value = "OptionalCancellation") OPTIONAL_CANCELLATION("OptionalCancellation"),
	
	/**
	 * The intent is to rebalance a portfolio, by inserting new derivatives transactions into portfolios of participants to reduce risks linked to those trades. These are offsetting trades that rebalance relationships between different counterparties when it comes to exposure of portfolios to certain types of risk, such as interest rate risk.
	 */
	@RosettaEnumValue(value = "PortfolioRebalancing") PORTFOLIO_REBALANCING("PortfolioRebalancing"),
	
	/**
	 * The intent is to pay or to receive a cash transfer, in accordance with Principal Exchange features.
	 */
	@RosettaEnumValue(value = "PrincipalExchange") PRINCIPAL_EXCHANGE("PrincipalExchange"),
	
	/**
	 * The intent is to reallocate one or more trades as part of an allocated block trade.
	 */
	@RosettaEnumValue(value = "Reallocation") REALLOCATION("Reallocation"),
	
	/**
	 * The intent is to close a repo transaction through repurchase.
	 */
	@RosettaEnumValue(value = "Repurchase") REPURCHASE("Repurchase")
;
	private static Map<String, EventIntentEnum> values;
	static {
        Map<String, EventIntentEnum> map = new ConcurrentHashMap<>();
		for (EventIntentEnum instance : EventIntentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	EventIntentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	EventIntentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static EventIntentEnum fromDisplayName(String name) {
		EventIntentEnum value = values.get(name);
		if (value == null) {
			throw new IllegalArgumentException("No enum constant with display name \"" + name + "\".");
		}
		return value;
	}

	@Override
	public String toString() {
		return toDisplayString();
	}

	public String toDisplayString() {
		return displayName != null ?  displayName : rosettaName;
	}
}
