package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the FpML asset class categorization.
 * @version ${project.version}
 */
@RosettaEnum("AssetClassEnum")
public enum AssetClassEnum {

	/**
	 * Commodity.
	 */
	@RosettaEnumValue(value = "Commodity") COMMODITY("Commodity"),
	
	/**
	 * Credit.
	 */
	@RosettaEnumValue(value = "Credit") CREDIT("Credit"),
	
	/**
	 * Equity.
	 */
	@RosettaEnumValue(value = "Equity") EQUITY("Equity"),
	
	/**
	 * ForeignExchange.
	 */
	@RosettaEnumValue(value = "ForeignExchange") FOREIGN_EXCHANGE("ForeignExchange"),
	
	/**
	 * InterestRate.
	 */
	@RosettaEnumValue(value = "InterestRate") INTEREST_RATE("InterestRate"),
	
	/**
	 * Money Market Assets like CP and CD.
	 */
	@RosettaEnumValue(value = "MoneyMarket") MONEY_MARKET("MoneyMarket")
;
	private static Map<String, AssetClassEnum> values;
	static {
        Map<String, AssetClassEnum> map = new ConcurrentHashMap<>();
		for (AssetClassEnum instance : AssetClassEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AssetClassEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AssetClassEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AssetClassEnum fromDisplayName(String name) {
		AssetClassEnum value = values.get(name);
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
