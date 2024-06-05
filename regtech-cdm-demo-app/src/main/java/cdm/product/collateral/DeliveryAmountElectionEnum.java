package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the application of Interest Amount with respect to the Delivery Amount through standard language.
 * @version ${project.version}
 */
@RosettaEnum("DeliveryAmountElectionEnum")
public enum DeliveryAmountElectionEnum {

	/**
	 * The delivery only includes `Transfer on last Local Business Day.
	 */
	@RosettaEnumValue(value = "LastLocalBusinessDay") LAST_LOCAL_BUSINESS_DAY("LastLocalBusinessDay"),
	
	/**
	 * The delivery includes both `Transfer on last Local Business Day` and `Transfer a Delivery Amount (IM) consisting of cash on any Local Business Day.`
	 */
	@RosettaEnumValue(value = "LastAndAnyLocalBusinessDay") LAST_AND_ANY_LOCAL_BUSINESS_DAY("LastAndAnyLocalBusinessDay")
;
	private static Map<String, DeliveryAmountElectionEnum> values;
	static {
        Map<String, DeliveryAmountElectionEnum> map = new ConcurrentHashMap<>();
		for (DeliveryAmountElectionEnum instance : DeliveryAmountElectionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DeliveryAmountElectionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DeliveryAmountElectionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DeliveryAmountElectionEnum fromDisplayName(String name) {
		DeliveryAmountElectionEnum value = values.get(name);
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
