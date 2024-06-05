package cdm.observable.event;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify whether an option will trigger or expire depending upon whether the spot rate is above or below the barrier rate.
 * @version ${project.version}
 */
@RosettaEnum("TriggerTypeEnum")
public enum TriggerTypeEnum {

	/**
	 * The underlier price must be equal to or less than the Trigger level.
	 */
	@RosettaEnumValue(value = "EqualOrLess") EQUAL_OR_LESS("EqualOrLess"),
	
	/**
	 * The underlier price must be equal to or greater than the Trigger level.
	 */
	@RosettaEnumValue(value = "EqualOrGreater") EQUAL_OR_GREATER("EqualOrGreater"),
	
	/**
	 * The underlier price must be equal to the Trigger level.
	 */
	@RosettaEnumValue(value = "Equal") EQUAL("Equal"),
	
	/**
	 * The underlier price must be less than the Trigger level.
	 */
	@RosettaEnumValue(value = "Less") LESS("Less"),
	
	/**
	 * The underlier price must be greater than the Trigger level.
	 */
	@RosettaEnumValue(value = "Greater") GREATER("Greater")
;
	private static Map<String, TriggerTypeEnum> values;
	static {
        Map<String, TriggerTypeEnum> map = new ConcurrentHashMap<>();
		for (TriggerTypeEnum instance : TriggerTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TriggerTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TriggerTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TriggerTypeEnum fromDisplayName(String name) {
		TriggerTypeEnum value = values.get(name);
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
