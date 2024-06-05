package cdm.observable.asset.calculatedrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * What calculation type is required, averaging or compounding. This enumeration is used to represent the definitions of modular calculated rates as described in the 2021 ISDA Definitions, section 7.
 * @version ${project.version}
 */
@RosettaEnum("CalculationMethodEnum")
public enum CalculationMethodEnum {

	/**
	 * Averaging, i.e. arithmetic averaging.
	 */
	@RosettaEnumValue(value = "Averaging") AVERAGING("Averaging"),
	
	/**
	 * Compounding, i.e. geometric averaging following an ISDA defined formula.
	 */
	@RosettaEnumValue(value = "Compounding") COMPOUNDING("Compounding"),
	
	/**
	 * A rate based on an index that is computed by a rate administrator.  The user is responsible for backing out the rate by applying a simple formula.
	 */
	@RosettaEnumValue(value = "CompoundedIndex") COMPOUNDED_INDEX("CompoundedIndex")
;
	private static Map<String, CalculationMethodEnum> values;
	static {
        Map<String, CalculationMethodEnum> map = new ConcurrentHashMap<>();
		for (CalculationMethodEnum instance : CalculationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CalculationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CalculationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CalculationMethodEnum fromDisplayName(String name) {
		CalculationMethodEnum value = values.get(name);
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
