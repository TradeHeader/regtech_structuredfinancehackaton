package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("DeliveryNearbyTypeEnum")
public enum DeliveryNearbyTypeEnum {

	/**
	 * Describes the reference contract as the one that pertains to the month-year of the calculation period. If used, the nearby count is expected to be 0.
	 */
	@RosettaEnumValue(value = "CalculationPeriod") CALCULATION_PERIOD("CalculationPeriod"),
	
	/**
	 * Specifies that the reference delivery date of the underlying Commodity shall be the expiration date of the futures contract in the nth nearby month.
	 */
	@RosettaEnumValue(value = "NearbyMonth") NEARBY_MONTH("NearbyMonth"),
	
	/**
	 * Specifies that the reference delivery date of the underlying Commodity shall be the expiration date of the futures contract in the nth nearby week.
	 */
	@RosettaEnumValue(value = "NearbyWeek") NEARBY_WEEK("NearbyWeek")
;
	private static Map<String, DeliveryNearbyTypeEnum> values;
	static {
        Map<String, DeliveryNearbyTypeEnum> map = new ConcurrentHashMap<>();
		for (DeliveryNearbyTypeEnum instance : DeliveryNearbyTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DeliveryNearbyTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DeliveryNearbyTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DeliveryNearbyTypeEnum fromDisplayName(String name) {
		DeliveryNearbyTypeEnum value = values.get(name);
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
