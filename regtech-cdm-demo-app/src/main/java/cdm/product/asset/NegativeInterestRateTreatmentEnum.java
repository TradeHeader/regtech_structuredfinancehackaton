package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the method of calculating payment obligations when a floating rate is negative (either due to a quoted negative floating rate or by operation of a spread that is subtracted from the floating rate).
 * @version ${project.version}
 */
@RosettaEnum("NegativeInterestRateTreatmentEnum")
public enum NegativeInterestRateTreatmentEnum {

	/**
	 * Negative Interest Rate Method. Per 2000 ISDA Definitions, Section 6.4 Negative Interest Rates, paragraphs (b) and (c).
	 */
	@RosettaEnumValue(value = "NegativeInterestRateMethod") NEGATIVE_INTEREST_RATE_METHOD("NegativeInterestRateMethod"),
	
	/**
	 * Zero Interest Rate Method. Per 2000 ISDA Definitions, Section 6.4. Negative Interest Rates, paragraphs (d) and (e).
	 */
	@RosettaEnumValue(value = "ZeroInterestRateMethod") ZERO_INTEREST_RATE_METHOD("ZeroInterestRateMethod"),
	
	/**
	 * Per 2021 ISDA Definitions section 6.8.6
	 */
	@RosettaEnumValue(value = "ZeroInterestRateExcludingSpreadMethod") ZERO_INTEREST_RATE_EXCLUDING_SPREAD_METHOD("ZeroInterestRateExcludingSpreadMethod")
;
	private static Map<String, NegativeInterestRateTreatmentEnum> values;
	static {
        Map<String, NegativeInterestRateTreatmentEnum> map = new ConcurrentHashMap<>();
		for (NegativeInterestRateTreatmentEnum instance : NegativeInterestRateTreatmentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	NegativeInterestRateTreatmentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	NegativeInterestRateTreatmentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static NegativeInterestRateTreatmentEnum fromDisplayName(String name) {
		NegativeInterestRateTreatmentEnum value = values.get(name);
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
