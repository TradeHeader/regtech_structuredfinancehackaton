package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the period, e.g. day, week.
 * @version ${project.version}
 */
@RosettaEnum("PeriodEnum")
public enum PeriodEnum {

	/**
	 * Day
	 */
	@RosettaEnumValue(value = "D") D("D"),
	
	/**
	 * Week
	 */
	@RosettaEnumValue(value = "W") W("W"),
	
	/**
	 * Month
	 */
	@RosettaEnumValue(value = "M") M("M"),
	
	/**
	 * Year
	 */
	@RosettaEnumValue(value = "Y") Y("Y")
;
	private static Map<String, PeriodEnum> values;
	static {
        Map<String, PeriodEnum> map = new ConcurrentHashMap<>();
		for (PeriodEnum instance : PeriodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PeriodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PeriodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PeriodEnum fromDisplayName(String name) {
		PeriodEnum value = values.get(name);
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
