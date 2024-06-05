package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to identify where a concentration limit is applied.
 * @version ${project.version}
 */
@RosettaEnum("ConcentrationLimitTypeEnum")
public enum ConcentrationLimitTypeEnum {

	/**
	 * Specifies a limit on a single asset in the portfolio
	 */
	@RosettaEnumValue(value = "Asset") ASSET("Asset"),
	
	/**
	 * Specifies a limit on all cash valued in the base currency of the portfolio.
	 */
	@RosettaEnumValue(value = "BaseCurrencyEquivalent") BASE_CURRENCY_EQUIVALENT("BaseCurrencyEquivalent"),
	
	/**
	 * Specifies a limit on a single issuer in the portfolio.
	 */
	@RosettaEnumValue(value = "Issuer") ISSUER("Issuer"),
	
	/**
	 * Specifies a limit on a single exchange in the portfolio.
	 */
	@RosettaEnumValue(value = "PrimaryExchange") PRIMARY_EXCHANGE("PrimaryExchange"),
	
	/**
	 * Specifies a limit on a single industry sector in the portfolio.
	 */
	@RosettaEnumValue(value = "IndustrySector") INDUSTRY_SECTOR("IndustrySector"),
	
	/**
	 * Specifies a limit on a single issuer in the portfolio at the ultimate parent institution level.
	 */
	@RosettaEnumValue(value = "UltimateParentInstitution") ULTIMATE_PARENT_INSTITUTION("UltimateParentInstitution"),
	
	/**
	 * Specifies a limit of the issue compared to the outstanding amount of the asset on the market.
	 */
	@RosettaEnumValue(value = "IssueOutstandingAmount") ISSUE_OUTSTANDING_AMOUNT("IssueOutstandingAmount"),
	
	/**
	 * Specifies a limit of the issue calculated as a percentage of the market capitalisation of the asset on the market.
	 */
	@RosettaEnumValue(value = "MarketCapitalisation") MARKET_CAPITALISATION("MarketCapitalisation")
;
	private static Map<String, ConcentrationLimitTypeEnum> values;
	static {
        Map<String, ConcentrationLimitTypeEnum> map = new ConcurrentHashMap<>();
		for (ConcentrationLimitTypeEnum instance : ConcentrationLimitTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ConcentrationLimitTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ConcentrationLimitTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ConcentrationLimitTypeEnum fromDisplayName(String name) {
		ConcentrationLimitTypeEnum value = values.get(name);
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
