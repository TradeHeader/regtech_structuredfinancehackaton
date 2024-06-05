package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Used in conjunction with an exchange-based pricing source. Identifies a date source calendar from which the pricing dates and thus roll to the next contract will be based off (e.g. pricing is based on the NYMEX WTI First Nearby Futures Contract, if Future is chosen, the pricing will roll to the next futures contract on expiration, if ListedOption is chosen, the pricing will roll to the next futures contract on the Option expiration date which is three business days before the expiration of the NYMEX WTI futures contract.) Omitting this element will result in the default behavior expected with the pricing source described within the commodity element.
 * @version ${project.version}
 */
@RosettaEnum("RollSourceCalendarEnum")
public enum RollSourceCalendarEnum {

	@RosettaEnumValue(value = "ListedOption") LISTED_OPTION("ListedOption"),
	
	@RosettaEnumValue(value = "Future") FUTURE("Future")
;
	private static Map<String, RollSourceCalendarEnum> values;
	static {
        Map<String, RollSourceCalendarEnum> map = new ConcurrentHashMap<>();
		for (RollSourceCalendarEnum instance : RollSourceCalendarEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RollSourceCalendarEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RollSourceCalendarEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RollSourceCalendarEnum fromDisplayName(String name) {
		RollSourceCalendarEnum value = values.get(name);
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
