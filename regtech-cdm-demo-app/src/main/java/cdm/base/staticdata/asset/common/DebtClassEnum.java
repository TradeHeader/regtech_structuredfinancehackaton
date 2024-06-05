package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list that identifies the type of debt.
 * @version ${project.version}
 */
@RosettaEnum("DebtClassEnum")
public enum DebtClassEnum {

	/**
	 * Identifies a debt instrument that has periodic income payments and value derived from or backed by a specified pool of underlying assets which could be mortgages or other obligations.
	 */
	@RosettaEnumValue(value = "AssetBacked") ASSET_BACKED("AssetBacked"),
	
	/**
	 * Identifies a debt instrument that can be converted into common shares.
	 */
	@RosettaEnumValue(value = "Convertible") CONVERTIBLE("Convertible"),
	
	/**
	 * Identifies a debt instrument as one issued by financial institutions to count towards regulatory capital, including term and perpetual subordinated debt, contingently convertible and others.  Excludes preferred share capital.
	 */
	@RosettaEnumValue(value = "RegCap") REG_CAP("RegCap"),
	
	/**
	 * Identifies a debt instrument athat has non-standard interest or principal features, with full recourse to the issuer.
	 */
	@RosettaEnumValue(value = "Structured") STRUCTURED("Structured"),
	
	/**
	 * Identifies a debt instrument that has a periodic coupon, a defined maturity, and is not backed by any specific asset. The seniority and the structure of the income and principal payments can optionally be defined in DebtType.DebtEconomics.
	 */
	@RosettaEnumValue(value = "Vanilla") VANILLA("Vanilla"),
	
	/**
	 * Identifies a debt instrument that can be converted primarily at the election of the holder into common shares of the Issuer.
	 */
	@RosettaEnumValue(value = "HolderConvertible") HOLDER_CONVERTIBLE("HolderConvertible"),
	
	/**
	 * Identifies a debt instrument that can be converted primarily at the election of the holder into common shares of a party other than the Issuer.
	 */
	@RosettaEnumValue(value = "HolderExchangeable") HOLDER_EXCHANGEABLE("HolderExchangeable"),
	
	/**
	 * Identifies a debt instrument that can be converted at the election of the Issuer into common shares of the Issuer.  Also known as reverse convertible.
	 */
	@RosettaEnumValue(value = "IssuerConvertible") ISSUER_CONVERTIBLE("IssuerConvertible"),
	
	/**
	 * Identifies a debt instrument that can be converted at the election of the Issuer into common shares of a party other than the Issuer.  Also known as reverse exchangeable.
	 */
	@RosettaEnumValue(value = "IssuerExchangeable") ISSUER_EXCHANGEABLE("IssuerExchangeable")
;
	private static Map<String, DebtClassEnum> values;
	static {
        Map<String, DebtClassEnum> map = new ConcurrentHashMap<>();
		for (DebtClassEnum instance : DebtClassEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DebtClassEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DebtClassEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DebtClassEnum fromDisplayName(String name) {
		DebtClassEnum value = values.get(name);
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
