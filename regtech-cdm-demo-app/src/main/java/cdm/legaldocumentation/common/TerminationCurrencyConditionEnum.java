package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("TerminationCurrencyConditionEnum")
public enum TerminationCurrencyConditionEnum {

	/**
	 * A currency in which payments would be due under one or more Transactions.
	 */
	@RosettaEnumValue(value = "PaymentsDue") PAYMENTS_DUE("PaymentsDue"),
	
	/**
	 * A currency that is freely available.
	 */
	@RosettaEnumValue(value = "FreelyAvailable") FREELY_AVAILABLE("FreelyAvailable"),
	
	/**
	 * A currency in which payments would be due under one or more Transactions and that is freely available.
	 */
	@RosettaEnumValue(value = "PaymentsDueAndFreelyAvailable") PAYMENTS_DUE_AND_FREELY_AVAILABLE("PaymentsDueAndFreelyAvailable"),
	
	/**
	 * Termination Currency Conditions are specified.
	 */
	@RosettaEnumValue(value = "Specified") SPECIFIED("Specified")
;
	private static Map<String, TerminationCurrencyConditionEnum> values;
	static {
        Map<String, TerminationCurrencyConditionEnum> map = new ConcurrentHashMap<>();
		for (TerminationCurrencyConditionEnum instance : TerminationCurrencyConditionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TerminationCurrencyConditionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TerminationCurrencyConditionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TerminationCurrencyConditionEnum fromDisplayName(String name) {
		TerminationCurrencyConditionEnum value = values.get(name);
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
