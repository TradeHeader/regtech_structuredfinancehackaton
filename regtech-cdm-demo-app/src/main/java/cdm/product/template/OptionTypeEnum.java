package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of the option. In FpML, OptionTypeEnum is a union with PutCallEnum, which specifies whether the option is a put or a call.
 * @version ${project.version}
 */
@RosettaEnum("OptionTypeEnum")
public enum OptionTypeEnum {

	/**
	 * A put option gives the holder the right to sell the underlying asset by a certain date for a certain price.
	 */
	@RosettaEnumValue(value = "Put") PUT("Put"),
	
	/**
	 * A call option gives the holder the right to buy the underlying asset by a certain date for a certain price.
	 */
	@RosettaEnumValue(value = "Call") CALL("Call"),
	
	/**
	 * A &#39;payer&#39; option: If you buy a &#39;payer&#39; option you have the right but not the obligation to enter into the underlying swap transaction as the &#39;fixed&#39; rate/price payer and receive float.
	 */
	@RosettaEnumValue(value = "Payer") PAYER("Payer"),
	
	/**
	 * A &#39;receiver&#39; option: If you buy a &#39;receiver&#39; option you have the right but not the obligation to enter into the underlying swap transaction as the &#39;fixed&#39; rate/price receiver and pay float.
	 */
	@RosettaEnumValue(value = "Receiver") RECEIVER("Receiver"),
	
	/**
	 * A straddle strategy, which involves the simultaneous buying of a put and a call of the same underlier, at the same strike and same expiration date
	 */
	@RosettaEnumValue(value = "Straddle") STRADDLE("Straddle")
;
	private static Map<String, OptionTypeEnum> values;
	static {
        Map<String, OptionTypeEnum> map = new ConcurrentHashMap<>();
		for (OptionTypeEnum instance : OptionTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	OptionTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	OptionTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static OptionTypeEnum fromDisplayName(String name) {
		OptionTypeEnum value = values.get(name);
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
