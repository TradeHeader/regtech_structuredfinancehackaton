package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represetns an enumeration list to indentify the type of security.
 * @version ${project.version}
 */
@RosettaEnum("SecurityTypeEnum")
public enum SecurityTypeEnum {

	/**
	 * Identifies a security as a fixed income instrument of debt issued and securitized as a tradable asset.
	 */
	@RosettaEnumValue(value = "Debt") DEBT("Debt"),
	
	/**
	 * Identifies a security as an Equity value of holding of shares in listed company.
	 */
	@RosettaEnumValue(value = "Equity") EQUITY("Equity"),
	
	/**
	 * Identifies a security as an Instrument representing holding in an investment fund.
	 */
	@RosettaEnumValue(value = "Fund") FUND("Fund"),
	
	/**
	 * Identifies a security as a Warrant that give the right, but not the obligation, to buy or sell a security — most commonly an equity — at a certain price before expiration, or to receive the cash equivalent.
	 */
	@RosettaEnumValue(value = "Warrant") WARRANT("Warrant"),
	
	/**
	 * Identifies a security as one that that offers a derivative-based economic return which is not structured as a bond, an equity or a warrant. Note that this security type is not a Certificate of Deposit (aka CD).
	 */
	@RosettaEnumValue(value = "Certificate") CERTIFICATE("Certificate"),
	
	/**
	 * Identifies a security as a letter of credit or documentary credit/ bankers commercial credit.  A payment mechanism used in international trade to provide economic guarantee of payment by a creditworthy issuer for payment of exported goods.
	 */
	@RosettaEnumValue(value = "LetterOfCredit") LETTER_OF_CREDIT("LetterOfCredit"),
	
	/**
	 * Identifies a security as a listed derivative on an exchange.
	 */
	@RosettaEnumValue(value = "ListedDerivative") LISTED_DERIVATIVE("ListedDerivative"),
	
	@RosettaEnumValue(value = "Mortgage") MORTGAGE("Mortgage")
;
	private static Map<String, SecurityTypeEnum> values;
	static {
        Map<String, SecurityTypeEnum> map = new ConcurrentHashMap<>();
		for (SecurityTypeEnum instance : SecurityTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SecurityTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SecurityTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SecurityTypeEnum fromDisplayName(String name) {
		SecurityTypeEnum value = values.get(name);
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
