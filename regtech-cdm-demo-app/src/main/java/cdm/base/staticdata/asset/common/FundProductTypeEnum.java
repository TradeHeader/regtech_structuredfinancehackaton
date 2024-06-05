package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the fund product type.
 * @version ${project.version}
 */
@RosettaEnum("FundProductTypeEnum")
public enum FundProductTypeEnum {

	/**
	 * Denotes a fund that invests only in highly liquid near-term instruments such as cash, cash equivalent securities, and high credit rating debt instruments with a short-term maturity.
	 */
	@RosettaEnumValue(value = "MoneyMarketFund") MONEY_MARKET_FUND("MoneyMarketFund"),
	
	/**
	 * Denotes an investment fund consisting of stocks, bonds, and/or other assets that is passively managed and traded on a stock exchange.
	 */
	@RosettaEnumValue(value = "ExchangeTradedFund") EXCHANGE_TRADED_FUND("ExchangeTradedFund"),
	
	/**
	 * Denotes an investment fund consisting of stocks, bonds, and/or other assets that is actively managed and can only be purchased or sold through the investment manager.
	 */
	@RosettaEnumValue(value = "MutualFund") MUTUAL_FUND("MutualFund"),
	
	/**
	 * Denotes a fund that is not an Exchange Traded Fund, Money Market Fund or Mutual Fund.
	 */
	@RosettaEnumValue(value = "OtherFund") OTHER_FUND("OtherFund")
;
	private static Map<String, FundProductTypeEnum> values;
	static {
        Map<String, FundProductTypeEnum> map = new ConcurrentHashMap<>();
		for (FundProductTypeEnum instance : FundProductTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FundProductTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FundProductTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FundProductTypeEnum fromDisplayName(String name) {
		FundProductTypeEnum value = values.get(name);
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
