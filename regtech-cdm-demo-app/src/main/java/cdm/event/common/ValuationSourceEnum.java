package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Source for the valuation of the transaction by the valuation party.
 * @version ${project.version}
 */
@RosettaEnum("ValuationSourceEnum")
public enum ValuationSourceEnum {

	/**
	 * Central Counterparty&#39;s Valuation
	 */
	@RosettaEnumValue(value = "CentralCounterparty") CENTRAL_COUNTERPARTY("CentralCounterparty")
;
	private static Map<String, ValuationSourceEnum> values;
	static {
        Map<String, ValuationSourceEnum> map = new ConcurrentHashMap<>();
		for (ValuationSourceEnum instance : ValuationSourceEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ValuationSourceEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ValuationSourceEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ValuationSourceEnum fromDisplayName(String name) {
		ValuationSourceEnum value = values.get(name);
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
