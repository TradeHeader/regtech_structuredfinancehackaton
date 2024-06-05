package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify a long or short spread value.
 * @version ${project.version}
 */
@RosettaEnum("SpreadScheduleTypeEnum")
public enum SpreadScheduleTypeEnum {

	/**
	 * Represents a Long Spread Schedule. Spread schedules defined as &#39;Long&#39; will be applied to Long Positions.
	 */
	@RosettaEnumValue(value = "Long") LONG("Long"),
	
	/**
	 * Represents a Short Spread Schedule. Spread schedules defined as &#39;Short&#39; will be applied to Short Positions.
	 */
	@RosettaEnumValue(value = "Short") SHORT("Short")
;
	private static Map<String, SpreadScheduleTypeEnum> values;
	static {
        Map<String, SpreadScheduleTypeEnum> map = new ConcurrentHashMap<>();
		for (SpreadScheduleTypeEnum instance : SpreadScheduleTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SpreadScheduleTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SpreadScheduleTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SpreadScheduleTypeEnum fromDisplayName(String name) {
		SpreadScheduleTypeEnum value = values.get(name);
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
