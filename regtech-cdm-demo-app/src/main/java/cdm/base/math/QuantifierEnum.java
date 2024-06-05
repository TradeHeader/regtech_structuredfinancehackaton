package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify a logical quantification, i.e. either All or Any.
 * @version ${project.version}
 */
@RosettaEnum("QuantifierEnum")
public enum QuantifierEnum {

	/**
	 * Specifies that the condition in the scope of the quantifier is true of every member of the domain i.e. every one of the elements in scope.
	 */
	@RosettaEnumValue(value = "All") ALL("All"),
	
	/**
	 * Specifies that the condition in the scope of the quantifier is true of at least one member of the domain i.e. one or more of the elements in scope.
	 */
	@RosettaEnumValue(value = "Any") ANY("Any")
;
	private static Map<String, QuantifierEnum> values;
	static {
        Map<String, QuantifierEnum> map = new ConcurrentHashMap<>();
		for (QuantifierEnum instance : QuantifierEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	QuantifierEnum(String rosettaName) {
		this(rosettaName, null);
	}

	QuantifierEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static QuantifierEnum fromDisplayName(String name) {
		QuantifierEnum value = values.get(name);
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
