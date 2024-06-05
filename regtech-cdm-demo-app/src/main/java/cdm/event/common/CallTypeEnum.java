package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration values that indicate the intended status of message type, such as expected call, notification of a call or a margin call.
 * @version ${project.version}
 */
@RosettaEnum("CallTypeEnum")
public enum CallTypeEnum {

	/**
	 * Identifies an actionable Margin Call.
	 */
	@RosettaEnumValue(value = "MarginCall") MARGIN_CALL("MarginCall"),
	
	/**
	 * Identifies a notification of a Margin Call for legal obligation to notify other party to initiate a margin call when notifying party is calculation or valuation agent.
	 */
	@RosettaEnumValue(value = "Notification") NOTIFICATION("Notification"),
	
	/**
	 * Identifies an expected Margin Call instruction for either party to notify the other or their service provider of an expected margin call movement.
	 */
	@RosettaEnumValue(value = "ExpectedCall") EXPECTED_CALL("ExpectedCall")
;
	private static Map<String, CallTypeEnum> values;
	static {
        Map<String, CallTypeEnum> map = new ConcurrentHashMap<>();
		for (CallTypeEnum instance : CallTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CallTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CallTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CallTypeEnum fromDisplayName(String name) {
		CallTypeEnum value = values.get(name);
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
