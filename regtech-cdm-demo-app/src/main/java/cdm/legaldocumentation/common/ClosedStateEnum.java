package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify what led to the contract or execution closure.
 * @version ${project.version}
 */
@RosettaEnum("ClosedStateEnum")
public enum ClosedStateEnum {

	/**
	 * The execution or contract has been allocated.
	 */
	@RosettaEnumValue(value = "Allocated") ALLOCATED("Allocated"),
	
	/**
	 * The execution or contract has been cancelled.
	 */
	@RosettaEnumValue(value = "Cancelled") CANCELLED("Cancelled"),
	
	/**
	 * The (option) contract has been exercised.
	 */
	@RosettaEnumValue(value = "Exercised") EXERCISED("Exercised"),
	
	/**
	 * The (option) contract has expired without being exercised.
	 */
	@RosettaEnumValue(value = "Expired") EXPIRED("Expired"),
	
	/**
	 * The contract has reached its contractual termination date.
	 */
	@RosettaEnumValue(value = "Matured") MATURED("Matured"),
	
	/**
	 * The contract has been novated. This state applies to the stepped-out contract component of the novation event.
	 */
	@RosettaEnumValue(value = "Novated") NOVATED("Novated"),
	
	/**
	 * The contract has been subject of an early termination event.
	 */
	@RosettaEnumValue(value = "Terminated") TERMINATED("Terminated")
;
	private static Map<String, ClosedStateEnum> values;
	static {
        Map<String, ClosedStateEnum> map = new ConcurrentHashMap<>();
		for (ClosedStateEnum instance : ClosedStateEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ClosedStateEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ClosedStateEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ClosedStateEnum fromDisplayName(String name) {
		ClosedStateEnum value = values.get(name);
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
