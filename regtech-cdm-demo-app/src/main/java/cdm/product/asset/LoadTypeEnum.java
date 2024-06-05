package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies the load type of the delivery.
 * @version ${project.version}
 */
@RosettaEnum("LoadTypeEnum")
public enum LoadTypeEnum {

	/**
	 * Base load
	 */
	@RosettaEnumValue(value = "BaseLoad") BASE_LOAD("BaseLoad"),
	
	/**
	 * Peak load
	 */
	@RosettaEnumValue(value = "PeakLoad") PEAK_LOAD("PeakLoad"),
	
	/**
	 * Off-peak load
	 */
	@RosettaEnumValue(value = "OffPeak") OFF_PEAK("OffPeak"),
	
	/**
	 * Block Hours
	 */
	@RosettaEnumValue(value = "BlockHours") BLOCK_HOURS("BlockHours"),
	
	/**
	 * Shaped
	 */
	@RosettaEnumValue(value = "Shaped") SHAPED("Shaped"),
	
	/**
	 * Gas Day
	 */
	@RosettaEnumValue(value = "GasDay") GAS_DAY("GasDay"),
	
	/**
	 * Other
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other")
;
	private static Map<String, LoadTypeEnum> values;
	static {
        Map<String, LoadTypeEnum> map = new ConcurrentHashMap<>();
		for (LoadTypeEnum instance : LoadTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	LoadTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	LoadTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static LoadTypeEnum fromDisplayName(String name) {
		LoadTypeEnum value = values.get(name);
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
