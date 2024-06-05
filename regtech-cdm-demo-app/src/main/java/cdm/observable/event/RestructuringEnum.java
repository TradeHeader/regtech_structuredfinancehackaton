package cdm.observable.event;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the form of the restructuring credit event that is applicable to the credit default swap.
 * @version ${project.version}
 */
@RosettaEnum("RestructuringEnum")
public enum RestructuringEnum {

	/**
	 * Restructuring (Section 4.7) and Modified Restructuring Maturity Limitation and Conditionally Transferable Obligation (2014 Definitions: Section 3.31, 2003 Definitions: 2.32) apply.
	 */
	@RosettaEnumValue(value = "ModModR") MOD_MOD_R("ModModR"),
	
	/**
	 * Restructuring (Section 4.7) and Restructuring Maturity Limitation and Fully Transferable Obligation (2014 Definitions: Section 3.31, 2003 Definitions: 2.32) apply.
	 */
	@RosettaEnumValue(value = "ModR") MOD_R("ModR"),
	
	/**
	 * Restructuring as defined in the applicable ISDA Credit Derivatives Definitions. (2003 or 2014).
	 */
	@RosettaEnumValue(value = "R") R("R")
;
	private static Map<String, RestructuringEnum> values;
	static {
        Map<String, RestructuringEnum> map = new ConcurrentHashMap<>();
		for (RestructuringEnum instance : RestructuringEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RestructuringEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RestructuringEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RestructuringEnum fromDisplayName(String name) {
		RestructuringEnum value = values.get(name);
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
