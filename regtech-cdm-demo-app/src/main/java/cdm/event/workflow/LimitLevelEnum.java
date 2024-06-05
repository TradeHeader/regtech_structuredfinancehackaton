package cdm.event.workflow;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to specify the level at which the limit is set: customer business, proprietary business or account level. This is part of the CME specification for clearing credit limits, although not specified as a set of enumerated values as part of the clearing confirmation specification.
 * @version ${project.version}
 */
@RosettaEnum("LimitLevelEnum")
public enum LimitLevelEnum {

	/**
	 * The limit is set in relation to the proprietary business undertaken by the clearing counterparty.
	 */
	@RosettaEnumValue(value = "Account") ACCOUNT("Account"),
	
	/**
	 * The limit is set in relation to the customer business undertaken by the clearing counterparty.
	 */
	@RosettaEnumValue(value = "Customer") CUSTOMER("Customer"),
	
	/**
	 * The limit is set at the account level in relation to the clearing counterparty.
	 */
	@RosettaEnumValue(value = "House") HOUSE("House")
;
	private static Map<String, LimitLevelEnum> values;
	static {
        Map<String, LimitLevelEnum> map = new ConcurrentHashMap<>();
		for (LimitLevelEnum instance : LimitLevelEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	LimitLevelEnum(String rosettaName) {
		this(rosettaName, null);
	}

	LimitLevelEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static LimitLevelEnum fromDisplayName(String name) {
		LimitLevelEnum value = values.get(name);
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
