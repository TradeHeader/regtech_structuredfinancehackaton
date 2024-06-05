package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the interest shortfall cap, applicable to mortgage derivatives.
 * @version ${project.version}
 */
@RosettaEnum("InterestShortfallCapEnum")
public enum InterestShortfallCapEnum {

	@RosettaEnumValue(value = "Fixed") FIXED("Fixed"),
	
	@RosettaEnumValue(value = "Variable") VARIABLE("Variable")
;
	private static Map<String, InterestShortfallCapEnum> values;
	static {
        Map<String, InterestShortfallCapEnum> map = new ConcurrentHashMap<>();
		for (InterestShortfallCapEnum instance : InterestShortfallCapEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InterestShortfallCapEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InterestShortfallCapEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InterestShortfallCapEnum fromDisplayName(String name) {
		InterestShortfallCapEnum value = values.get(name);
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
