package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration indicators to specify if an asset or group of assets valuation is based on any valuation treatment haircut.
 * @version ${project.version}
 */
@RosettaEnum("HaircutIndicatorEnum")
public enum HaircutIndicatorEnum {

	/**
	 * Indicates Pre haircut value
	 */
	@RosettaEnumValue(value = "PreHaircut") PRE_HAIRCUT("PreHaircut"),
	
	/**
	 * Indicates Post haircut value
	 */
	@RosettaEnumValue(value = "PostHaircut") POST_HAIRCUT("PostHaircut")
;
	private static Map<String, HaircutIndicatorEnum> values;
	static {
        Map<String, HaircutIndicatorEnum> map = new ConcurrentHashMap<>();
		for (HaircutIndicatorEnum instance : HaircutIndicatorEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	HaircutIndicatorEnum(String rosettaName) {
		this(rosettaName, null);
	}

	HaircutIndicatorEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static HaircutIndicatorEnum fromDisplayName(String name) {
		HaircutIndicatorEnum value = values.get(name);
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
