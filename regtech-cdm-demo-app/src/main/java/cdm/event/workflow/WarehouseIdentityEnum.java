package cdm.event.workflow;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("WarehouseIdentityEnum")
public enum WarehouseIdentityEnum {

	/**
	 * The DTCC Trade Information Warehouse Gold service
	 */
	@RosettaEnumValue(value = "DTCC_TIW_Gold") DTCC_TIW_GOLD("DTCC_TIW_Gold")
;
	private static Map<String, WarehouseIdentityEnum> values;
	static {
        Map<String, WarehouseIdentityEnum> map = new ConcurrentHashMap<>();
		for (WarehouseIdentityEnum instance : WarehouseIdentityEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	WarehouseIdentityEnum(String rosettaName) {
		this(rosettaName, null);
	}

	WarehouseIdentityEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static WarehouseIdentityEnum fromDisplayName(String name) {
		WarehouseIdentityEnum value = values.get(name);
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
