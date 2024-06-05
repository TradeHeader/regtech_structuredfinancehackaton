package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of return associated the equity payout.
 * @version ${project.version}
 */
@RosettaEnum("ReturnTypeEnum")
public enum ReturnTypeEnum {

	/**
	 * Price return, i.e. excluding dividends.
	 */
	@RosettaEnumValue(value = "Price") PRICE("Price"),
	
	/**
	 * Total return, i.e. including dividend and price components.
	 */
	@RosettaEnumValue(value = "Total") TOTAL("Total")
;
	private static Map<String, ReturnTypeEnum> values;
	static {
        Map<String, ReturnTypeEnum> map = new ConcurrentHashMap<>();
		for (ReturnTypeEnum instance : ReturnTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ReturnTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ReturnTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ReturnTypeEnum fromDisplayName(String name) {
		ReturnTypeEnum value = values.get(name);
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
