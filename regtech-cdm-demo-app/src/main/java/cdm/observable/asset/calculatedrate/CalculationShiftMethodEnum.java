package cdm.observable.asset.calculatedrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  the specific calculation method, e.g. lookback. This enumeration is used to represent the definitions of modular calculated rates as described in the 2021 ISDA Definitions, section 7.
 * @version ${project.version}
 */
@RosettaEnum("CalculationShiftMethodEnum")
public enum CalculationShiftMethodEnum {

	/**
	 * Calculations and weighting are done with respect to the calculation period, but observations are shifted back by several days.
	 */
	@RosettaEnumValue(value = "Lookback") LOOKBACK("Lookback"),
	
	/**
	 * the observation period is shifted by several days prior to rate setting, and weightings are done with respect to the obseration period.
	 */
	@RosettaEnumValue(value = "ObservationPeriodShift") OBSERVATION_PERIOD_SHIFT("ObservationPeriodShift"),
	
	/**
	 * Calculations cut the rate off several business days prior to rate setting (Lockout).
	 */
	@RosettaEnumValue(value = "RateCutOff") RATE_CUT_OFF("RateCutOff"),
	
	/**
	 * calculations occur without any shifting, e.g. OIS Compounding/Basic Averaging style.
	 */
	@RosettaEnumValue(value = "NoShift") NO_SHIFT("NoShift")
;
	private static Map<String, CalculationShiftMethodEnum> values;
	static {
        Map<String, CalculationShiftMethodEnum> map = new ConcurrentHashMap<>();
		for (CalculationShiftMethodEnum instance : CalculationShiftMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CalculationShiftMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CalculationShiftMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CalculationShiftMethodEnum fromDisplayName(String name) {
		CalculationShiftMethodEnum value = values.get(name);
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
