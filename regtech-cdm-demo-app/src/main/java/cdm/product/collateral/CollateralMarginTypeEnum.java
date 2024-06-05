package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of margin for which a legal agreement is named.
 * @version ${project.version}
 */
@RosettaEnum("CollateralMarginTypeEnum")
public enum CollateralMarginTypeEnum {

	/**
	 * Denotes a margin agreement that is identified for use with Variation Margin/VM.
	 */
	@RosettaEnumValue(value = "VariationMargin") VARIATION_MARGIN("VariationMargin"),
	
	/**
	 * Denotes a margin agreement that is identified for use with Initial Margin/IM.
	 */
	@RosettaEnumValue(value = "InitialMargin") INITIAL_MARGIN("InitialMargin")
;
	private static Map<String, CollateralMarginTypeEnum> values;
	static {
        Map<String, CollateralMarginTypeEnum> map = new ConcurrentHashMap<>();
		for (CollateralMarginTypeEnum instance : CollateralMarginTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CollateralMarginTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CollateralMarginTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CollateralMarginTypeEnum fromDisplayName(String name) {
		CollateralMarginTypeEnum value = values.get(name);
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
