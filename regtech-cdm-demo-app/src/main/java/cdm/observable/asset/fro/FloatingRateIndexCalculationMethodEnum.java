package cdm.observable.asset.fro;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 3rd level ISDA FRO category.
 * @version ${project.version}
 */
@RosettaEnum("FloatingRateIndexCalculationMethodEnum")
public enum FloatingRateIndexCalculationMethodEnum {

	/**
	 * A calculation methodology using the ISDA-defined OIS compounding formula.
	 */
	@RosettaEnumValue(value = "OISCompound", displayName = "OIS Compounding") OIS_COMPOUND("OISCompound", "OIS Compounding"),
	
	/**
	 * A calculation methodology using the arithmetic mean.
	 */
	@RosettaEnumValue(value = "Average", displayName = "Overnight Averaging") AVERAGE("Average", "Overnight Averaging"),
	
	@RosettaEnumValue(value = "Compounded", displayName = "Compounded Index") COMPOUNDED("Compounded", "Compounded Index"),
	
	@RosettaEnumValue(value = "AllInCompounded", displayName = "All-In Compounded Index") ALL_IN_COMPOUNDED("AllInCompounded", "All-In Compounded Index")
;
	private static Map<String, FloatingRateIndexCalculationMethodEnum> values;
	static {
        Map<String, FloatingRateIndexCalculationMethodEnum> map = new ConcurrentHashMap<>();
		for (FloatingRateIndexCalculationMethodEnum instance : FloatingRateIndexCalculationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FloatingRateIndexCalculationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FloatingRateIndexCalculationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FloatingRateIndexCalculationMethodEnum fromDisplayName(String name) {
		FloatingRateIndexCalculationMethodEnum value = values.get(name);
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
