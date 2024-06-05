package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the treatment of Non-Cash Dividends.
 * @version ${project.version}
 */
@RosettaEnum("NonCashDividendTreatmentEnum")
public enum NonCashDividendTreatmentEnum {

	/**
	 * The treatment of any non-cash dividend shall be determined in accordance with the Potential Adjustment Event provisions.
	 */
	@RosettaEnumValue(value = "PotentialAdjustmentEvent") POTENTIAL_ADJUSTMENT_EVENT("PotentialAdjustmentEvent"),
	
	/**
	 * Any non-cash dividend shall be treated as a Declared Cash Equivalent Dividend.
	 */
	@RosettaEnumValue(value = "CashEquivalent") CASH_EQUIVALENT("CashEquivalent")
;
	private static Map<String, NonCashDividendTreatmentEnum> values;
	static {
        Map<String, NonCashDividendTreatmentEnum> map = new ConcurrentHashMap<>();
		for (NonCashDividendTreatmentEnum instance : NonCashDividendTreatmentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	NonCashDividendTreatmentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	NonCashDividendTreatmentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static NonCashDividendTreatmentEnum fromDisplayName(String name) {
		NonCashDividendTreatmentEnum value = values.get(name);
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
