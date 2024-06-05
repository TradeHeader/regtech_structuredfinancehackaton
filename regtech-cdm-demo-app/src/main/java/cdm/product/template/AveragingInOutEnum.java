package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of averaging used in an Asian option.
 * @version ${project.version}
 */
@RosettaEnum("AveragingInOutEnum")
public enum AveragingInOutEnum {

	/**
	 * The average price is used to derive the strike price. Also known as &#39;Asian strike&#39; style option.
	 */
	@RosettaEnumValue(value = "In") IN("In"),
	
	/**
	 * The average price is used to derive the expiration price. Also known as &#39;Asian price&#39; style option.
	 */
	@RosettaEnumValue(value = "Out") OUT("Out"),
	
	/**
	 * The average price is used to derive both the strike and the expiration price.
	 */
	@RosettaEnumValue(value = "Both") BOTH("Both")
;
	private static Map<String, AveragingInOutEnum> values;
	static {
        Map<String, AveragingInOutEnum> map = new ConcurrentHashMap<>();
		for (AveragingInOutEnum instance : AveragingInOutEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AveragingInOutEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AveragingInOutEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AveragingInOutEnum fromDisplayName(String name) {
		AveragingInOutEnum value = values.get(name);
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
