package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the type of Equity.
 * @version ${project.version}
 */
@RosettaEnum("EquityTypeEnum")
public enum EquityTypeEnum {

	/**
	 * Identifies an Equity of Common stocks and shares.
	 */
	@RosettaEnumValue(value = "Ordinary") ORDINARY("Ordinary"),
	
	/**
	 * Identifies an Equity of Non-Convertible Preference, Shares which hold priority to receive capital return in event of issuer liquidation.
	 */
	@RosettaEnumValue(value = "NonConvertiblePreference") NON_CONVERTIBLE_PREFERENCE("NonConvertiblePreference")
;
	private static Map<String, EquityTypeEnum> values;
	static {
        Map<String, EquityTypeEnum> map = new ConcurrentHashMap<>();
		for (EquityTypeEnum instance : EquityTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	EquityTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	EquityTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static EquityTypeEnum fromDisplayName(String name) {
		EquityTypeEnum value = values.get(name);
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
