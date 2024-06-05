package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of the master agreement governing the transaction.
 * @version ${project.version}
 */
@RosettaEnum("MasterAgreementTypeEnum")
public enum MasterAgreementTypeEnum {

	/**
	 * AFB Master Agreement for Foreign Exchange and Derivatives Transactions
	 */
	@RosettaEnumValue(value = "AFB") AFB("AFB"),
	
	/**
	 * A Bespoke (custom) Master Agreement, including one-off agreements for transactions
	 */
	@RosettaEnumValue(value = "Bespoke") BESPOKE("Bespoke"),
	
	/**
	 * Clearing Master Agreement
	 */
	@RosettaEnumValue(value = "CMA") CMA("CMA"),
	
	/**
	 * Contrato Marco de Operaciones Financieras
	 */
	@RosettaEnumValue(value = "CMOF") CMOF("CMOF"),
	
	/**
	 * EEI Master Power Purchase and Sale Agreement
	 */
	@RosettaEnumValue(value = "EEIPower") EEI_POWER("EEIPower"),
	
	/**
	 * EFET General Agreement Concerning the Delivery and Acceptance of Electricity
	 */
	@RosettaEnumValue(value = "EFETElectricity") EFET_ELECTRICITY("EFETElectricity"),
	
	/**
	 * EFET General Agreement Concerning The Delivery And Acceptance of Natural Gas
	 */
	@RosettaEnumValue(value = "EFETGas") EFET_GAS("EFETGas"),
	
	/**
	 * European Master Agreement and the Derivatives Annex (Banking Federation of the European Union)
	 */
	@RosettaEnumValue(value = "EMA") EMA("EMA"),
	
	/**
	 * Master Agreement Relating to transactions on Forward Financial Instruments (Federation Bancaire Francaise)
	 */
	@RosettaEnumValue(value = "FBF") FBF("FBF"),
	
	/**
	 * GasEDI Base Contract for Short-term Sale and Purchase of Natural Gas
	 */
	@RosettaEnumValue(value = "GasEDI") GAS_EDI("GasEDI"),
	
	/**
	 * German Master Agreement for Financial derivatives and Addendum for Options on Stock Exchange Indices or Securities
	 */
	@RosettaEnumValue(value = "German") GERMAN("German"),
	
	/**
	 * ICMA Global Master Agreement for REPO Trades
	 */
	@RosettaEnumValue(value = "GMRA") GMRA("GMRA"),
	
	/**
	 * ISLA Global Master Agreement for Securities Lending
	 */
	@RosettaEnumValue(value = "GMSLA") GMSLA("GMSLA"),
	
	/**
	 * FOA Grid Trade Master Agreement
	 */
	@RosettaEnumValue(value = "GTMA") GTMA("GTMA"),
	
	/**
	 * International Currency Options Market Master Agreement
	 */
	@RosettaEnumValue(value = "ICOM") ICOM("ICOM"),
	
	/**
	 * International Emissions Trading Association Emissions Reduction Purchase Agreement
	 */
	@RosettaEnumValue(value = "IETA_ERPA", displayName = "IETA-ERPA") IETA_ERPA("IETA_ERPA", "IETA-ERPA"),
	
	/**
	 * International Emissions Trading Association Emissions Trading Master Agreement
	 */
	@RosettaEnumValue(value = "IETA_ETMA", displayName = "IETA-ETMA") IETA_ETMA("IETA_ETMA", "IETA-ETMA"),
	
	/**
	 * International Emissions Trading Association International Emissions Trading Master Agreement
	 */
	@RosettaEnumValue(value = "IETA_IETMA", displayName = "IETA-IETMA") IETA_IETMA("IETA_IETMA", "IETA-IETMA"),
	
	/**
	 * International Foreign Exchange Master Agreement
	 */
	@RosettaEnumValue(value = "IFEMA") IFEMA("IFEMA"),
	
	/**
	 * International Foreign Exchange and Options Master Agreement
	 */
	@RosettaEnumValue(value = "IFEOMA") IFEOMA("IFEOMA"),
	
	/**
	 * ISDA Master Agreement
	 */
	@RosettaEnumValue(value = "ISDAMaster") ISDA_MASTER("ISDAMaster"),
	
	/**
	 * ISDA-FIA Cleared Derivatives Execution Agreement
	 */
	@RosettaEnumValue(value = "ISDAFIA_CDEA", displayName = "ISDAFIA-CDEA") ISDAFIA_CDEA("ISDAFIA_CDEA", "ISDAFIA-CDEA"),
	
	/**
	 * ISDA/IIFM Tahawwut (Hedging) Master Agreement (TMA)
	 */
	@RosettaEnumValue(value = "ISDAIIFM_TMA", displayName = "ISDAIIFM-TMA") ISDAIIFM_TMA("ISDAIIFM_TMA", "ISDAIIFM-TMA"),
	
	/**
	 * Master agreement of Japan Securities Clearing Corporation
	 */
	@RosettaEnumValue(value = "JSCC") JSCC("JSCC"),
	
	/**
	 * International Bullion Master Agreement Terms published by the London Bullion Market Association
	 */
	@RosettaEnumValue(value = "LBMA") LBMA("LBMA"),
	
	/**
	 * Leadership in Energy Automated Processing
	 */
	@RosettaEnumValue(value = "LEAP") LEAP("LEAP"),
	
	/**
	 * CTA Master Coal Purchase and Sales Agreement
	 */
	@RosettaEnumValue(value = "MCPSA") MCPSA("MCPSA"),
	
	/**
	 * NAESB Base Contract for Sale and Purchase of Natural Gas
	 */
	@RosettaEnumValue(value = "NAESBGas") NAESB_GAS("NAESBGas"),
	
	/**
	 * Short Term Flat NBP Trading Terms and Conditions
	 */
	@RosettaEnumValue(value = "NBP") NBP("NBP"),
	
	/**
	 * Standard Documentation for Derivative Transactions on the Russian Financial Markets
	 */
	@RosettaEnumValue(value = "RussianDerivatives") RUSSIAN_DERIVATIVES("RussianDerivatives"),
	
	/**
	 * Master Agreement and Contractual Terms for Repurchase Agreements on the Russian Financial Market
	 */
	@RosettaEnumValue(value = "RussianRepo") RUSSIAN_REPO("RussianRepo"),
	
	/**
	 * globalCOAL Standard Coal Trading Agreement
	 */
	@RosettaEnumValue(value = "SCoTA") S_CO_TA("SCoTA"),
	
	/**
	 * Swiss Master Agreement for OTC Derivatives Instruments
	 */
	@RosettaEnumValue(value = "Swiss") SWISS("Swiss"),
	
	/**
	 * TTF Hub Natural Gas Trading Terms and Conditions
	 */
	@RosettaEnumValue(value = "TTF") TTF("TTF"),
	
	/**
	 * Zeebrugge Hub Natural Gas Trading Terms and Conditions
	 */
	@RosettaEnumValue(value = "ZBT") ZBT("ZBT")
;
	private static Map<String, MasterAgreementTypeEnum> values;
	static {
        Map<String, MasterAgreementTypeEnum> map = new ConcurrentHashMap<>();
		for (MasterAgreementTypeEnum instance : MasterAgreementTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MasterAgreementTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MasterAgreementTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MasterAgreementTypeEnum fromDisplayName(String name) {
		MasterAgreementTypeEnum value = values.get(name);
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
