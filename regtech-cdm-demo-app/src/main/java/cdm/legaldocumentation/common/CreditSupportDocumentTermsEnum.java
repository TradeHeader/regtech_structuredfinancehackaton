package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the Credit Support Document Terms
 * @version ${project.version}
 */
@RosettaEnum("CreditSupportDocumentTermsEnum")
public enum CreditSupportDocumentTermsEnum {

	/**
	 * A specified Credit Support Document is provided
	 */
	@RosettaEnumValue(value = "Specified") SPECIFIED("Specified"),
	
	/**
	 * Any guarantee, collateral arrangement and/or other agreement or arrangement which provides for credit support with respect to the party’s obligations under this Agreement.
	 */
	@RosettaEnumValue(value = "Any") ANY("Any"),
	
	/**
	 * No Credit Support Document is specified.
	 */
	@RosettaEnumValue(value = "None") NONE("None")
;
	private static Map<String, CreditSupportDocumentTermsEnum> values;
	static {
        Map<String, CreditSupportDocumentTermsEnum> map = new ConcurrentHashMap<>();
		for (CreditSupportDocumentTermsEnum instance : CreditSupportDocumentTermsEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditSupportDocumentTermsEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditSupportDocumentTermsEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditSupportDocumentTermsEnum fromDisplayName(String name) {
		CreditSupportDocumentTermsEnum value = values.get(name);
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
