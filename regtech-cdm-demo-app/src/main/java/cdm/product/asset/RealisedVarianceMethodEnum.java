package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The contract specifies which price must satisfy the boundary condition.  Used for variance, volatility and correlation caps and floors.
 * @version ${project.version}
 */
@RosettaEnum("RealisedVarianceMethodEnum")
public enum RealisedVarianceMethodEnum {

	/**
	 * For a return on day T, the observed price on T-1 must be in range.
	 */
	@RosettaEnumValue(value = "Previous") PREVIOUS("Previous"),
	
	/**
	 * For a return on day T, the observed price on T must be in range.
	 */
	@RosettaEnumValue(value = "Last") LAST("Last"),
	
	/**
	 * For a return on day T, the observed prices on both T and T-1 must be in range
	 */
	@RosettaEnumValue(value = "Both") BOTH("Both")
;
	private static Map<String, RealisedVarianceMethodEnum> values;
	static {
        Map<String, RealisedVarianceMethodEnum> map = new ConcurrentHashMap<>();
		for (RealisedVarianceMethodEnum instance : RealisedVarianceMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RealisedVarianceMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RealisedVarianceMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RealisedVarianceMethodEnum fromDisplayName(String name) {
		RealisedVarianceMethodEnum value = values.get(name);
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
