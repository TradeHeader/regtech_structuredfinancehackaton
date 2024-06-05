package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the interpolation method, e.g. linear.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/interpolation-method"
 *
 * Provision 
 *
 */
@RosettaEnum("InterpolationMethodEnum")
public enum InterpolationMethodEnum {

	/**
	 * Linear Interpolation applicable.
	 */
	@RosettaEnumValue(value = "Linear") LINEAR("Linear"),
	
	/**
	 * Linear Interpolation applicable.
	 */
	@RosettaEnumValue(value = "LinearZeroYield") LINEAR_ZERO_YIELD("LinearZeroYield"),
	
	/**
	 * No Interpolation applicable.
	 */
	@RosettaEnumValue(value = "None") NONE("None")
;
	private static Map<String, InterpolationMethodEnum> values;
	static {
        Map<String, InterpolationMethodEnum> map = new ConcurrentHashMap<>();
		for (InterpolationMethodEnum instance : InterpolationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InterpolationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InterpolationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InterpolationMethodEnum fromDisplayName(String name) {
		InterpolationMethodEnum value = values.get(name);
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
