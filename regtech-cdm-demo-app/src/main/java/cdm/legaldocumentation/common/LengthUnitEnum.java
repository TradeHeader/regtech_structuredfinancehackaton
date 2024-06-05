package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the length unit in the Resource type.
 * @version ${project.version}
 */
@RosettaEnum("LengthUnitEnum")
public enum LengthUnitEnum {

	@RosettaEnumValue(value = "Pages") PAGES("Pages"),
	
	@RosettaEnumValue(value = "TimeUnit") TIME_UNIT("TimeUnit")
;
	private static Map<String, LengthUnitEnum> values;
	static {
        Map<String, LengthUnitEnum> map = new ConcurrentHashMap<>();
		for (LengthUnitEnum instance : LengthUnitEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	LengthUnitEnum(String rosettaName) {
		this(rosettaName, null);
	}

	LengthUnitEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static LengthUnitEnum fromDisplayName(String name) {
		LengthUnitEnum value = values.get(name);
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
