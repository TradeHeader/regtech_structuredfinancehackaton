package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 2002 ISDA Equity Derivatives Definitions: First Period, Second Period |
 * @version ${project.version}
 */
@RosettaEnum("DividendPeriodEnum")
public enum DividendPeriodEnum {

	/**
	 * 2002 ISDA Equity Derivatives Definitions: First Period means each period from, and including, one Cash Settlement Payment Date or Settlement Date, as the case may be, to, but excluding, the next following Cash Settlement Payment Date or Settlement Date, as the case may be, except that (i) the initial Dividend Period will commence on, and include, the Clearance System Business Day that is one Settlement Cycle following the Trade Date and (ii) the final Dividend Period will end on, but exclude, the final Cash Settlement Payment Date or Settlement Date, as the case may be.
	 */
	@RosettaEnumValue(value = "FirstPeriod") FIRST_PERIOD("FirstPeriod"),
	
	/**
	 * 2002 ISDA Equity Derivatives Definitions: Second Period means each period from, but excluding, one Valuation Date to, and including, the next Valuation Date, except that (i) the initial Dividend Period will commence on, but exclude, the Trade Date and (ii) the final Dividend Period will end on, and include, the final Valuation Date or, in respect of a Physically-settled Forward Transaction to which Variable Obligation is not applicable, the date that is one Settlement Cycle prior to the Settlement Date.
	 */
	@RosettaEnumValue(value = "SecondPeriod") SECOND_PERIOD("SecondPeriod")
;
	private static Map<String, DividendPeriodEnum> values;
	static {
        Map<String, DividendPeriodEnum> map = new ConcurrentHashMap<>();
		for (DividendPeriodEnum instance : DividendPeriodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DividendPeriodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DividendPeriodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DividendPeriodEnum fromDisplayName(String name) {
		DividendPeriodEnum value = values.get(name);
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
