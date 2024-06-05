package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the Credit Support Provider Terms
 * @version ${project.version}
 */
@RosettaEnum("CreditSupportProviderTermsEnum")
public enum CreditSupportProviderTermsEnum {

	/**
	 * A specified Credit Support Provider is provided
	 */
	@RosettaEnumValue(value = "Specified") SPECIFIED("Specified"),
	
	/**
	 * Any party or parties who now or in the future may provide a Credit Support Document or other form of credit support.
	 */
	@RosettaEnumValue(value = "Any") ANY("Any"),
	
	/**
	 * No Credit Support Provider is specified.
	 */
	@RosettaEnumValue(value = "None") NONE("None")
;
	private static Map<String, CreditSupportProviderTermsEnum> values;
	static {
        Map<String, CreditSupportProviderTermsEnum> map = new ConcurrentHashMap<>();
		for (CreditSupportProviderTermsEnum instance : CreditSupportProviderTermsEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditSupportProviderTermsEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditSupportProviderTermsEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditSupportProviderTermsEnum fromDisplayName(String name) {
		CreditSupportProviderTermsEnum value = values.get(name);
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
