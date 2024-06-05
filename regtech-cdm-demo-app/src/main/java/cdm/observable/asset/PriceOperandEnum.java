package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("PriceOperandEnum")
public enum PriceOperandEnum {

	@RosettaEnumValue(value = "AccruedInterest") ACCRUED_INTEREST("AccruedInterest"),
	
	@RosettaEnumValue(value = "Commission") COMMISSION("Commission"),
	
	@RosettaEnumValue(value = "ForwardPoint") FORWARD_POINT("ForwardPoint")
;
	private static Map<String, PriceOperandEnum> values;
	static {
        Map<String, PriceOperandEnum> map = new ConcurrentHashMap<>();
		for (PriceOperandEnum instance : PriceOperandEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PriceOperandEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PriceOperandEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PriceOperandEnum fromDisplayName(String name) {
		PriceOperandEnum value = values.get(name);
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
