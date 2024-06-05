package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Indicates the type of equity average trading volume (single) the highest amount on one exchange, or (consolidated) volumes across more than one exchange.
 * @version ${project.version}
 */
@RosettaEnum("AverageTradingVolumeMethodologyEnum")
public enum AverageTradingVolumeMethodologyEnum {

	/**
	 * Single, the highest amount on one exchange.
	 */
	@RosettaEnumValue(value = "Single") SINGLE("Single"),
	
	/**
	 * Consolidated volume across more than one exchange.
	 */
	@RosettaEnumValue(value = "Consolidated") CONSOLIDATED("Consolidated")
;
	private static Map<String, AverageTradingVolumeMethodologyEnum> values;
	static {
        Map<String, AverageTradingVolumeMethodologyEnum> map = new ConcurrentHashMap<>();
		for (AverageTradingVolumeMethodologyEnum instance : AverageTradingVolumeMethodologyEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AverageTradingVolumeMethodologyEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AverageTradingVolumeMethodologyEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AverageTradingVolumeMethodologyEnum fromDisplayName(String name) {
		AverageTradingVolumeMethodologyEnum value = values.get(name);
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
