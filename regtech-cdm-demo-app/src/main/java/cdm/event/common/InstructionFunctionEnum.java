package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values indicating the BusinessEvent function associated input instructions.
 * @version ${project.version}
 */
@RosettaEnum("InstructionFunctionEnum")
public enum InstructionFunctionEnum {

	@RosettaEnumValue(value = "Execution") EXECUTION("Execution"),
	
	@RosettaEnumValue(value = "ContractFormation") CONTRACT_FORMATION("ContractFormation"),
	
	@RosettaEnumValue(value = "QuantityChange") QUANTITY_CHANGE("QuantityChange"),
	
	@RosettaEnumValue(value = "Renegotiation") RENEGOTIATION("Renegotiation"),
	
	@RosettaEnumValue(value = "Compression") COMPRESSION("Compression")
;
	private static Map<String, InstructionFunctionEnum> values;
	static {
        Map<String, InstructionFunctionEnum> map = new ConcurrentHashMap<>();
		for (InstructionFunctionEnum instance : InstructionFunctionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	InstructionFunctionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	InstructionFunctionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static InstructionFunctionEnum fromDisplayName(String name) {
		InstructionFunctionEnum value = values.get(name);
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
