package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify an event that has given rise to a fee.
 * @version ${project.version}
 */
@RosettaEnum("FeeTypeEnum")
public enum FeeTypeEnum {

	/**
	 * A cash flow resulting from the assignment of a contract to a new counterparty.
	 */
	@RosettaEnumValue(value = "Assignment") ASSIGNMENT("Assignment"),
	
	/**
	 * The brokerage commission.
	 */
	@RosettaEnumValue(value = "BrokerageCommission") BROKERAGE_COMMISSION("BrokerageCommission"),
	
	/**
	 * A cash flow associated with an increase lifecycle event.
	 */
	@RosettaEnumValue(value = "Increase") INCREASE("Increase"),
	
	/**
	 * The novation fee.
	 */
	@RosettaEnumValue(value = "Novation") NOVATION("Novation"),
	
	/**
	 * A cash flow associated with a partial termination lifecycle event.
	 */
	@RosettaEnumValue(value = "PartialTermination") PARTIAL_TERMINATION("PartialTermination"),
	
	/**
	 * Denotes the amount payable by the buyer to the seller for an option. The premium is paid on the specified premium payment date or on each premium payment date if specified.
	 */
	@RosettaEnumValue(value = "Premium") PREMIUM("Premium"),
	
	/**
	 * A cash flow associated with a renegotiation lifecycle event.
	 */
	@RosettaEnumValue(value = "Renegotiation") RENEGOTIATION("Renegotiation"),
	
	/**
	 * A cash flow associated with a termination lifecycle event.
	 */
	@RosettaEnumValue(value = "Termination") TERMINATION("Termination"),
	
	/**
	 * An upfront cashflow associated to the swap to adjust for a difference between the swap price and the current market price.
	 */
	@RosettaEnumValue(value = "Upfront") UPFRONT("Upfront"),
	
	/**
	 * A cash flow associated with a credit event.
	 */
	@RosettaEnumValue(value = "CreditEvent") CREDIT_EVENT("CreditEvent"),
	
	/**
	 * A cash flow associated with a corporate action
	 */
	@RosettaEnumValue(value = "CorporateAction") CORPORATE_ACTION("CorporateAction")
;
	private static Map<String, FeeTypeEnum> values;
	static {
        Map<String, FeeTypeEnum> map = new ConcurrentHashMap<>();
		for (FeeTypeEnum instance : FeeTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FeeTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FeeTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FeeTypeEnum fromDisplayName(String name) {
		FeeTypeEnum value = values.get(name);
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
