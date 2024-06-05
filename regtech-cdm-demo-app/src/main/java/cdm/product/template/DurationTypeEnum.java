package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies the duration type of the Security Lending transaction. e.g. Open or Term.
 * @version ${project.version}
 */
@RosettaEnum("DurationTypeEnum")
public enum DurationTypeEnum {

	/**
	 * Specifies a trade with a termination date.
	 */
	@RosettaEnumValue(value = "Term") TERM("Term"),
	
	/**
	 * Specifies a trade with no termination date.
	 */
	@RosettaEnumValue(value = "Open") OPEN("Open"),
	
	/**
	 * Specifies a trade where the term date is extended by a pre-determined period until a notice is serviced. Once the notice is served, the trade will not be reset again and goes to term.
	 */
	@RosettaEnumValue(value = "Evergreen") EVERGREEN("Evergreen")
;
	private static Map<String, DurationTypeEnum> values;
	static {
        Map<String, DurationTypeEnum> map = new ConcurrentHashMap<>();
		for (DurationTypeEnum instance : DurationTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DurationTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DurationTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DurationTypeEnum fromDisplayName(String name) {
		DurationTypeEnum value = values.get(name);
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
