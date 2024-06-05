package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Enumeration for the different types of affirmation status.
 * @version ${project.version}
 */
@RosettaEnum("AffirmationStatusEnum")
public enum AffirmationStatusEnum {

	@RosettaEnumValue(value = "Affirmed") AFFIRMED("Affirmed"),
	
	@RosettaEnumValue(value = "Unaffirmed") UNAFFIRMED("Unaffirmed")
;
	private static Map<String, AffirmationStatusEnum> values;
	static {
        Map<String, AffirmationStatusEnum> map = new ConcurrentHashMap<>();
		for (AffirmationStatusEnum instance : AffirmationStatusEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AffirmationStatusEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AffirmationStatusEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AffirmationStatusEnum fromDisplayName(String name) {
		AffirmationStatusEnum value = values.get(name);
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
