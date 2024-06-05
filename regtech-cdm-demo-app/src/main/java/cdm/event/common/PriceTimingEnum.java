package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("PriceTimingEnum")
public enum PriceTimingEnum {

	/**
	 * The last price anyone paid for a share of a product during the business hours of the exchange where the product is traded on a business day.
	 */
	@RosettaEnumValue(value = "ClosingPrice") CLOSING_PRICE("ClosingPrice"),
	
	/**
	 * The first price anyone paid for a share of a product during the business hours of the exchange where the product is traded on a business day.
	 */
	@RosettaEnumValue(value = "OpeningPrice") OPENING_PRICE("OpeningPrice")
;
	private static Map<String, PriceTimingEnum> values;
	static {
        Map<String, PriceTimingEnum> map = new ConcurrentHashMap<>();
		for (PriceTimingEnum instance : PriceTimingEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PriceTimingEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PriceTimingEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PriceTimingEnum fromDisplayName(String name) {
		PriceTimingEnum value = values.get(name);
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
