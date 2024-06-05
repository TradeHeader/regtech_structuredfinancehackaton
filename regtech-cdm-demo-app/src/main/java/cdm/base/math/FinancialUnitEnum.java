package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides enumerated values for financial units, generally used in the context of defining quantities for securities.
 * @version ${project.version}
 */
@RosettaEnum("FinancialUnitEnum")
public enum FinancialUnitEnum {

	/**
	 * Denotes financial contracts, such as listed futures and options.
	 */
	@RosettaEnumValue(value = "Contract") CONTRACT("Contract"),
	
	/**
	 * Denotes a Contractual Product as defined in the CDM.  This unit type would be used when the price applies to the whole product, for example, in the case of a premium expressed as a cash amount.
	 */
	@RosettaEnumValue(value = "ContractualProduct") CONTRACTUAL_PRODUCT("ContractualProduct"),
	
	/**
	 * Denotes a price expressed in index points, e.g. for a stock index.
	 */
	@RosettaEnumValue(value = "IndexUnit") INDEX_UNIT("IndexUnit"),
	
	/**
	 * Denotes a log normal volatility, expressed in %/month, where the percentage is represented as a decimal. For example, 0.15 means a log-normal volatility of 15% per month.
	 */
	@RosettaEnumValue(value = "LogNormalVolatility") LOG_NORMAL_VOLATILITY("LogNormalVolatility"),
	
	/**
	 * Denotes the number of units of financial stock shares.
	 */
	@RosettaEnumValue(value = "Share") SHARE("Share"),
	
	/**
	 * Denotes a value (expressed in currency units) for a one day change in a valuation date, which is typically used for expressing sensitivity to the passage of time, also known as theta risk, or carry, or other names.
	 */
	@RosettaEnumValue(value = "ValuePerDay") VALUE_PER_DAY("ValuePerDay"),
	
	/**
	 * Denotes a value (expressed in currency units) per percent change in the underlying rate which is typically used for expressing sensitivity to volatility changes, also known as vega risk.
	 */
	@RosettaEnumValue(value = "ValuePerPercent") VALUE_PER_PERCENT("ValuePerPercent"),
	
	/**
	 * Denotes a quantity (expressed as a decimal value) represented the weight of a component in a basket.
	 */
	@RosettaEnumValue(value = "Weight") WEIGHT("Weight")
;
	private static Map<String, FinancialUnitEnum> values;
	static {
        Map<String, FinancialUnitEnum> map = new ConcurrentHashMap<>();
		for (FinancialUnitEnum instance : FinancialUnitEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FinancialUnitEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FinancialUnitEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FinancialUnitEnum fromDisplayName(String name) {
		FinancialUnitEnum value = values.get(name);
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
