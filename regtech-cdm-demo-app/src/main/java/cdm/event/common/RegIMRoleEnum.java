package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration values to specify the role of the party in relation to a regulatory initial margin call.
 * @version ${project.version}
 */
@RosettaEnum("RegIMRoleEnum")
public enum RegIMRoleEnum {

	/**
	 * Indicates &#39;Pledgor&#39; party of initial margin call.
	 */
	@RosettaEnumValue(value = "Pledgor") PLEDGOR("Pledgor"),
	
	/**
	 * Indicates &#39;Secured&#39; party of initial margin call.
	 */
	@RosettaEnumValue(value = "Secured") SECURED("Secured")
;
	private static Map<String, RegIMRoleEnum> values;
	static {
        Map<String, RegIMRoleEnum> map = new ConcurrentHashMap<>();
		for (RegIMRoleEnum instance : RegIMRoleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RegIMRoleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RegIMRoleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RegIMRoleEnum fromDisplayName(String name) {
		RegIMRoleEnum value = values.get(name);
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
