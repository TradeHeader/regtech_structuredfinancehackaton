package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of compounding, e.g. flat, straight.
 * @version ${project.version}
 */
@RosettaEnum("CompoundingMethodEnum")
public enum CompoundingMethodEnum {

	/**
	 * Flat compounding. Compounding excludes the spread. Note that the first compounding period has it&#39;s interest calculated including any spread then subsequent periods compound this at a rate excluding the spread.
	 */
	@RosettaEnumValue(value = "Flat") FLAT("Flat"),
	
	/**
	 * No compounding is to be applied.
	 */
	@RosettaEnumValue(value = "None") NONE("None"),
	
	/**
	 * Straight compounding. Compounding includes the spread.
	 */
	@RosettaEnumValue(value = "Straight") STRAIGHT("Straight"),
	
	/**
	 * Spread Exclusive compounding.
	 */
	@RosettaEnumValue(value = "SpreadExclusive") SPREAD_EXCLUSIVE("SpreadExclusive")
;
	private static Map<String, CompoundingMethodEnum> values;
	static {
        Map<String, CompoundingMethodEnum> map = new ConcurrentHashMap<>();
		for (CompoundingMethodEnum instance : CompoundingMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CompoundingMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CompoundingMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CompoundingMethodEnum fromDisplayName(String name) {
		CompoundingMethodEnum value = values.get(name);
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
