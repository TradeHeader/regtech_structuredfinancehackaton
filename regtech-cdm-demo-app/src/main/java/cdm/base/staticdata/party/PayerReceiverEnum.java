package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify an interest rate stream payer or receiver party.
 * @version ${project.version}
 */
@RosettaEnum("PayerReceiverEnum")
public enum PayerReceiverEnum {

	/**
	 * The party identified as the stream payer.
	 */
	@RosettaEnumValue(value = "Payer") PAYER("Payer"),
	
	/**
	 * The party identified as the stream receiver.
	 */
	@RosettaEnumValue(value = "Receiver") RECEIVER("Receiver")
;
	private static Map<String, PayerReceiverEnum> values;
	static {
        Map<String, PayerReceiverEnum> map = new ConcurrentHashMap<>();
		for (PayerReceiverEnum instance : PayerReceiverEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PayerReceiverEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PayerReceiverEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PayerReceiverEnum fromDisplayName(String name) {
		PayerReceiverEnum value = values.get(name);
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
