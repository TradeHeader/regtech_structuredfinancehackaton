package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the different cash settlement methods for a product where cash settlement is applicable.
 * @version ${project.version}
 */
@RosettaEnum("CashSettlementMethodEnum")
public enum CashSettlementMethodEnum {

	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (a).
	 */
	@RosettaEnumValue(value = "CashPriceMethod") CASH_PRICE_METHOD("CashPriceMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (b).
	 */
	@RosettaEnumValue(value = "CashPriceAlternateMethod") CASH_PRICE_ALTERNATE_METHOD("CashPriceAlternateMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (c).
	 */
	@RosettaEnumValue(value = "ParYieldCurveAdjustedMethod") PAR_YIELD_CURVE_ADJUSTED_METHOD("ParYieldCurveAdjustedMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (d).
	 */
	@RosettaEnumValue(value = "ZeroCouponYieldAdjustedMethod") ZERO_COUPON_YIELD_ADJUSTED_METHOD("ZeroCouponYieldAdjustedMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (e).
	 */
	@RosettaEnumValue(value = "ParYieldCurveUnadjustedMethod") PAR_YIELD_CURVE_UNADJUSTED_METHOD("ParYieldCurveUnadjustedMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (f) (published in Supplement number 23).
	 */
	@RosettaEnumValue(value = "CrossCurrencyMethod") CROSS_CURRENCY_METHOD("CrossCurrencyMethod"),
	
	/**
	 * An ISDA defined cash settlement method (yield curve) used for the determination of the applicable cash settlement amount. The method is defined in the 2006 ISDA Definitions, Section 18.3. Cash Settlement Methods, paragraph (g) (published in Supplement number 28). The method is defined in the 2021 ISDA Definitions, section 18.2.6.
	 */
	@RosettaEnumValue(value = "CollateralizedCashPriceMethod") COLLATERALIZED_CASH_PRICE_METHOD("CollateralizedCashPriceMethod"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2021 ISDA Definitions, Section 18.2.1.
	 */
	@RosettaEnumValue(value = "MidMarketIndicativeQuotations") MID_MARKET_INDICATIVE_QUOTATIONS("MidMarketIndicativeQuotations"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2021 ISDA Definitions, Section 18.2.2.
	 */
	@RosettaEnumValue(value = "MidMarketIndicativeQuotationsAlternate") MID_MARKET_INDICATIVE_QUOTATIONS_ALTERNATE("MidMarketIndicativeQuotationsAlternate"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2021 ISDA Definitions, Section 18.2.3.
	 */
	@RosettaEnumValue(value = "MidMarketCalculationAgentDetermination") MID_MARKET_CALCULATION_AGENT_DETERMINATION("MidMarketCalculationAgentDetermination"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2021 ISDA Definitions, Section 18.2.4.
	 */
	@RosettaEnumValue(value = "ReplacementValueFirmQuotations") REPLACEMENT_VALUE_FIRM_QUOTATIONS("ReplacementValueFirmQuotations"),
	
	/**
	 * An ISDA defined cash settlement method used for the determination of the applicable cash settlement amount. The method is defined in the 2021 ISDA Definitions, Section 18.2.5
	 */
	@RosettaEnumValue(value = "ReplacementValueCalculationAgentDetermination") REPLACEMENT_VALUE_CALCULATION_AGENT_DETERMINATION("ReplacementValueCalculationAgentDetermination")
;
	private static Map<String, CashSettlementMethodEnum> values;
	static {
        Map<String, CashSettlementMethodEnum> map = new ConcurrentHashMap<>();
		for (CashSettlementMethodEnum instance : CashSettlementMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CashSettlementMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CashSettlementMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CashSettlementMethodEnum fromDisplayName(String name) {
		CashSettlementMethodEnum value = values.get(name);
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
