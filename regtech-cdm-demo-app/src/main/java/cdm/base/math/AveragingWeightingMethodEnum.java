package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the method of calculation to be used when averaging rates. Per ISDA 2000 Definitions, Section 6.2. Certain Definitions Relating to Floating Amounts.
 * @version ${project.version}
 */
@RosettaEnum("AveragingWeightingMethodEnum")
public enum AveragingWeightingMethodEnum {

	/**
	 * The arithmetic mean of the relevant rates for each reset date.
	 */
	@RosettaEnumValue(value = "Unweighted") UNWEIGHTED("Unweighted"),
	
	/**
	 * The arithmetic mean of the relevant rates in effect for each day in a calculation period calculated by multiplying each relevant rate by the number of days such relevant rate is in effect, determining the sum of such products and dividing such sum by the number of days in the calculation period.
	 */
	@RosettaEnumValue(value = "Weighted") WEIGHTED("Weighted")
;
	private static Map<String, AveragingWeightingMethodEnum> values;
	static {
        Map<String, AveragingWeightingMethodEnum> map = new ConcurrentHashMap<>();
		for (AveragingWeightingMethodEnum instance : AveragingWeightingMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AveragingWeightingMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AveragingWeightingMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AveragingWeightingMethodEnum fromDisplayName(String name) {
		AveragingWeightingMethodEnum value = values.get(name);
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
