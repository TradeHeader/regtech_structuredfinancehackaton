package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the asset type.
 * @version ${project.version}
 */
@RosettaEnum("AssetTypeEnum")
public enum AssetTypeEnum {

	/**
	 * Indentifies negotiable financial instrument of monetary value with an issue ownership position.
	 */
	@RosettaEnumValue(value = "Security") SECURITY("Security"),
	
	/**
	 * Indentifies cash in a currency form.
	 */
	@RosettaEnumValue(value = "Cash") CASH("Cash"),
	
	/**
	 * Indentifies basic good used in commerce that is interchangeable with other goods of the same type.
	 */
	@RosettaEnumValue(value = "Commodity") COMMODITY("Commodity"),
	
	/**
	 * Indentifies other asset types.
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other")
;
	private static Map<String, AssetTypeEnum> values;
	static {
        Map<String, AssetTypeEnum> map = new ConcurrentHashMap<>();
		for (AssetTypeEnum instance : AssetTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AssetTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AssetTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AssetTypeEnum fromDisplayName(String name) {
		AssetTypeEnum value = values.get(name);
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
