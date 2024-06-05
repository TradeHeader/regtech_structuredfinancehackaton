package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify the reference source that determines the final settlement price of the option.
 * @version ${project.version}
 */
@RosettaEnum("OptionReferenceTypeEnum")
public enum OptionReferenceTypeEnum {

	/**
	 * Reference from the price of a future contract.
	 */
	@RosettaEnumValue(value = "Future") FUTURE("Future"),
	
	/**
	 * Reference from an underlyer spot price.
	 */
	@RosettaEnumValue(value = "Spot") SPOT("Spot")
;
	private static Map<String, OptionReferenceTypeEnum> values;
	static {
        Map<String, OptionReferenceTypeEnum> map = new ConcurrentHashMap<>();
		for (OptionReferenceTypeEnum instance : OptionReferenceTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	OptionReferenceTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	OptionReferenceTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static OptionReferenceTypeEnum fromDisplayName(String name) {
		OptionReferenceTypeEnum value = values.get(name);
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
