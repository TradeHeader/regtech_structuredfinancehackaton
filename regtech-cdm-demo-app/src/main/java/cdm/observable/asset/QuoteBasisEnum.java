package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify how an exchange rate is quoted.
 * @version ${project.version}
 */
@RosettaEnum("QuoteBasisEnum")
public enum QuoteBasisEnum {

	/**
	 * The amount of currency1 for one unit of currency2
	 */
	@RosettaEnumValue(value = "Currency1PerCurrency2") CURRENCY_1_PER_CURRENCY_2("Currency1PerCurrency2"),
	
	/**
	 * The amount of currency2 for one unit of currency1
	 */
	@RosettaEnumValue(value = "Currency2PerCurrency1") CURRENCY_2_PER_CURRENCY_1("Currency2PerCurrency1")
;
	private static Map<String, QuoteBasisEnum> values;
	static {
        Map<String, QuoteBasisEnum> map = new ConcurrentHashMap<>();
		for (QuoteBasisEnum instance : QuoteBasisEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	QuoteBasisEnum(String rosettaName) {
		this(rosettaName, null);
	}

	QuoteBasisEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static QuoteBasisEnum fromDisplayName(String name) {
		QuoteBasisEnum value = values.get(name);
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
