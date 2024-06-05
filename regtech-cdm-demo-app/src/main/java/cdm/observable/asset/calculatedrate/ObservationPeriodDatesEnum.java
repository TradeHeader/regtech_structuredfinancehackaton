package cdm.observable.asset.calculatedrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify whether rate calculations occur relative to the first or last day of a calculation period. Done in uppercase due to a bug in code generation. This enumeration is used to represent the definitions of modular calculated rates as described in the 2021 ISDA Definitions, section 7.
 * @version ${project.version}
 */
@RosettaEnum("ObservationPeriodDatesEnum")
public enum ObservationPeriodDatesEnum {

	/**
	 * Calculations occur relative to the first day of a calculation period.
	 */
	@RosettaEnumValue(value = "SetInAdvance") SET_IN_ADVANCE("SetInAdvance"),
	
	/**
	 * Calculations occur relative to the last day of a calculation period (set in arrears).
	 */
	@RosettaEnumValue(value = "Standard") STANDARD("Standard"),
	
	/**
	 * Calculations occur relative to a previously defined reset date, e.g. for a fallback rate.
	 */
	@RosettaEnumValue(value = "FixingDate") FIXING_DATE("FixingDate")
;
	private static Map<String, ObservationPeriodDatesEnum> values;
	static {
        Map<String, ObservationPeriodDatesEnum> map = new ConcurrentHashMap<>();
		for (ObservationPeriodDatesEnum instance : ObservationPeriodDatesEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ObservationPeriodDatesEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ObservationPeriodDatesEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ObservationPeriodDatesEnum fromDisplayName(String name) {
		ObservationPeriodDatesEnum value = values.get(name);
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
