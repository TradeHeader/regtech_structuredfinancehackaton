package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify a day of the seven-day week.
 * @version ${project.version}
 */
@RosettaEnum("DayOfWeekEnum")
public enum DayOfWeekEnum {

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
	private static Map<String, DayOfWeekEnum> values;
	static {
        Map<String, DayOfWeekEnum> map = new ConcurrentHashMap<>();
		for (DayOfWeekEnum instance : DayOfWeekEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DayOfWeekEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DayOfWeekEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DayOfWeekEnum fromDisplayName(String name) {
		DayOfWeekEnum value = values.get(name);
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
