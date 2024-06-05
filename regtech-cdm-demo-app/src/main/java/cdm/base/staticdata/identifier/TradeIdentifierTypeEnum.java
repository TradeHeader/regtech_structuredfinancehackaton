package cdm.base.staticdata.identifier;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the enumerated values to specify the nature of a trade identifier.
 * @version ${project.version}
 */
@RosettaEnum("TradeIdentifierTypeEnum")
public enum TradeIdentifierTypeEnum {

	@RosettaEnumValue(value = "UniqueTransactionIdentifier") UNIQUE_TRANSACTION_IDENTIFIER("UniqueTransactionIdentifier"),
	
	@RosettaEnumValue(value = "UniqueSwapIdentifier") UNIQUE_SWAP_IDENTIFIER("UniqueSwapIdentifier")
;
	private static Map<String, TradeIdentifierTypeEnum> values;
	static {
        Map<String, TradeIdentifierTypeEnum> map = new ConcurrentHashMap<>();
		for (TradeIdentifierTypeEnum instance : TradeIdentifierTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TradeIdentifierTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TradeIdentifierTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TradeIdentifierTypeEnum fromDisplayName(String name) {
		TradeIdentifierTypeEnum value = values.get(name);
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
