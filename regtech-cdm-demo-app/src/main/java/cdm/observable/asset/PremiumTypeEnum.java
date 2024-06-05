package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the premium type for forward start options.
 * @version ${project.version}
 */
@RosettaEnum("PremiumTypeEnum")
public enum PremiumTypeEnum {

	@RosettaEnumValue(value = "PrePaid") PRE_PAID("PrePaid"),
	
	@RosettaEnumValue(value = "PostPaid") POST_PAID("PostPaid"),
	
	@RosettaEnumValue(value = "Variable") VARIABLE("Variable"),
	
	@RosettaEnumValue(value = "Fixed") FIXED("Fixed")
;
	private static Map<String, PremiumTypeEnum> values;
	static {
        Map<String, PremiumTypeEnum> map = new ConcurrentHashMap<>();
		for (PremiumTypeEnum instance : PremiumTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PremiumTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PremiumTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PremiumTypeEnum fromDisplayName(String name) {
		PremiumTypeEnum value = values.get(name);
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
