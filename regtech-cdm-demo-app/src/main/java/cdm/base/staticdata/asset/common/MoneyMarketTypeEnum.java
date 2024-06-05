package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("MoneyMarketTypeEnum")
public enum MoneyMarketTypeEnum {

	@RosettaEnumValue(value = "CommercialPaper") COMMERCIAL_PAPER("CommercialPaper"),
	
	@RosettaEnumValue(value = "CertificateOfDeposit") CERTIFICATE_OF_DEPOSIT("CertificateOfDeposit")
;
	private static Map<String, MoneyMarketTypeEnum> values;
	static {
        Map<String, MoneyMarketTypeEnum> map = new ConcurrentHashMap<>();
		for (MoneyMarketTypeEnum instance : MoneyMarketTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MoneyMarketTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MoneyMarketTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MoneyMarketTypeEnum fromDisplayName(String name) {
		MoneyMarketTypeEnum value = values.get(name);
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
