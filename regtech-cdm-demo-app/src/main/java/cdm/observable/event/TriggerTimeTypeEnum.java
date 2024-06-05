package cdm.observable.event;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the time of day which would be considered for valuing the knock event.
 * @version ${project.version}
 */
@RosettaEnum("TriggerTimeTypeEnum")
public enum TriggerTimeTypeEnum {

	/**
	 * The close of trading on a day would be considered for valuation.
	 */
	@RosettaEnumValue(value = "Closing") CLOSING("Closing"),
	
	/**
	 * At any time during the Knock Determination period (continuous barrier).
	 */
	@RosettaEnumValue(value = "Anytime") ANYTIME("Anytime")
;
	private static Map<String, TriggerTimeTypeEnum> values;
	static {
        Map<String, TriggerTimeTypeEnum> map = new ConcurrentHashMap<>();
		for (TriggerTimeTypeEnum instance : TriggerTimeTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TriggerTimeTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TriggerTimeTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TriggerTimeTypeEnum fromDisplayName(String name) {
		TriggerTimeTypeEnum value = values.get(name);
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
