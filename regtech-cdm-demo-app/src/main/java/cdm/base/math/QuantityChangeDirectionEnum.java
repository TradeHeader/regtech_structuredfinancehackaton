package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies whether a quantity change is an increase, a decrease or a replacement, whereby the quantity is always specified as a positive number.
 * @version ${project.version}
 */
@RosettaEnum("QuantityChangeDirectionEnum")
public enum QuantityChangeDirectionEnum {

	/**
	 * When the quantity should go up by the specified amount.
	 */
	@RosettaEnumValue(value = "Increase") INCREASE("Increase"),
	
	/**
	 * When the quantity should go down by the specified amount.
	 */
	@RosettaEnumValue(value = "Decrease") DECREASE("Decrease"),
	
	/**
	 * When the quantity should be replaced by the specified amount.
	 */
	@RosettaEnumValue(value = "Replace") REPLACE("Replace")
;
	private static Map<String, QuantityChangeDirectionEnum> values;
	static {
        Map<String, QuantityChangeDirectionEnum> map = new ConcurrentHashMap<>();
		for (QuantityChangeDirectionEnum instance : QuantityChangeDirectionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	QuantityChangeDirectionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	QuantityChangeDirectionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static QuantityChangeDirectionEnum fromDisplayName(String name) {
		QuantityChangeDirectionEnum value = values.get(name);
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
