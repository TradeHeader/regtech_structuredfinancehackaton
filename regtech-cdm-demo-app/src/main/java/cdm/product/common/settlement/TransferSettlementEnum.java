package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify how the transfer will settle, e.g. DvP.
 * @version ${project.version}
 */
@RosettaEnum("TransferSettlementEnum")
public enum TransferSettlementEnum {

	/**
	 * Simultaneous transfer of two assets, typically securities, as a way to avoid settlement risk.
	 */
	@RosettaEnumValue(value = "DeliveryVersusDelivery") DELIVERY_VERSUS_DELIVERY("DeliveryVersusDelivery"),
	
	/**
	 * Settlement in which the transfer of the asset and the cash settlement are simultaneous.
	 */
	@RosettaEnumValue(value = "DeliveryVersusPayment") DELIVERY_VERSUS_PAYMENT("DeliveryVersusPayment"),
	
	/**
	 * Simultaneous transfer of cashflows.
	 */
	@RosettaEnumValue(value = "PaymentVersusPayment") PAYMENT_VERSUS_PAYMENT("PaymentVersusPayment"),
	
	/**
	 * No central settlement.
	 */
	@RosettaEnumValue(value = "NotCentralSettlement") NOT_CENTRAL_SETTLEMENT("NotCentralSettlement")
;
	private static Map<String, TransferSettlementEnum> values;
	static {
        Map<String, TransferSettlementEnum> map = new ConcurrentHashMap<>();
		for (TransferSettlementEnum instance : TransferSettlementEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TransferSettlementEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TransferSettlementEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TransferSettlementEnum fromDisplayName(String name) {
		TransferSettlementEnum value = values.get(name);
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
