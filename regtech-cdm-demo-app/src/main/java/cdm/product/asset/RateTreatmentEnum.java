package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the methods for converting rates from one basis to another.
 * @version ${project.version}
 */
@RosettaEnum("RateTreatmentEnum")
public enum RateTreatmentEnum {

	/**
	 * Bond Equivalent Yield. Per Annex to the 2000 ISDA Definitions (June 2000 Version), Section 7.3. Certain General Definitions Relating to Floating Rate Options, paragraph (g).
	 */
	@RosettaEnumValue(value = "BondEquivalentYield") BOND_EQUIVALENT_YIELD("BondEquivalentYield"),
	
	/**
	 * Money Market Yield. Per Annex to the 2000 ISDA Definitions (June 2000 Version), Section 7.3. Certain General Definitions Relating to Floating Rate Options, paragraph (h).
	 */
	@RosettaEnumValue(value = "MoneyMarketYield") MONEY_MARKET_YIELD("MoneyMarketYield")
;
	private static Map<String, RateTreatmentEnum> values;
	static {
        Map<String, RateTreatmentEnum> map = new ConcurrentHashMap<>();
		for (RateTreatmentEnum instance : RateTreatmentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RateTreatmentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RateTreatmentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RateTreatmentEnum fromDisplayName(String name) {
		RateTreatmentEnum value = values.get(name);
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
