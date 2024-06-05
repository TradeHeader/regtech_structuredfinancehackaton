package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Identifies United Kingdom Eligible Collateral Assets classification categories based on UK Onshored EMIR Uncleared Margin Rules. Eligible Collateral asset classes for both initial margin (IM) and variation margin (VM) posted and collected between specified entities. Please note: UK EMIR regulation will detail which eligible collateral assets classes apply to each type of entity pairing (counterparty) and which apply to posting of IM and VM.
 * @version ${project.version}
 */
@RosettaEnum("UK_EMIR_EligibleCollateralEnum")
public enum UK_EMIR_EligibleCollateralEnum {

	/**
	 * Denotes cash in the form of money credited to an account in any currency, or similar claims for the repayment of money, such as money market deposits.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeA") UK_EMIR_TYPE_A("UK_EMIRTypeA"),
	
	/**
	 * Denotes gold in the form of allocated pure gold bullion of recognised good delivery.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeB") UK_EMIR_TYPE_B("UK_EMIRTypeB"),
	
	/**
	 * Denotes debt securities issued by the central government of the United Kingdom or the Bank of England.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeC") UK_EMIR_TYPE_C("UK_EMIRTypeC"),
	
	/**
	 * Denotes debt securities issued by United Kingdom regional governments or local authorities whose exposures are treated as exposures to the central government of the United Kingdom in accordance with Article 115(2) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeD") UK_EMIR_TYPE_D("UK_EMIRTypeD"),
	
	/**
	 * Denotes debt securities issued by United Kingdom public sector entities whose exposures are treated as exposures to the central government, regional government or local authority of the United Kingdom in accordance with Article 116(4) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeE") UK_EMIR_TYPE_E("UK_EMIRTypeE"),
	
	/**
	 * Denotes debt securities issued by United Kingdom regional governments or local authorities other than those referred to in (TypeD).
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeF") UK_EMIR_TYPE_F("UK_EMIRTypeF"),
	
	/**
	 * Denotes debt securities issued by United Kingdom public sector entities other than those referred to in (TypeE).
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeG") UK_EMIR_TYPE_G("UK_EMIRTypeG"),
	
	/**
	 * Denotes debt securities issued by multilateral development banks listed in Article 117(2) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeH") UK_EMIR_TYPE_H("UK_EMIRTypeH"),
	
	/**
	 * Denotes debt securities issued by the international organisations listed in Article 118 of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeI") UK_EMIR_TYPE_I("UK_EMIRTypeI"),
	
	/**
	 * Denotes debt securities issued by third countries&#39; governments or central banks.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeJ") UK_EMIR_TYPE_J("UK_EMIRTypeJ"),
	
	/**
	 * Denotes debt securities issued by third countries&#39; regional governments or local authorities that meet the requirements of (TypeD) and (TypeE).
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeK") UK_EMIR_TYPE_K("UK_EMIRTypeK"),
	
	/**
	 * Denotes debt securities issued by third countries&#39; regional governments or local authorities other than those referred to in (TypeD) and (TypeE).
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeL") UK_EMIR_TYPE_L("UK_EMIRTypeL"),
	
	/**
	 * Denotes debt securities issued by credit institutions or investment firms including bonds admitted to the register of regulated covered bonds maintained under Regulation 7(1)(b) of the Regulated Covered Bonds Regulations 2008 (SI 2008/346).
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeM") UK_EMIR_TYPE_M("UK_EMIRTypeM"),
	
	/**
	 * Denotes corporate bonds.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeN") UK_EMIR_TYPE_N("UK_EMIRTypeN"),
	
	/**
	 * Denotes the most senior tranche of a securitisation, as defined in Article 4(61) of Regulation (EU) No 575/2013, that is not a re-securitisation as defined in Article 4(63) of that Regulation.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeO") UK_EMIR_TYPE_O("UK_EMIRTypeO"),
	
	/**
	 * Denotes convertible bonds provided that they can be converted only into equities which are included in an index specified pursuant to point (a) of Article 197 (8) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeP") UK_EMIR_TYPE_P("UK_EMIRTypeP"),
	
	/**
	 * Denotes equities included in an index specified pursuant to point (a) of Article 197(8) of Regulation (EU) No 575/2013.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeQ") UK_EMIR_TYPE_Q("UK_EMIRTypeQ"),
	
	/**
	 * Denotes shares or units in undertakings for UK UCITS, provided that the conditions set out in Article 5 of EU Regulation 2016/2251 (as modified by the Technical Standards (European Market Infrastructure) (EU Exit) (No. 3) Instrument 2019) are met.
	 */
	@RosettaEnumValue(value = "UK_EMIRTypeR") UK_EMIR_TYPE_R("UK_EMIRTypeR")
;
	private static Map<String, UK_EMIR_EligibleCollateralEnum> values;
	static {
        Map<String, UK_EMIR_EligibleCollateralEnum> map = new ConcurrentHashMap<>();
		for (UK_EMIR_EligibleCollateralEnum instance : UK_EMIR_EligibleCollateralEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	UK_EMIR_EligibleCollateralEnum(String rosettaName) {
		this(rosettaName, null);
	}

	UK_EMIR_EligibleCollateralEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static UK_EMIR_EligibleCollateralEnum fromDisplayName(String name) {
		UK_EMIR_EligibleCollateralEnum value = values.get(name);
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
