package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify the credit rating outlook.
 * @version ${project.version}
 */
@RosettaEnum("CreditRatingOutlookEnum")
public enum CreditRatingOutlookEnum {

	/**
	 * Denotes a rating may be raised.
	 */
	@RosettaEnumValue(value = "Positive") POSITIVE("Positive"),
	
	/**
	 * Denotes a rating may be lowered.
	 */
	@RosettaEnumValue(value = "Negative") NEGATIVE("Negative"),
	
	/**
	 * Denotes a rating is not likely to change.
	 */
	@RosettaEnumValue(value = "Stable") STABLE("Stable"),
	
	/**
	 * Denotes a rating may be raised, lowered, or affirmed.
	 */
	@RosettaEnumValue(value = "Developing") DEVELOPING("Developing")
;
	private static Map<String, CreditRatingOutlookEnum> values;
	static {
        Map<String, CreditRatingOutlookEnum> map = new ConcurrentHashMap<>();
		for (CreditRatingOutlookEnum instance : CreditRatingOutlookEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditRatingOutlookEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditRatingOutlookEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditRatingOutlookEnum fromDisplayName(String name) {
		CreditRatingOutlookEnum value = values.get(name);
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
