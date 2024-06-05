package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Denotes the method by which the pricing days are distributed across the pricing period.
 * @version ${project.version}
 */
@RosettaEnum("DayDistributionEnum")
public enum DayDistributionEnum {

	@RosettaEnumValue(value = "All") ALL("All"),
	
	@RosettaEnumValue(value = "First") FIRST("First"),
	
	@RosettaEnumValue(value = "Last") LAST("Last"),
	
	@RosettaEnumValue(value = "Penultimate") PENULTIMATE("Penultimate")
;
	private static Map<String, DayDistributionEnum> values;
	static {
        Map<String, DayDistributionEnum> map = new ConcurrentHashMap<>();
		for (DayDistributionEnum instance : DayDistributionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DayDistributionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DayDistributionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DayDistributionEnum fromDisplayName(String name) {
		DayDistributionEnum value = values.get(name);
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
