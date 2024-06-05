package cdm.product.common.schedule;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify whether resets occur relative to the first or last day of a calculation period.
 * @version ${project.version}
 */
@RosettaEnum("ResetRelativeToEnum")
public enum ResetRelativeToEnum {

	/**
	 * Resets occur relative to the first day of a calculation period.
	 */
	@RosettaEnumValue(value = "CalculationPeriodStartDate") CALCULATION_PERIOD_START_DATE("CalculationPeriodStartDate"),
	
	/**
	 * Resets occur relative to the last day of a calculation period.
	 */
	@RosettaEnumValue(value = "CalculationPeriodEndDate") CALCULATION_PERIOD_END_DATE("CalculationPeriodEndDate")
;
	private static Map<String, ResetRelativeToEnum> values;
	static {
        Map<String, ResetRelativeToEnum> map = new ConcurrentHashMap<>();
		for (ResetRelativeToEnum instance : ResetRelativeToEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ResetRelativeToEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ResetRelativeToEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ResetRelativeToEnum fromDisplayName(String name) {
		ResetRelativeToEnum value = values.get(name);
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
