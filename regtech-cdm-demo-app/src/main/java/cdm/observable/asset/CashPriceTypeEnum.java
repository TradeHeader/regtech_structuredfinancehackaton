package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides a list of possible types of cash prices, applicable when PriceTypeEnum is itself of type CashPrice.
 * @version ${project.version}
 */
@RosettaEnum("CashPriceTypeEnum")
public enum CashPriceTypeEnum {

	/**
	 * Denotes the amount payable by the buyer to the seller for an option. The premium is paid on the specified premium payment date or on each premium payment date if specified.
	 */
	@RosettaEnumValue(value = "Premium") PREMIUM("Premium"),
	
	/**
	 * A generic term for describing a non-scheduled cashflow that can be associated either with the initial contract, with some later corrections to it (e.g. a correction to the day count fraction that has a cashflow impact) or with some lifecycle events. Fees that are specifically associated with termination and partial termination, increase, amendment, and exercise events are qualified accordingly.
	 */
	@RosettaEnumValue(value = "Fee") FEE("Fee"),
	
	/**
	 * Denotes a discount factor expressed as a decimal, e.g. 0.95.
	 */
	@RosettaEnumValue(value = "Discount") DISCOUNT("Discount")
;
	private static Map<String, CashPriceTypeEnum> values;
	static {
        Map<String, CashPriceTypeEnum> map = new ConcurrentHashMap<>();
		for (CashPriceTypeEnum instance : CashPriceTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CashPriceTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CashPriceTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CashPriceTypeEnum fromDisplayName(String name) {
		CashPriceTypeEnum value = values.get(name);
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
