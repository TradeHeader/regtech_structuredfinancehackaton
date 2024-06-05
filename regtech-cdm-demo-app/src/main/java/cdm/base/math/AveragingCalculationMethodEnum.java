package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies enumerations for the type of averaging calculation.
 * @version ${project.version}
 */
@RosettaEnum("AveragingCalculationMethodEnum")
public enum AveragingCalculationMethodEnum {

	/**
	 * Refers to the calculation of an average by taking the sum of observations divided by the count of observations.
	 */
	@RosettaEnumValue(value = "Arithmetic") ARITHMETIC("Arithmetic"),
	
	/**
	 * Refers to the calculation of an average by taking the nth root of the product of n observations.
	 */
	@RosettaEnumValue(value = "Geometric") GEOMETRIC("Geometric"),
	
	/**
	 * Refers to the calculation of an average by taking the reciprocal of the arithmetic mean of the reciprocals of the observations.
	 */
	@RosettaEnumValue(value = "Harmonic") HARMONIC("Harmonic")
;
	private static Map<String, AveragingCalculationMethodEnum> values;
	static {
        Map<String, AveragingCalculationMethodEnum> map = new ConcurrentHashMap<>();
		for (AveragingCalculationMethodEnum instance : AveragingCalculationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AveragingCalculationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AveragingCalculationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AveragingCalculationMethodEnum fromDisplayName(String name) {
		AveragingCalculationMethodEnum value = values.get(name);
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
