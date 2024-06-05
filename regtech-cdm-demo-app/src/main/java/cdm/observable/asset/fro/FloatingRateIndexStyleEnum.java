package cdm.observable.asset.fro;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Second level ISDA FRO category.
 * @version ${project.version}
 */
@RosettaEnum("FloatingRateIndexStyleEnum")
public enum FloatingRateIndexStyleEnum {

	/**
	 * An ISDA-defined calculated rate done using arithmetic averaging.
	 */
	@RosettaEnumValue(value = "AverageFRO", displayName = "Average FRO") AVERAGE_FRO("AverageFRO", "Average FRO"),
	
	/**
	 * An ISDA-defined calculated rate done using arithmetic averaging.
	 */
	@RosettaEnumValue(value = "CompoundedFRO", displayName = "Compounded FRO") COMPOUNDED_FRO("CompoundedFRO", "Compounded FRO"),
	
	/**
	 * A published index calculated using compounding.
	 */
	@RosettaEnumValue(value = "CompoundedIndex", displayName = "Compounded Index") COMPOUNDED_INDEX("CompoundedIndex", "Compounded Index"),
	
	/**
	 * A published index using a methodology defined by the publisher, e.g. S&amp;P 500.
	 */
	@RosettaEnumValue(value = "Index", displayName = "Index") INDEX("Index", "Index"),
	
	@RosettaEnumValue(value = "Other", displayName = "Other") OTHER("Other", "Other"),
	
	@RosettaEnumValue(value = "Overnight", displayName = "Overnight Rate") OVERNIGHT("Overnight", "Overnight Rate"),
	
	/**
	 *  A published rate computed using an averaging methodology.
	 */
	@RosettaEnumValue(value = "PublishedAverage", displayName = "Published Average Rate") PUBLISHED_AVERAGE("PublishedAverage", "Published Average Rate"),
	
	@RosettaEnumValue(value = "SpecifiedFormula", displayName = "Specified Formula") SPECIFIED_FORMULA("SpecifiedFormula", "Specified Formula"),
	
	/**
	 * A rate representing the market rate for swaps of a given maturity.
	 */
	@RosettaEnumValue(value = "SwapRate", displayName = "Swap Rate") SWAP_RATE("SwapRate", "Swap Rate"),
	
	/**
	 * A rate specified over a given term, such as a libor-type rate.
	 */
	@RosettaEnumValue(value = "TermRate", displayName = "Term Rate") TERM_RATE("TermRate", "Term Rate")
;
	private static Map<String, FloatingRateIndexStyleEnum> values;
	static {
        Map<String, FloatingRateIndexStyleEnum> map = new ConcurrentHashMap<>();
		for (FloatingRateIndexStyleEnum instance : FloatingRateIndexStyleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FloatingRateIndexStyleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FloatingRateIndexStyleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FloatingRateIndexStyleEnum fromDisplayName(String name) {
		FloatingRateIndexStyleEnum value = values.get(name);
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
