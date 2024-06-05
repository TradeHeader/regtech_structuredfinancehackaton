package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Identifies European Union Eligible Collateral Assets classification categories based on EMIR Uncleared Margin Rules. Eligible Collateral asset classes for both initial margin (IM) and variation margin (VM) posted and collected between specified entities. Please note: EMIR regulation will detail which eligible collateral assets classes apply to each type of entity pairing (counterparty) and which apply to posting of IM and VM.
 * @version ${project.version}
 */
@RosettaEnum("EU_EMIR_EligibleCollateralEnum")
public enum EU_EMIR_EligibleCollateralEnum {

	/**
	 * Denotes Cash in the form of money credited to an account in any currency, or similar claims for the repayment of money, such as money market deposits.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeA") EU_EMIR_TYPE_A("EU_EMIRTypeA"),
	
	/**
	 *  Denotes gold in the form of allocated pure gold bullion of recognised good delivery.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeB") EU_EMIR_TYPE_B("EU_EMIRTypeB"),
	
	/**
	 *  Denotes debt securities issued by Member States&#39; central governments or central banks.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeC") EU_EMIR_TYPE_C("EU_EMIRTypeC"),
	
	/**
	 *  Denotes debt securities issued by Member States&#39; regional governments or local authorities whose exposures are treated as exposures to the central government of that Member State in accordance with Article 115(2) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeD") EU_EMIR_TYPE_D("EU_EMIRTypeD"),
	
	/**
	 *  Denotes debt securities issued by Member States&#39; public sector entities whose exposures are treated as exposures to the central government, regional government or local authority of that Member State in accordance with Article 116(4) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeE") EU_EMIR_TYPE_E("EU_EMIRTypeE"),
	
	/**
	 *  Denotes debt securities issued by Member States&#39; regional governments or local authorities other than those referred to in (TypeD.)
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeF") EU_EMIR_TYPE_F("EU_EMIRTypeF"),
	
	/**
	 *  Denotes debt securities issued by Member States&#39; public sector entities other than those referred to in (TypeE).
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeG") EU_EMIR_TYPE_G("EU_EMIRTypeG"),
	
	/**
	 *  Denotes debt securities issued by multilateral development banks listed in Article 117(2) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeH") EU_EMIR_TYPE_H("EU_EMIRTypeH"),
	
	/**
	 *  Denotes debt securities issued by the international organisations listed in Article 118 of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeI") EU_EMIR_TYPE_I("EU_EMIRTypeI"),
	
	/**
	 *  Denotes debt securities issued by third countries&#39; governments or central banks.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeJ") EU_EMIR_TYPE_J("EU_EMIRTypeJ"),
	
	/**
	 *  Denotes debt securities issued by third countries&#39; regional governments or local authorities that meet the requirements of (TypeD) and (TypeE).
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeK") EU_EMIR_TYPE_K("EU_EMIRTypeK"),
	
	/**
	 *  Denotes debt securities issued by third countries&#39; regional governments or local authorities other than those referred to in (TypeD) and (TypeE).
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeL") EU_EMIR_TYPE_L("EU_EMIRTypeL"),
	
	/**
	 *  Denotes debt securities issued by credit institutions or investment firms including bonds referred to in Article 52(4) of Directive 2009/65/EC of the European Parliament and of the Council.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeM") EU_EMIR_TYPE_M("EU_EMIRTypeM"),
	
	/**
	 *  Denotes corporate bonds.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeN") EU_EMIR_TYPE_N("EU_EMIRTypeN"),
	
	/**
	 *  Denotes the most senior tranche of a securitisation, as defined in Article 4(61) of Regulation (EU) No 575/2013, that is not a re-securitisation as defined in Article 4(63) of that Regulation.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeO") EU_EMIR_TYPE_O("EU_EMIRTypeO"),
	
	/**
	 *  Denotes convertible bonds provided that they can be converted only into equities which are included in an index specified pursuant to point (a) of Article 197 (8) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeP") EU_EMIR_TYPE_P("EU_EMIRTypeP"),
	
	/**
	 *  Denotes equities included in an index specified pursuant to point (a) of Article 197(8) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeQ") EU_EMIR_TYPE_Q("EU_EMIRTypeQ"),
	
	/**
	 *  Denotes shares or units in undertakings for collective investments in transferable securities (UCITS), provided that the conditions set out in Article 5 of EU Regulation 2016/2251 are met.
	 */
	@RosettaEnumValue(value = "EU_EMIRTypeR") EU_EMIR_TYPE_R("EU_EMIRTypeR")
;
	private static Map<String, EU_EMIR_EligibleCollateralEnum> values;
	static {
        Map<String, EU_EMIR_EligibleCollateralEnum> map = new ConcurrentHashMap<>();
		for (EU_EMIR_EligibleCollateralEnum instance : EU_EMIR_EligibleCollateralEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	EU_EMIR_EligibleCollateralEnum(String rosettaName) {
		this(rosettaName, null);
	}

	EU_EMIR_EligibleCollateralEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static EU_EMIR_EligibleCollateralEnum fromDisplayName(String name) {
		EU_EMIR_EligibleCollateralEnum value = values.get(name);
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
