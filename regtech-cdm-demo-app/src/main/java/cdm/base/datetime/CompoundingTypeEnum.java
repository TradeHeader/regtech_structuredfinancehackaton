package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify how the compounding calculation is done
 * @version ${project.version}
 */
@RosettaEnum("CompoundingTypeEnum")
public enum CompoundingTypeEnum {

	/**
	 * Compounding is not applicable
	 */
	@RosettaEnumValue(value = "None") NONE("None"),
	
	/**
	 * Compounding is done only on business days, i.e. not compounded each day on weekends or holidays.
	 */
	@RosettaEnumValue(value = "Business") BUSINESS("Business"),
	
	/**
	 * Compounding is done on each calendar day.
	 */
	@RosettaEnumValue(value = "Calendar") CALENDAR("Calendar")
;
	private static Map<String, CompoundingTypeEnum> values;
	static {
        Map<String, CompoundingTypeEnum> map = new ConcurrentHashMap<>();
		for (CompoundingTypeEnum instance : CompoundingTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CompoundingTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CompoundingTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CompoundingTypeEnum fromDisplayName(String name) {
		CompoundingTypeEnum value = values.get(name);
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
