package cdm.observable.event;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the handling of an averaging date market disruption for an equity derivative transaction.
 * @version ${project.version}
 */
@RosettaEnum("MarketDisruptionEnum")
public enum MarketDisruptionEnum {

	/**
	 * As defined in section 6.7 paragraph (c) sub-paragraph (iii) of the ISDA 2002 Equity Derivative definitions.
	 */
	@RosettaEnumValue(value = "ModifiedPostponement") MODIFIED_POSTPONEMENT("ModifiedPostponement"),
	
	/**
	 * As defined in section 6.7 paragraph (c) sub-paragraph (i) of the ISDA 2002 Equity Derivative definitions.
	 */
	@RosettaEnumValue(value = "Omission") OMISSION("Omission"),
	
	/**
	 * As defined in section 6.7 paragraph (c) sub-paragraph (ii) of the ISDA 2002 Equity Derivative definitions.
	 */
	@RosettaEnumValue(value = "Postponement") POSTPONEMENT("Postponement")
;
	private static Map<String, MarketDisruptionEnum> values;
	static {
        Map<String, MarketDisruptionEnum> map = new ConcurrentHashMap<>();
		for (MarketDisruptionEnum instance : MarketDisruptionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MarketDisruptionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MarketDisruptionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MarketDisruptionEnum fromDisplayName(String name) {
		MarketDisruptionEnum value = values.get(name);
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
