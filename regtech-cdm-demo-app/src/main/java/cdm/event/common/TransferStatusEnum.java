package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify the transfer status.
 * @version ${project.version}
 */
@RosettaEnum("TransferStatusEnum")
public enum TransferStatusEnum {

	/**
	 * The transfer is disputed.
	 */
	@RosettaEnumValue(value = "Disputed") DISPUTED("Disputed"),
	
	/**
	 * The transfer has been instructed.
	 */
	@RosettaEnumValue(value = "Instructed") INSTRUCTED("Instructed"),
	
	/**
	 * The transfer is pending instruction.
	 */
	@RosettaEnumValue(value = "Pending") PENDING("Pending"),
	
	/**
	 * The transfer has been settled.
	 */
	@RosettaEnumValue(value = "Settled") SETTLED("Settled"),
	
	/**
	 * The transfer has been netted into a separate Transfer.
	 */
	@RosettaEnumValue(value = "Netted") NETTED("Netted")
;
	private static Map<String, TransferStatusEnum> values;
	static {
        Map<String, TransferStatusEnum> map = new ConcurrentHashMap<>();
		for (TransferStatusEnum instance : TransferStatusEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TransferStatusEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TransferStatusEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TransferStatusEnum fromDisplayName(String name) {
		TransferStatusEnum value = values.get(name);
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
