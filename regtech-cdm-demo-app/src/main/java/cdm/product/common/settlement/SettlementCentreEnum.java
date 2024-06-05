package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the settlement centre for a securities transaction.
 * @version ${project.version}
 */
@RosettaEnum("SettlementCentreEnum")
public enum SettlementCentreEnum {

	/**
	 * Euroclear Bank
	 */
	@RosettaEnumValue(value = "EuroclearBank") EUROCLEAR_BANK("EuroclearBank"),
	
	/**
	 * ClearStream Banking Luxembourg
	 */
	@RosettaEnumValue(value = "ClearstreamBankingLuxembourg") CLEARSTREAM_BANKING_LUXEMBOURG("ClearstreamBankingLuxembourg")
;
	private static Map<String, SettlementCentreEnum> values;
	static {
        Map<String, SettlementCentreEnum> map = new ConcurrentHashMap<>();
		for (SettlementCentreEnum instance : SettlementCentreEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SettlementCentreEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SettlementCentreEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SettlementCentreEnum fromDisplayName(String name) {
		SettlementCentreEnum value = values.get(name);
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
