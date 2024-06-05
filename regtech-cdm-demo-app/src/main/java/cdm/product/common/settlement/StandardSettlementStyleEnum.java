package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify whether a trade is settling using standard settlement instructions as well as whether it is a candidate for settlement netting.
 * @version ${project.version}
 */
@RosettaEnum("StandardSettlementStyleEnum")
public enum StandardSettlementStyleEnum {

	/**
	 * This trade will settle using standard predetermined funds settlement instructions.
	 */
	@RosettaEnumValue(value = "Standard") STANDARD("Standard"),
	
	/**
	 * This trade is a candidate for settlement netting.
	 */
	@RosettaEnumValue(value = "Net") NET("Net"),
	
	/**
	 * This trade will settle using standard predetermined funds settlement instructions and is a candidate for settlement netting.
	 */
	@RosettaEnumValue(value = "StandardAndNet") STANDARD_AND_NET("StandardAndNet"),
	
	/**
	 * These trades have been paired and are a candidate for settlement netting.
	 */
	@RosettaEnumValue(value = "PairAndNet") PAIR_AND_NET("PairAndNet")
;
	private static Map<String, StandardSettlementStyleEnum> values;
	static {
        Map<String, StandardSettlementStyleEnum> map = new ConcurrentHashMap<>();
		for (StandardSettlementStyleEnum instance : StandardSettlementStyleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	StandardSettlementStyleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	StandardSettlementStyleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static StandardSettlementStyleEnum fromDisplayName(String name) {
		StandardSettlementStyleEnum value = values.get(name);
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
