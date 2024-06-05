package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("PositionEventIntentEnum")
public enum PositionEventIntentEnum {

	/**
	 * The intent is to form a position from a fully formed contract.
	 */
	@RosettaEnumValue(value = "PositionCreation") POSITION_CREATION("PositionCreation"),
	
	/**
	 * The intent is to take into effect the occurrence of a Corporate Action and the particular Corporate Action at stake shall be further specified in CorporateActionTypeEnum.
	 */
	@RosettaEnumValue(value = "CorporateActionAdjustment") CORPORATE_ACTION_ADJUSTMENT("CorporateActionAdjustment"),
	
	/**
	 * The intent is to Decrease the quantity of the position.
	 */
	@RosettaEnumValue(value = "Decrease") DECREASE("Decrease"),
	
	/**
	 * The intent is to Increase the quantity of the position.
	 */
	@RosettaEnumValue(value = "Increase") INCREASE("Increase"),
	
	/**
	 * The intent is to transfer the position to another clearing member.
	 */
	@RosettaEnumValue(value = "Transfer") TRANSFER("Transfer"),
	
	/**
	 * The intent is to Exercise a position or part of a position.
	 */
	@RosettaEnumValue(value = "OptionExercise") OPTION_EXERCISE("OptionExercise"),
	
	/**
	 * The intent is to update the valuation of the position.
	 */
	@RosettaEnumValue(value = "Valuation") VALUATION("Valuation")
;
	private static Map<String, PositionEventIntentEnum> values;
	static {
        Map<String, PositionEventIntentEnum> map = new ConcurrentHashMap<>();
		for (PositionEventIntentEnum instance : PositionEventIntentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PositionEventIntentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PositionEventIntentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PositionEventIntentEnum fromDisplayName(String name) {
		PositionEventIntentEnum value = values.get(name);
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
