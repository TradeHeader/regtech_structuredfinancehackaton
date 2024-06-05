package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * How often is rounding performed
 * @version ${project.version}
 */
@RosettaEnum("RoundingFrequencyEnum")
public enum RoundingFrequencyEnum {

	/**
	 * Rounding is done on each day
	 */
	@RosettaEnumValue(value = "Daily") DAILY("Daily"),
	
	/**
	 * Rounding is done only at the end of the period
	 */
	@RosettaEnumValue(value = "PeriodEnd") PERIOD_END("PeriodEnd")
;
	private static Map<String, RoundingFrequencyEnum> values;
	static {
        Map<String, RoundingFrequencyEnum> map = new ConcurrentHashMap<>();
		for (RoundingFrequencyEnum instance : RoundingFrequencyEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RoundingFrequencyEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RoundingFrequencyEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RoundingFrequencyEnum fromDisplayName(String name) {
		RoundingFrequencyEnum value = values.get(name);
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
