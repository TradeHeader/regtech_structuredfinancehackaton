package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration values to define the response type to a margin call.
 * @version ${project.version}
 */
@RosettaEnum("MarginCallResponseTypeEnum")
public enum MarginCallResponseTypeEnum {

	/**
	 * Specifies a &#39;Full Agreement&#39; to Margin Call.
	 */
	@RosettaEnumValue(value = "AgreeinFull") AGREEIN_FULL("AgreeinFull"),
	
	/**
	 * Specifies a &#39;Partial agreement&#39; to Margin Call.
	 */
	@RosettaEnumValue(value = "PartiallyAgree") PARTIALLY_AGREE("PartiallyAgree"),
	
	/**
	 * Specifies a &#39;Full Dispute&#39; to a Margin call.
	 */
	@RosettaEnumValue(value = "Dispute") DISPUTE("Dispute")
;
	private static Map<String, MarginCallResponseTypeEnum> values;
	static {
        Map<String, MarginCallResponseTypeEnum> map = new ConcurrentHashMap<>();
		for (MarginCallResponseTypeEnum instance : MarginCallResponseTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MarginCallResponseTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MarginCallResponseTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MarginCallResponseTypeEnum fromDisplayName(String name) {
		MarginCallResponseTypeEnum value = values.get(name);
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
