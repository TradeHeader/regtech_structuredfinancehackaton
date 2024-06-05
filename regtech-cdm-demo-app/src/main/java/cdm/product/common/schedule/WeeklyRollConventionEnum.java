package cdm.product.common.schedule;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the weekly roll day.
 * @version ${project.version}
 */
@RosettaEnum("WeeklyRollConventionEnum")
public enum WeeklyRollConventionEnum {

	/**
	 * 13-week and 26-week U.S. Treasury Bill Auction Dates. Each Monday except for U.S. (New York) holidays when it will occur on a Tuesday
	 */
	@RosettaEnumValue(value = "TBILL") TBILL("TBILL"),
	
	/**
	 * Monday
	 */
	@RosettaEnumValue(value = "MON") MON("MON"),
	
	/**
	 * Tuesday
	 */
	@RosettaEnumValue(value = "TUE") TUE("TUE"),
	
	/**
	 * Wednesday
	 */
	@RosettaEnumValue(value = "WED") WED("WED"),
	
	/**
	 * Thursday
	 */
	@RosettaEnumValue(value = "THU") THU("THU"),
	
	/**
	 * Friday
	 */
	@RosettaEnumValue(value = "FRI") FRI("FRI"),
	
	/**
	 * Saturday
	 */
	@RosettaEnumValue(value = "SAT") SAT("SAT"),
	
	/**
	 * Sunday
	 */
	@RosettaEnumValue(value = "SUN") SUN("SUN")
;
	private static Map<String, WeeklyRollConventionEnum> values;
	static {
        Map<String, WeeklyRollConventionEnum> map = new ConcurrentHashMap<>();
		for (WeeklyRollConventionEnum instance : WeeklyRollConventionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	WeeklyRollConventionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	WeeklyRollConventionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static WeeklyRollConventionEnum fromDisplayName(String name) {
		WeeklyRollConventionEnum value = values.get(name);
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
