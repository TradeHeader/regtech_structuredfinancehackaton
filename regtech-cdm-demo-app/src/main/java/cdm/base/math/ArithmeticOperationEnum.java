package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * An arithmetic operator that can be passed to a function
 * @version ${project.version}
 */
@RosettaEnum("ArithmeticOperationEnum")
public enum ArithmeticOperationEnum {

	/**
	 * Addition of 2 values
	 */
	@RosettaEnumValue(value = "Add") ADD("Add"),
	
	/**
	 * Subtraction of 2nd value from 1st value
	 */
	@RosettaEnumValue(value = "Subtract") SUBTRACT("Subtract"),
	
	/**
	 * Multiplication of 2 values
	 */
	@RosettaEnumValue(value = "Multiply") MULTIPLY("Multiply"),
	
	/**
	 * Division of 1st value by 2nd value
	 */
	@RosettaEnumValue(value = "Divide") DIVIDE("Divide"),
	
	/**
	 * Maximum of 2 values
	 */
	@RosettaEnumValue(value = "Max") MAX("Max"),
	
	/**
	 * Minimum of 2 values
	 */
	@RosettaEnumValue(value = "Min") MIN("Min")
;
	private static Map<String, ArithmeticOperationEnum> values;
	static {
        Map<String, ArithmeticOperationEnum> map = new ConcurrentHashMap<>();
		for (ArithmeticOperationEnum instance : ArithmeticOperationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ArithmeticOperationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ArithmeticOperationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ArithmeticOperationEnum fromDisplayName(String name) {
		ArithmeticOperationEnum value = values.get(name);
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
