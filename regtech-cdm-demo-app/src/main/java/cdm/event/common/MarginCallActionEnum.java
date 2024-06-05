package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration values to identify the collateral action instruction.
 * @version ${project.version}
 */
@RosettaEnum("MarginCallActionEnum")
public enum MarginCallActionEnum {

	/**
	 * Indicates an instruction of a new collateral asset delivery.
	 */
	@RosettaEnumValue(value = "Delivery") DELIVERY("Delivery"),
	
	/**
	 * Indicates an instruction for a return of a principals collateral asset delivery.
	 */
	@RosettaEnumValue(value = "Return") RETURN("Return")
;
	private static Map<String, MarginCallActionEnum> values;
	static {
        Map<String, MarginCallActionEnum> map = new ConcurrentHashMap<>();
		for (MarginCallActionEnum instance : MarginCallActionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MarginCallActionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MarginCallActionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MarginCallActionEnum fromDisplayName(String name) {
		MarginCallActionEnum value = values.get(name);
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
