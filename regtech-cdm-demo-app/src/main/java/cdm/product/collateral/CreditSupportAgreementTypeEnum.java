package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import com.rosetta.model.lib.annotations.RosettaSynonym;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of Credit Support Agreement governing the transaction.
 * @version ${project.version}
 */
@RosettaEnum("CreditSupportAgreementTypeEnum")
public enum CreditSupportAgreementTypeEnum {

	/**
	 * A Credit Support Deed legal agreement.
	 */
	@RosettaEnumValue(value = "CreditSupportDeed") CREDIT_SUPPORT_DEED("CreditSupportDeed"),
	
	/**
	 * A Credit Support Annex legal agreement.
	 */
	@RosettaSynonym(value = "CSA", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "CreditSupportAnnex") CREDIT_SUPPORT_ANNEX("CreditSupportAnnex"),
	
	/**
	 * A Collateral Transfer Agreement
	 */
	@RosettaEnumValue(value = "CollateralTransferAgreement") COLLATERAL_TRANSFER_AGREEMENT("CollateralTransferAgreement")
;
	private static Map<String, CreditSupportAgreementTypeEnum> values;
	static {
        Map<String, CreditSupportAgreementTypeEnum> map = new ConcurrentHashMap<>();
		for (CreditSupportAgreementTypeEnum instance : CreditSupportAgreementTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditSupportAgreementTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditSupportAgreementTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditSupportAgreementTypeEnum fromDisplayName(String name) {
		CreditSupportAgreementTypeEnum value = values.get(name);
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
