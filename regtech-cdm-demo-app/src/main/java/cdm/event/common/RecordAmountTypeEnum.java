package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration of the account level for the billing summary.
 * @version ${project.version}
 */
@RosettaEnum("RecordAmountTypeEnum")
public enum RecordAmountTypeEnum {

	@RosettaEnumValue(value = "AccountTotal") ACCOUNT_TOTAL("AccountTotal"),
	
	@RosettaEnumValue(value = "GrandTotal") GRAND_TOTAL("GrandTotal"),
	
	@RosettaEnumValue(value = "ParentTotal") PARENT_TOTAL("ParentTotal")
;
	private static Map<String, RecordAmountTypeEnum> values;
	static {
        Map<String, RecordAmountTypeEnum> map = new ConcurrentHashMap<>();
		for (RecordAmountTypeEnum instance : RecordAmountTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RecordAmountTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RecordAmountTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RecordAmountTypeEnum fromDisplayName(String name) {
		RecordAmountTypeEnum value = values.get(name);
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
