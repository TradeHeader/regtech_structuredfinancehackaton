package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Enumerated values to specify whether the price is expressed in absolute or relative terms.
 * @version ${project.version}
 */
@RosettaEnum("PriceExpressionEnum")
public enum PriceExpressionEnum {

	/**
	 * The price is expressed as an absolute amount.
	 */
	@RosettaEnumValue(value = "AbsoluteTerms") ABSOLUTE_TERMS("AbsoluteTerms"),
	
	/**
	 * The price is expressed in percentage of the notional amount.
	 */
	@RosettaEnumValue(value = "PercentageOfNotional") PERCENTAGE_OF_NOTIONAL("PercentageOfNotional"),
	
	/**
	 * Denotes a price expressed in percentage of face value with fractions which is used for quoting bonds, e.g. 101 3/8 indicates that the buyer will pay 101.375 of the face value.
	 */
	@RosettaEnumValue(value = "ParValueFraction") PAR_VALUE_FRACTION("ParValueFraction"),
	
	/**
	 * Denotes a price expressed per number of options.
	 */
	@RosettaEnumValue(value = "PerOption") PER_OPTION("PerOption")
;
	private static Map<String, PriceExpressionEnum> values;
	static {
        Map<String, PriceExpressionEnum> map = new ConcurrentHashMap<>();
		for (PriceExpressionEnum instance : PriceExpressionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PriceExpressionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PriceExpressionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PriceExpressionEnum fromDisplayName(String name) {
		PriceExpressionEnum value = values.get(name);
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
