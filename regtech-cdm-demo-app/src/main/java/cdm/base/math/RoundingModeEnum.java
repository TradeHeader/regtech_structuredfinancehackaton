package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the rounding direction when rounding of a number to nearest.  Used by function cdm.base.math.RoundToNearest.
 * @version ${project.version}
 */
@RosettaEnum("RoundingModeEnum")
public enum RoundingModeEnum {

	/**
	 * A number will be rounded down to the specified nearest number. For example, 529 rounded down to the nearest 10 is 520.
	 */
	@RosettaEnumValue(value = "Down") DOWN("Down"),
	
	/**
	 * A number will be rounded up to the specified nearest number. For example, 521 rounded up to the nearest 10 is 530.
	 */
	@RosettaEnumValue(value = "Up") UP("Up")
;
	private static Map<String, RoundingModeEnum> values;
	static {
        Map<String, RoundingModeEnum> map = new ConcurrentHashMap<>();
		for (RoundingModeEnum instance : RoundingModeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RoundingModeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RoundingModeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RoundingModeEnum fromDisplayName(String name) {
		RoundingModeEnum value = values.get(name);
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
