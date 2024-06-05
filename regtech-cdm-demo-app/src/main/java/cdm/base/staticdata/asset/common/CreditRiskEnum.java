package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify tranched or untranched credit risk.
 * @version ${project.version}
 */
@RosettaEnum("CreditRiskEnum")
public enum CreditRiskEnum {

	/**
	 * Indicates tranched credit risk, including securitizations.
	 */
	@RosettaEnumValue(value = "TranchedCreditRisk") TRANCHED_CREDIT_RISK("TranchedCreditRisk"),
	
	/**
	 * Indicates tranched credit risk, including repackagings.
	 */
	@RosettaEnumValue(value = "UntranchedCreditRisk") UNTRANCHED_CREDIT_RISK("UntranchedCreditRisk")
;
	private static Map<String, CreditRiskEnum> values;
	static {
        Map<String, CreditRiskEnum> map = new ConcurrentHashMap<>();
		for (CreditRiskEnum instance : CreditRiskEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditRiskEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditRiskEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditRiskEnum fromDisplayName(String name) {
		CreditRiskEnum value = values.get(name);
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
