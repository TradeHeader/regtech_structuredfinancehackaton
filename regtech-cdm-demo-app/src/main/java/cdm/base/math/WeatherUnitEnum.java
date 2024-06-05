package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides enumerated values for weather units, generally used in the context of defining quantities for commodities.
 * @version ${project.version}
 */
@RosettaEnum("WeatherUnitEnum")
public enum WeatherUnitEnum {

	/**
	 * Denotes Cooling Degree Days as a standard unit.
	 */
	@RosettaEnumValue(value = "CDD") CDD("CDD"),
	
	/**
	 * Denotes Critical Precipitation Day as a standard unit.
	 */
	@RosettaEnumValue(value = "CPD") CPD("CPD"),
	
	/**
	 * Heating Degree Day as a standard unit.
	 */
	@RosettaEnumValue(value = "HDD") HDD("HDD")
;
	private static Map<String, WeatherUnitEnum> values;
	static {
        Map<String, WeatherUnitEnum> map = new ConcurrentHashMap<>();
		for (WeatherUnitEnum instance : WeatherUnitEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	WeatherUnitEnum(String rosettaName) {
		this(rosettaName, null);
	}

	WeatherUnitEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static WeatherUnitEnum fromDisplayName(String name) {
		WeatherUnitEnum value = values.get(name);
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
