package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the Maturity.
 * @version ${project.version}
 */
@RosettaEnum("MaturityTypeEnum")
public enum MaturityTypeEnum {

	/**
	 * Denotes a period from now until maturity date.
	 */
	@RosettaEnumValue(value = "RemainingMaturity") REMAINING_MATURITY("RemainingMaturity"),
	
	/**
	 * Denotes a period from issuance until maturity date.
	 */
	@RosettaEnumValue(value = "OriginalMaturity") ORIGINAL_MATURITY("OriginalMaturity"),
	
	/**
	 * Denotes a period from issuance date until now.
	 */
	@RosettaEnumValue(value = "FromIssuance") FROM_ISSUANCE("FromIssuance")
;
	private static Map<String, MaturityTypeEnum> values;
	static {
        Map<String, MaturityTypeEnum> map = new ConcurrentHashMap<>();
		for (MaturityTypeEnum instance : MaturityTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MaturityTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MaturityTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MaturityTypeEnum fromDisplayName(String name) {
		MaturityTypeEnum value = values.get(name);
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
