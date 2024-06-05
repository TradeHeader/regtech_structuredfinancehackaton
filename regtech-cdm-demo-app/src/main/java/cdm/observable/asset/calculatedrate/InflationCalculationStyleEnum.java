package cdm.observable.asset.calculatedrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Indicates the style of how the inflation index calculates the payment (e.g. YearOnYear, ZeroCoupon).
 * @version ${project.version}
 */
@RosettaEnum("InflationCalculationStyleEnum")
public enum InflationCalculationStyleEnum {

	/**
	 * YearOnYear means Inflation Index Base is the inflation index for Reference Month that is 12 months prior to Inflation Index Final (subject to interpolation). Inflation Index Base is cashflow dependent.
	 */
	@RosettaEnumValue(value = "YearOnYear") YEAR_ON_YEAR("YearOnYear"),
	
	/**
	 * ZeroCoupon means Inflation Index Base used in the CalculationMethod is the inflation index for the Reference Month that is the lag number of months prior to Effective Date in the case of a swap, or Bond Interest Accrual Date in the case of an Asset Swap (subject to interpolation). Inflation Index Base has the same value for each inflation cashflow and Principal Exchange calculation within the trade.
	 */
	@RosettaEnumValue(value = "ZeroCoupon") ZERO_COUPON("ZeroCoupon")
;
	private static Map<String, InflationCalculationStyleEnum> values;
	static {
        Map<String, InflationCalculationStyleEnum> map = new ConcurrentHashMap<>();
		for (InflationCalculationStyleEnum instance : InflationCalculationStyleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InflationCalculationStyleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InflationCalculationStyleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InflationCalculationStyleEnum fromDisplayName(String name) {
		InflationCalculationStyleEnum value = values.get(name);
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
