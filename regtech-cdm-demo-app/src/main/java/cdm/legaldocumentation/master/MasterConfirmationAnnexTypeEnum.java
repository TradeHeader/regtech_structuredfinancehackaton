package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of annex to be used with master confirmation agreement governing the transaction.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/master-confirmation-annex-type"
 *
 * Provision 
 *
 */
@RosettaEnum("MasterConfirmationAnnexTypeEnum")
public enum MasterConfirmationAnnexTypeEnum {

	/**
	 * The Index Variance Swap 2004 Annex to the ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement and to the Revised ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2004IndexVarianceSwapAmericasInterdealer") ISDA_2004_INDEX_VARIANCE_SWAP_AMERICAS_INTERDEALER("ISDA2004IndexVarianceSwapAmericasInterdealer"),
	
	/**
	 * The Share Variance Swap 2004 Annex to the ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement and to the Revised ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2004ShareVarianceSwapAmericasInterdealer") ISDA_2004_SHARE_VARIANCE_SWAP_AMERICAS_INTERDEALER("ISDA2004ShareVarianceSwapAmericasInterdealer"),
	
	/**
	 * The Dispersion Variance Swap Annex to the Revised 2007 ISDA European Variance Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007DispersionVarianceSwapEuropean") ISDA_2007_DISPERSION_VARIANCE_SWAP_EUROPEAN("ISDA2007DispersionVarianceSwapEuropean"),
	
	/**
	 * The EFS (Equity Share Finance Swap) 2007 Annex to the ISDA 2007 European Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007EquityFinanceSwapEuropean") ISDA_2007_EQUITY_FINANCE_SWAP_EUROPEAN("ISDA2007EquityFinanceSwapEuropean"),
	
	/**
	 * The Index Variance Swap 2007 Annex to the Revised ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007IndexVarianceSwapAmericasInterdealer") ISDA_2007_INDEX_VARIANCE_SWAP_AMERICAS_INTERDEALER("ISDA2007IndexVarianceSwapAmericasInterdealer"),
	
	/**
	 * The Share Variance Swap 2007 Annex to the Revised ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007ShareVarianceSwapAmericasInterdealer") ISDA_2007_SHARE_VARIANCE_SWAP_AMERICAS_INTERDEALER("ISDA2007ShareVarianceSwapAmericasInterdealer"),
	
	/**
	 * The Variance Option Standard Terms Appendix to the Revised ISDA 2007 European Variance Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceOptionEuropean") ISDA_2007_VARIANCE_OPTION_EUROPEAN("ISDA2007VarianceOptionEuropean"),
	
	/**
	 * The Cash-settled Open Market EFS (Equity Finance Share Swap) 2008 Annex to the ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityFinanceSwapAsiaExcludingJapan") ISDA_2008_EQUITY_FINANCE_SWAP_ASIA_EXCLUDING_JAPAN("ISDA2008EquityFinanceSwapAsiaExcludingJapan"),
	
	/**
	 * The Cash-settled Open Market EFS (Equity Finance Share Swap) Annex to the Revised ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityFinanceSwapAsiaExcludingJapanRev1") ISDA_2008_EQUITY_FINANCE_SWAP_ASIA_EXCLUDING_JAPAN_REV_1("ISDA2008EquityFinanceSwapAsiaExcludingJapanRev1"),
	
	/**
	 * The Open Market Equity Option 2008 Annex to the ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityOptionAsiaExcludingJapan") ISDA_2008_EQUITY_OPTION_ASIA_EXCLUDING_JAPAN("ISDA2008EquityOptionAsiaExcludingJapan"),
	
	/**
	 * The Open Market Equity Option Annex to the Revised ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityOptionAsiaExcludingJapanRev1") ISDA_2008_EQUITY_OPTION_ASIA_EXCLUDING_JAPAN_REV_1("ISDA2008EquityOptionAsiaExcludingJapanRev1"),
	
	/**
	 * The Equity Option 2008 Annex to the ISDA 2008 Japanese Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityOptionJapan") ISDA_2008_EQUITY_OPTION_JAPAN("ISDA2008EquityOptionJapan"),
	
	/**
	 * The Cash-settled Closed Market Index and Share Options 2009 Annex to the Revised ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009ClosedMarketsOptionsAsiaExcludingJapan") ISDA_2009_CLOSED_MARKETS_OPTIONS_ASIA_EXCLUDING_JAPAN("ISDA2009ClosedMarketsOptionsAsiaExcludingJapan"),
	
	/**
	 * The Interdealer Share Swap 2009 Annex to the ISDA 2009 European Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009EquityEuropeanInterdealerSS") ISDA_2009_EQUITY_EUROPEAN_INTERDEALER_SS("ISDA2009EquityEuropeanInterdealerSS"),
	
	/**
	 * The Index Swap 2009 Annex to the ISDA 2007 European Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009EquityEuropeanIS") ISDA_2009_EQUITY_EUROPEAN_IS("ISDA2009EquityEuropeanIS"),
	
	/**
	 * The Index and Share Options 2009 Annex to the ISDA 2009 Americas Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009IndexShareOptionAmericas") ISDA_2009_INDEX_SHARE_OPTION_AMERICAS("ISDA2009IndexShareOptionAmericas"),
	
	/**
	 * The Interdealer Index Swap 2009 Annex to the ISDA 2009 European Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009IndexSwapEuropeanInterdealer") ISDA_2009_INDEX_SWAP_EUROPEAN_INTERDEALER("ISDA2009IndexSwapEuropeanInterdealer"),
	
	/**
	 * The Index Swap 2009 Annex to the ISDA 2009 Pan-Asia Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009IndexSwapPanAsiaInterdealer") ISDA_2009_INDEX_SWAP_PAN_ASIA_INTERDEALER("ISDA2009IndexSwapPanAsiaInterdealer"),
	
	/**
	 * The Share Swap 2009 Annex to the ISDA 2009 Pan-Asia Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009ShareSwapPanAsia") ISDA_2009_SHARE_SWAP_PAN_ASIA("ISDA2009ShareSwapPanAsia"),
	
	/**
	 * The Fair Value Interdealer Share Swap 2010 Annex to the ISDA 2009 European Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2010FairValueShareSwapEuropeanInterdealer") ISDA_2010_FAIR_VALUE_SHARE_SWAP_EUROPEAN_INTERDEALER("ISDA2010FairValueShareSwapEuropeanInterdealer"),
	
	/**
	 * The Cash-settled Index Option/Cash/Physically-settled Share Option 2010 Annex to the ISDA 2010 EMEA EM Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2010IndexShareOptionEMEAInterdealer") ISDA_2010_INDEX_SHARE_OPTION_EMEA_INTERDEALER("ISDA2010IndexShareOptionEMEAInterdealer")
;
	private static Map<String, MasterConfirmationAnnexTypeEnum> values;
	static {
        Map<String, MasterConfirmationAnnexTypeEnum> map = new ConcurrentHashMap<>();
		for (MasterConfirmationAnnexTypeEnum instance : MasterConfirmationAnnexTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MasterConfirmationAnnexTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MasterConfirmationAnnexTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MasterConfirmationAnnexTypeEnum fromDisplayName(String name) {
		MasterConfirmationAnnexTypeEnum value = values.get(name);
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
