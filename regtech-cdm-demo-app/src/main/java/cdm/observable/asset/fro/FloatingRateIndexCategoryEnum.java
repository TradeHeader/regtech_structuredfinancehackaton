package cdm.observable.asset.fro;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Top level ISDA FRO category.
 * @version ${project.version}
 */
@RosettaEnum("FloatingRateIndexCategoryEnum")
public enum FloatingRateIndexCategoryEnum {

	/**
	 * The rate is observed directly from a screen.
	 */
	@RosettaEnumValue(value = "ScreenRate", displayName = "Screen Rate") SCREEN_RATE("ScreenRate", "Screen Rate"),
	
	/**
	 * The rate is calculated by the calculation agents from multiple observations.
	 */
	@RosettaEnumValue(value = "Calculated", displayName = "Calculated Rate") CALCULATED("Calculated", "Calculated Rate"),
	
	/**
	 * The rate is obtained by polling several other banks.
	 */
	@RosettaEnumValue(value = "ReferenceBanks", displayName = "Reference Banks Rate") REFERENCE_BANKS("ReferenceBanks", "Reference Banks Rate")
;
	private static Map<String, FloatingRateIndexCategoryEnum> values;
	static {
        Map<String, FloatingRateIndexCategoryEnum> map = new ConcurrentHashMap<>();
		for (FloatingRateIndexCategoryEnum instance : FloatingRateIndexCategoryEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FloatingRateIndexCategoryEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FloatingRateIndexCategoryEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FloatingRateIndexCategoryEnum fromDisplayName(String name) {
		FloatingRateIndexCategoryEnum value = values.get(name);
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
