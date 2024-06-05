package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to qualify the type of account.
 * @version ${project.version}
 */
@RosettaEnum("AccountTypeEnum")
public enum AccountTypeEnum {

	/**
	 * Aggregate client account, as defined under ESMA MiFIR.
	 */
	@RosettaEnumValue(value = "AggregateClient") AGGREGATE_CLIENT("AggregateClient"),
	
	/**
	 * The account contains trading activity or positions that belong to a client of the firm that opened the account.
	 */
	@RosettaEnumValue(value = "Client") CLIENT("Client"),
	
	/**
	 * The account contains proprietary trading activity or positions, belonging to the firm that is the owner of the account.
	 */
	@RosettaEnumValue(value = "House") HOUSE("House")
;
	private static Map<String, AccountTypeEnum> values;
	static {
        Map<String, AccountTypeEnum> map = new ConcurrentHashMap<>();
		for (AccountTypeEnum instance : AccountTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AccountTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AccountTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AccountTypeEnum fromDisplayName(String name) {
		AccountTypeEnum value = values.get(name);
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
