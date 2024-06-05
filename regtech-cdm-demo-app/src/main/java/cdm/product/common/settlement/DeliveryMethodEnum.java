package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies delivery methods for securities transactions. This coding-scheme defines the possible delivery methods for securities.
 * @version ${project.version}
 */
@RosettaEnum("DeliveryMethodEnum")
public enum DeliveryMethodEnum {

	/**
	 * Indicates that a securities delivery must be made against payment in simultaneous transmissions and stipulate each other.
	 */
	@RosettaEnumValue(value = "DeliveryVersusPayment") DELIVERY_VERSUS_PAYMENT("DeliveryVersusPayment"),
	
	/**
	 * Indicates that a securities delivery can be made without a simultaneous cash payment in exchange and not depending on if payment obligations are fulfilled or not and vice versa.
	 */
	@RosettaEnumValue(value = "FreeOfPayment") FREE_OF_PAYMENT("FreeOfPayment"),
	
	/**
	 * Indicates that a securities delivery must be made in full before the payment for the securities; fulfillment of payment obligations depends on securities delivery obligations fulfillment.
	 */
	@RosettaEnumValue(value = "PreDelivery") PRE_DELIVERY("PreDelivery"),
	
	/**
	 * Indicates that a payment in full amount must be made before the securities delivery; fulfillment of securities delivery obligations depends on payment obligations fulfillment.
	 */
	@RosettaEnumValue(value = "PrePayment") PRE_PAYMENT("PrePayment")
;
	private static Map<String, DeliveryMethodEnum> values;
	static {
        Map<String, DeliveryMethodEnum> map = new ConcurrentHashMap<>();
		for (DeliveryMethodEnum instance : DeliveryMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DeliveryMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DeliveryMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DeliveryMethodEnum fromDisplayName(String name) {
		DeliveryMethodEnum value = values.get(name);
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
