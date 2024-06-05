package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumeration values to specify the margin type in relation to bilateral or regulatory obligation.
 * @version ${project.version}
 */
@RosettaEnum("RegMarginTypeEnum")
public enum RegMarginTypeEnum {

	/**
	 * Indicates Variation Margin
	 */
	@RosettaEnumValue(value = "VM") VM("VM"),
	
	/**
	 * Indicates Regulatory Initial Margin
	 */
	@RosettaEnumValue(value = "RegIM") REG_IM("RegIM"),
	
	/**
	 * Indicates Non Regulatory Initial margin or independent amount
	 */
	@RosettaEnumValue(value = "NonRegIM") NON_REG_IM("NonRegIM")
;
	private static Map<String, RegMarginTypeEnum> values;
	static {
        Map<String, RegMarginTypeEnum> map = new ConcurrentHashMap<>();
		for (RegMarginTypeEnum instance : RegMarginTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RegMarginTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RegMarginTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RegMarginTypeEnum fromDisplayName(String name) {
		RegMarginTypeEnum value = values.get(name);
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
