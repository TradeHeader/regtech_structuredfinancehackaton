package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify how a contract has been executed, e.g. electronically, verbally, ...
 * @version ${project.version}
 */
@RosettaEnum("ExecutionTypeEnum")
public enum ExecutionTypeEnum {

	/**
	 * Execution via electronic execution facility, derivatives contract market, or other electronic message such as an instant message.
	 */
	@RosettaEnumValue(value = "Electronic") ELECTRONIC("Electronic"),
	
	/**
	 * Bilateral execution between counterparties not pursuant to the rules of a SEF or DCM.
	 */
	@RosettaEnumValue(value = "OffFacility") OFF_FACILITY("OffFacility"),
	
	/**
	 * Execution via a platform that may or may not be covered by a regulatory defintion. OnVenue is intended to distinguish trades executed on a trading platform from those executed via phone, email or messaging apps. The role and details of the venue are included in the party attribute of the trade. The general rule is that if the parties utilitzed the services of the platform to execute the trade then it would be considered OnVenue.
	 */
	@RosettaEnumValue(value = "OnVenue") ON_VENUE("OnVenue")
;
	private static Map<String, ExecutionTypeEnum> values;
	static {
        Map<String, ExecutionTypeEnum> map = new ConcurrentHashMap<>();
		for (ExecutionTypeEnum instance : ExecutionTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ExecutionTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ExecutionTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ExecutionTypeEnum fromDisplayName(String name) {
		ExecutionTypeEnum value = values.get(name);
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
