package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration list to identify the settlement status of the collateral.
 * @version ${project.version}
 */
@RosettaEnum("CollateralStatusEnum")
public enum CollateralStatusEnum {

	/**
	 * Indicates the collateral balance amount in full, inclusive of any pre-agreed collateral positions in transit for settlement.
	 */
	@RosettaEnumValue(value = "FullAmount") FULL_AMOUNT("FullAmount"),
	
	/**
	 * Indicates the collateral is settled and not an in transit pre-agreed collateral amount/s.
	 */
	@RosettaEnumValue(value = "SettledAmount") SETTLED_AMOUNT("SettledAmount"),
	
	/**
	 * Indicates collateral amount in transit settlement cycle only, excluding settled collateral amount/s.
	 */
	@RosettaEnumValue(value = "InTransitAmount") IN_TRANSIT_AMOUNT("InTransitAmount")
;
	private static Map<String, CollateralStatusEnum> values;
	static {
        Map<String, CollateralStatusEnum> map = new ConcurrentHashMap<>();
		for (CollateralStatusEnum instance : CollateralStatusEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CollateralStatusEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CollateralStatusEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CollateralStatusEnum fromDisplayName(String name) {
		CollateralStatusEnum value = values.get(name);
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
