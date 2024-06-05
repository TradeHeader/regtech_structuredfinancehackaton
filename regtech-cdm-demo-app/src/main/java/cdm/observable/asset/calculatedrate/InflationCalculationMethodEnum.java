package cdm.observable.asset.calculatedrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Indicates how to use the inflation index to calculate the payment (e.g. Ratio, Return, Spread). Added for Inflation Asset Swap
 * @version ${project.version}
 */
@RosettaEnum("InflationCalculationMethodEnum")
public enum InflationCalculationMethodEnum {

	/**
	 * (Inflation Index Final / Inflation Index Base). Inflation Index Final is inflation index for Reference Month that is the Lag number of months prior to Payment Date (subject to interpolation). Inflation Index Base subject to the Calculation Style. Used in inflation asset swaps to calculate the inflation coupons and principal exchange.
	 */
	@RosettaEnumValue(value = "Ratio") RATIO("Ratio"),
	
	/**
	 * (Inflation Index Final / Inflation Index Base -1). Inflation Index Final is the inflation index for Reference Month that is the Lag number of months prior to Payment Date (subject to interp). Inflation Index Base subject to the Calculation Style. Used in market standard ZC Inflation swaps.
	 */
	@RosettaEnumValue(value = "Return") RETURN("Return"),
	
	/**
	 * Inflation Index Final - Inflation Index Base). Inflation Index Final is Index for Ref month the Lag months prior to Payment Date (subject to interp). Inflation Index Base subject to the Calculation Style. Typically used for fixing locks.
	 */
	@RosettaEnumValue(value = "Spread") SPREAD("Spread")
;
	private static Map<String, InflationCalculationMethodEnum> values;
	static {
        Map<String, InflationCalculationMethodEnum> map = new ConcurrentHashMap<>();
		for (InflationCalculationMethodEnum instance : InflationCalculationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InflationCalculationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InflationCalculationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InflationCalculationMethodEnum fromDisplayName(String name) {
		InflationCalculationMethodEnum value = values.get(name);
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
