package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify the credit watch rating.
 * @version ${project.version}
 */
@RosettaEnum("CreditRatingCreditWatchEnum")
public enum CreditRatingCreditWatchEnum {

	/**
	 * Denotes a rating may be raised.
	 */
	@RosettaEnumValue(value = "Positive") POSITIVE("Positive"),
	
	/**
	 * Denotes a rating may be lowered.
	 */
	@RosettaEnumValue(value = "Negative") NEGATIVE("Negative"),
	
	/**
	 * Denotes a rating may be raised, lowered, or affirmed.
	 */
	@RosettaEnumValue(value = "Developing") DEVELOPING("Developing")
;
	private static Map<String, CreditRatingCreditWatchEnum> values;
	static {
        Map<String, CreditRatingCreditWatchEnum> map = new ConcurrentHashMap<>();
		for (CreditRatingCreditWatchEnum instance : CreditRatingCreditWatchEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditRatingCreditWatchEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditRatingCreditWatchEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditRatingCreditWatchEnum fromDisplayName(String name) {
		CreditRatingCreditWatchEnum value = values.get(name);
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
