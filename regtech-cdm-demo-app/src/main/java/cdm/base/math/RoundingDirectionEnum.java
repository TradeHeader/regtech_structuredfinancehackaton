package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the rounding direction and precision to be used in the rounding of a number.  Used by function cdm.base.math.RoundToPrecision.
 * @version ${project.version}
 */
@RosettaEnum("RoundingDirectionEnum")
public enum RoundingDirectionEnum {

	/**
	 * A fractional number will be rounded up to the specified number of decimal places (the precision). For example, 5.21 and 5.25 rounded up to 1 decimal place are 5.3 and 5.3 respectively.
	 */
	@RosettaEnumValue(value = "Up") UP("Up"),
	
	/**
	 * A fractional number will be rounded down to the specified number of decimal places (the precision). For example, 5.29 and 5.25 rounded down to 1 decimal place are 5.2 and 5.2 respectively.
	 */
	@RosettaEnumValue(value = "Down") DOWN("Down"),
	
	/**
	 * A fractional number will be rounded either up or down to the specified number of decimal places (the precision) depending on its value. For example, 5.24 would be rounded down to 5.2 and 5.25 would be rounded up to 5.3 if a precision of 1 decimal place were specified.
	 */
	@RosettaEnumValue(value = "Nearest") NEAREST("Nearest")
;
	private static Map<String, RoundingDirectionEnum> values;
	static {
        Map<String, RoundingDirectionEnum> map = new ConcurrentHashMap<>();
		for (RoundingDirectionEnum instance : RoundingDirectionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RoundingDirectionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RoundingDirectionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RoundingDirectionEnum fromDisplayName(String name) {
		RoundingDirectionEnum value = values.get(name);
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
