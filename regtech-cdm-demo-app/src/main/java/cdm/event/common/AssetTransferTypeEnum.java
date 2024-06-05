package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The qualification of the type of asset transfer.
 * @version ${project.version}
 */
@RosettaEnum("AssetTransferTypeEnum")
public enum AssetTransferTypeEnum {

	/**
	 * The transfer of assets takes place without a corresponding exchange of payment.
	 */
	@RosettaEnumValue(value = "FreeOfPayment") FREE_OF_PAYMENT("FreeOfPayment")
;
	private static Map<String, AssetTransferTypeEnum> values;
	static {
        Map<String, AssetTransferTypeEnum> map = new ConcurrentHashMap<>();
		for (AssetTransferTypeEnum instance : AssetTransferTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AssetTransferTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AssetTransferTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AssetTransferTypeEnum fromDisplayName(String name) {
		AssetTransferTypeEnum value = values.get(name);
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
