package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Method by which spread is calculated. For example on an asset swap: &#39;ParPar&#39; or &#39;Proceeds&#39; may be the method indicated.
 * @version ${project.version}
 */
@RosettaEnum("SpreadCalculationMethodEnum")
public enum SpreadCalculationMethodEnum {

	@RosettaEnumValue(value = "ParPar") PAR_PAR("ParPar"),
	
	@RosettaEnumValue(value = "Proceeds") PROCEEDS("Proceeds")
;
	private static Map<String, SpreadCalculationMethodEnum> values;
	static {
        Map<String, SpreadCalculationMethodEnum> map = new ConcurrentHashMap<>();
		for (SpreadCalculationMethodEnum instance : SpreadCalculationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SpreadCalculationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SpreadCalculationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SpreadCalculationMethodEnum fromDisplayName(String name) {
		SpreadCalculationMethodEnum value = values.get(name);
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
