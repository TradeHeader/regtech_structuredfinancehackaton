package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies the order of repayment in the event of a sale or bankruptcy of the issuer or a related party (eg guarantor).
 * @version ${project.version}
 */
@RosettaEnum("DebtSeniorityEnum")
public enum DebtSeniorityEnum {

	/**
	 * Denotes debt which is secured over assets of the issuer or a related party (eg guarantor).
	 */
	@RosettaEnumValue(value = "Secured") SECURED("Secured"),
	
	/**
	 * Denotes debt  which ranks pari passu with all other unsecured creditors of the issuer.
	 */
	@RosettaEnumValue(value = "Senior") SENIOR("Senior"),
	
	/**
	 * Denotes debt  owed to an unsecured creditor that in the event of a liquidation can only be paid after the claims of secured and senior creditors have been met.
	 */
	@RosettaEnumValue(value = "Subordinated") SUBORDINATED("Subordinated")
;
	private static Map<String, DebtSeniorityEnum> values;
	static {
        Map<String, DebtSeniorityEnum> map = new ConcurrentHashMap<>();
		for (DebtSeniorityEnum instance : DebtSeniorityEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DebtSeniorityEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DebtSeniorityEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DebtSeniorityEnum fromDisplayName(String name) {
		DebtSeniorityEnum value = values.get(name);
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
