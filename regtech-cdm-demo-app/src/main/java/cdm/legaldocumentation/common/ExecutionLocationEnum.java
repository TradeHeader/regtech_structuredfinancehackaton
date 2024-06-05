package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the Execution Location of a Security Agreement
 * @version ${project.version}
 */
@RosettaEnum("ExecutionLocationEnum")
public enum ExecutionLocationEnum {

	/**
	 * The Agreement was executed outside of Belgium
	 */
	@RosettaEnumValue(value = "ExecutedOutsideBelgium") EXECUTED_OUTSIDE_BELGIUM("ExecutedOutsideBelgium"),
	
	/**
	 * The Agreement was executed outside of Belgium
	 */
	@RosettaEnumValue(value = "ExecutedInBelgium") EXECUTED_IN_BELGIUM("ExecutedInBelgium"),
	
	/**
	 * An alternative approach is described in the document as follows.
	 */
	@RosettaEnumValue(value = "OtherLocation") OTHER_LOCATION("OtherLocation")
;
	private static Map<String, ExecutionLocationEnum> values;
	static {
        Map<String, ExecutionLocationEnum> map = new ConcurrentHashMap<>();
		for (ExecutionLocationEnum instance : ExecutionLocationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ExecutionLocationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ExecutionLocationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ExecutionLocationEnum fromDisplayName(String name) {
		ExecutionLocationEnum value = values.get(name);
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
