package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("CompareOp")
public enum CompareOp {

	@RosettaEnumValue(value = "GreaterThan") GREATER_THAN("GreaterThan"),
	
	@RosettaEnumValue(value = "GreaterThanOrEquals") GREATER_THAN_OR_EQUALS("GreaterThanOrEquals"),
	
	@RosettaEnumValue(value = "Equals") EQUALS("Equals"),
	
	@RosettaEnumValue(value = "LessThanOrEquals") LESS_THAN_OR_EQUALS("LessThanOrEquals"),
	
	@RosettaEnumValue(value = "LessThan") LESS_THAN("LessThan")
;
	private static Map<String, CompareOp> values;
	static {
        Map<String, CompareOp> map = new ConcurrentHashMap<>();
		for (CompareOp instance : CompareOp.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CompareOp(String rosettaName) {
		this(rosettaName, null);
	}

	CompareOp(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CompareOp fromDisplayName(String name) {
		CompareOp value = values.get(name);
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
