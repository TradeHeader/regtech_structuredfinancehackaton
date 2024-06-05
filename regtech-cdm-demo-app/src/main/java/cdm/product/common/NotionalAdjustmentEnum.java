package cdm.product.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the conditions that govern the adjustment to the number of units of the return swap.
 * @version ${project.version}
 */
@RosettaEnum("NotionalAdjustmentEnum")
public enum NotionalAdjustmentEnum {

	/**
	 * The adjustments to the number of units are governed by an execution clause.
	 */
	@RosettaEnumValue(value = "Execution") EXECUTION("Execution"),
	
	/**
	 * The adjustments to the number of units are governed by a portfolio rebalancing clause.
	 */
	@RosettaEnumValue(value = "PortfolioRebalancing") PORTFOLIO_REBALANCING("PortfolioRebalancing"),
	
	/**
	 * The adjustments to the number of units are not governed by any specific clause.
	 */
	@RosettaEnumValue(value = "Standard") STANDARD("Standard")
;
	private static Map<String, NotionalAdjustmentEnum> values;
	static {
        Map<String, NotionalAdjustmentEnum> map = new ConcurrentHashMap<>();
		for (NotionalAdjustmentEnum instance : NotionalAdjustmentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	NotionalAdjustmentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	NotionalAdjustmentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static NotionalAdjustmentEnum fromDisplayName(String name) {
		NotionalAdjustmentEnum value = values.get(name);
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
