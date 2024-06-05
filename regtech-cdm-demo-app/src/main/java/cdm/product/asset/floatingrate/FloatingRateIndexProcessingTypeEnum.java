package cdm.product.asset.floatingrate;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This enumeration provides guidance on how to process a given floating rate index.  It&#39;s based on the ISDA Floating Rate Index information, but transforms it into the specific categories needed for calculation 
 * @version ${project.version}
 */
@RosettaEnum("FloatingRateIndexProcessingTypeEnum")
public enum FloatingRateIndexProcessingTypeEnum {

	/**
	 * These values are just looked up from the screen and applied.
	 */
	@RosettaEnumValue(value = "Screen") SCREEN("Screen"),
	
	/**
	 * A published index calculated using compounding; the implied rate must be backed out.
	 */
	@RosettaEnumValue(value = "CompoundIndex") COMPOUND_INDEX("CompoundIndex"),
	
	/**
	 * These are calculated by the calculation agent based on a standard OIS FRO definition.
	 */
	@RosettaEnumValue(value = "OIS") OIS("OIS"),
	
	/**
	 * These are calculated by the calculation agent based on a standard overnight averaging FRO definition.
	 */
	@RosettaEnumValue(value = "OvernightAvg") OVERNIGHT_AVG("OvernightAvg"),
	
	/**
	 * These are calculated by the calculation agent based on deal-specific parameters (e.g. lookback compound based on an RFR).
	 */
	@RosettaEnumValue(value = "Modular") MODULAR("Modular"),
	
	/**
	 * These must be looked up using a manual process
	 */
	@RosettaEnumValue(value = "RefBanks") REF_BANKS("RefBanks")
;
	private static Map<String, FloatingRateIndexProcessingTypeEnum> values;
	static {
        Map<String, FloatingRateIndexProcessingTypeEnum> map = new ConcurrentHashMap<>();
		for (FloatingRateIndexProcessingTypeEnum instance : FloatingRateIndexProcessingTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FloatingRateIndexProcessingTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FloatingRateIndexProcessingTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FloatingRateIndexProcessingTypeEnum fromDisplayName(String name) {
		FloatingRateIndexProcessingTypeEnum value = values.get(name);
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
