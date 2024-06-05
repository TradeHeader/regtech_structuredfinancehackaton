package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify a scheme of transaction types specified in the Equity Derivatives Settlement Matrix.
 * @version ${project.version}
 */
@RosettaEnum("MatrixTermEnum")
public enum MatrixTermEnum {

	/**
	 * Matrix Transaction Type of ASIA CORPORATE.
	 */
	@RosettaEnumValue(value = "AsiaCorporate") ASIA_CORPORATE("AsiaCorporate"),
	
	/**
	 * Matrix Transaction Type of ASIA FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "AsiaFinancialCorporate") ASIA_FINANCIAL_CORPORATE("AsiaFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of ASIA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "AsiaSovereign") ASIA_SOVEREIGN("AsiaSovereign"),
	
	/**
	 * Matrix Transaction Type of AUSTRALIA CORPORATE.
	 */
	@RosettaEnumValue(value = "AustraliaCorporate") AUSTRALIA_CORPORATE("AustraliaCorporate"),
	
	/**
	 * Matrix Transaction Type of AUSTRALIA FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "AustraliaFinancialCorporate") AUSTRALIA_FINANCIAL_CORPORATE("AustraliaFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of AUSTRALIA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "AustraliaSovereign") AUSTRALIA_SOVEREIGN("AustraliaSovereign"),
	
	/**
	 * Matrix Transaction Type of EMERGING EUROPEAN AND MIDDLE EASTERN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "EmergingEuropeanAndMiddleEasternSovereign") EMERGING_EUROPEAN_AND_MIDDLE_EASTERN_SOVEREIGN("EmergingEuropeanAndMiddleEasternSovereign"),
	
	/**
	 * Matrix Transaction Type of EMERGING EUROPEAN CORPORATE.
	 */
	@RosettaEnumValue(value = "EmergingEuropeanCorporate") EMERGING_EUROPEAN_CORPORATE("EmergingEuropeanCorporate"),
	
	/**
	 * Matrix Transaction Type of EMERGING EUROPEAN CORPORATE LPN.
	 */
	@RosettaEnumValue(value = "EmergingEuropeanCorporateLPN") EMERGING_EUROPEAN_CORPORATE_LPN("EmergingEuropeanCorporateLPN"),
	
	/**
	 * Matrix Transaction Type of EMERGING EUROPEAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "EmergingEuropeanFinancialCorporate") EMERGING_EUROPEAN_FINANCIAL_CORPORATE("EmergingEuropeanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of EMERGING EUROPEAN FINANCIAL CORPORATE LPN.
	 */
	@RosettaEnumValue(value = "EmergingEuropeanFinancialCorporateLPN") EMERGING_EUROPEAN_FINANCIAL_CORPORATE_LPN("EmergingEuropeanFinancialCorporateLPN"),
	
	/**
	 * Matrix Transaction Type of EUROPEAN COCO FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "EuropeanCoCoFinancialCorporate") EUROPEAN_CO_CO_FINANCIAL_CORPORATE("EuropeanCoCoFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of EUROPEAN CORPORATE.
	 */
	@RosettaEnumValue(value = "EuropeanCorporate") EUROPEAN_CORPORATE("EuropeanCorporate"),
	
	/**
	 * Matrix Transaction Type of EUROPEAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "EuropeanFinancialCorporate") EUROPEAN_FINANCIAL_CORPORATE("EuropeanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of EUROPEAN SENIOR NON PREFERRED FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "EuropeanSeniorNonPreferredFinancialCorporate") EUROPEAN_SENIOR_NON_PREFERRED_FINANCIAL_CORPORATE("EuropeanSeniorNonPreferredFinancialCorporate"),
	
	/**
	 * The ISDA-published 2011 Index Volatility Swap Agreement for Open Markets.
	 */
	@RosettaEnumValue(value = "IVS1OpenMarkets") IVS_1_OPEN_MARKETS("IVS1OpenMarkets"),
	
	/**
	 * Matrix Transaction Type of JAPAN CORPORATE.
	 */
	@RosettaEnumValue(value = "JapanCorporate") JAPAN_CORPORATE("JapanCorporate"),
	
	/**
	 * Matrix Transaction Type of JAPAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "JapanFinancialCorporate") JAPAN_FINANCIAL_CORPORATE("JapanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of JAPAN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "JapanSovereign") JAPAN_SOVEREIGN("JapanSovereign"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA CORPORATE.
	 */
	@RosettaEnumValue(value = "LatinAmericaCorporate") LATIN_AMERICA_CORPORATE("LatinAmericaCorporate"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA CORPORATE B.
	 */
	@RosettaEnumValue(value = "LatinAmericaCorporateBond") LATIN_AMERICA_CORPORATE_BOND("LatinAmericaCorporateBond"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA CORPORATE BL.
	 */
	@RosettaEnumValue(value = "LatinAmericaCorporateBondOrLoan") LATIN_AMERICA_CORPORATE_BOND_OR_LOAN("LatinAmericaCorporateBondOrLoan"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA FINANCIAL CORPORATE B.
	 */
	@RosettaEnumValue(value = "LatinAmericaFinancialCorporateBond") LATIN_AMERICA_FINANCIAL_CORPORATE_BOND("LatinAmericaFinancialCorporateBond"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA FINANCIAL CORPORATE BL.
	 */
	@RosettaEnumValue(value = "LatinAmericaFinancialCorporateBondOrLoan") LATIN_AMERICA_FINANCIAL_CORPORATE_BOND_OR_LOAN("LatinAmericaFinancialCorporateBondOrLoan"),
	
	/**
	 * Matrix Transaction Type of LATIN AMERICA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "LatinAmericaSovereign") LATIN_AMERICA_SOVEREIGN("LatinAmericaSovereign"),
	
	/**
	 * Matrix Transaction Type of NEW ZEALAND CORPORATE.
	 */
	@RosettaEnumValue(value = "NewZealandCorporate") NEW_ZEALAND_CORPORATE("NewZealandCorporate"),
	
	/**
	 * Matrix Transaction Type of NEW ZEALAND FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "NewZealandFinancialCorporate") NEW_ZEALAND_FINANCIAL_CORPORATE("NewZealandFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of NEW ZEALAND SOVEREIGN.
	 */
	@RosettaEnumValue(value = "NewZealandSovereign") NEW_ZEALAND_SOVEREIGN("NewZealandSovereign"),
	
	/**
	 * Matrix Transaction Type of NORTH AMERICAN CORPORATE.
	 */
	@RosettaEnumValue(value = "NorthAmericanCorporate") NORTH_AMERICAN_CORPORATE("NorthAmericanCorporate"),
	
	/**
	 * Matrix Transaction Type of NORTH AMERICAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "NorthAmericanFinancialCorporate") NORTH_AMERICAN_FINANCIAL_CORPORATE("NorthAmericanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of SINGAPORE CORPORATE.
	 */
	@RosettaEnumValue(value = "SingaporeCorporate") SINGAPORE_CORPORATE("SingaporeCorporate"),
	
	/**
	 * Matrix Transaction Type of SINGAPORE FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "SingaporeFinancialCorporate") SINGAPORE_FINANCIAL_CORPORATE("SingaporeFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of SINGAPORE SOVEREIGN.
	 */
	@RosettaEnumValue(value = "SingaporeSovereign") SINGAPORE_SOVEREIGN("SingaporeSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD ASIA CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardAsiaCorporate") STANDARD_ASIA_CORPORATE("StandardAsiaCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD ASIA FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardAsiaFinancialCorporate") STANDARD_ASIA_FINANCIAL_CORPORATE("StandardAsiaFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD ASIA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardAsiaSovereign") STANDARD_ASIA_SOVEREIGN("StandardAsiaSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD AUSTRALIA CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardAustraliaCorporate") STANDARD_AUSTRALIA_CORPORATE("StandardAustraliaCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD AUSTRALIA FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardAustraliaFinancialCorporate") STANDARD_AUSTRALIA_FINANCIAL_CORPORATE("StandardAustraliaFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD AUSTRALIA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardAustraliaSovereign") STANDARD_AUSTRALIA_SOVEREIGN("StandardAustraliaSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD EMERGING EUROPEAN AND MIDDLE EASTERN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardEmergingEuropeanAndMiddleEasternSovereign") STANDARD_EMERGING_EUROPEAN_AND_MIDDLE_EASTERN_SOVEREIGN("StandardEmergingEuropeanAndMiddleEasternSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD EMERGING EUROPEAN CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEmergingEuropeanCorporate") STANDARD_EMERGING_EUROPEAN_CORPORATE("StandardEmergingEuropeanCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD EMERGING EUROPEAN CORPORATE LPN.
	 */
	@RosettaEnumValue(value = "StandardEmergingEuropeanCorporateLPN") STANDARD_EMERGING_EUROPEAN_CORPORATE_LPN("StandardEmergingEuropeanCorporateLPN"),
	
	/**
	 * Matrix Transaction Type of STANDARD EMERGING EUROPEAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEmergingEuropeanFinancialCorporate") STANDARD_EMERGING_EUROPEAN_FINANCIAL_CORPORATE("StandardEmergingEuropeanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD EMERGING EUROPEAN FINANCIAL CORPORATE LPN.
	 */
	@RosettaEnumValue(value = "StandardEmergingEuropeanFinancialCorporateLPN") STANDARD_EMERGING_EUROPEAN_FINANCIAL_CORPORATE_LPN("StandardEmergingEuropeanFinancialCorporateLPN"),
	
	/**
	 * Matrix Transaction Type of STANDARD EUROPEAN COCO FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEuropeanCoCoFinancialCorporate") STANDARD_EUROPEAN_CO_CO_FINANCIAL_CORPORATE("StandardEuropeanCoCoFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD EUROPEAN CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEuropeanCorporate") STANDARD_EUROPEAN_CORPORATE("StandardEuropeanCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD EUROPEAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEuropeanFinancialCorporate") STANDARD_EUROPEAN_FINANCIAL_CORPORATE("StandardEuropeanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD EUROPEAN SENIOR NON PREFERRED FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardEuropeanSeniorNonPreferredFinancialCorporate") STANDARD_EUROPEAN_SENIOR_NON_PREFERRED_FINANCIAL_CORPORATE("StandardEuropeanSeniorNonPreferredFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD JAPAN CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardJapanCorporate") STANDARD_JAPAN_CORPORATE("StandardJapanCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD JAPAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardJapanFinancialCorporate") STANDARD_JAPAN_FINANCIAL_CORPORATE("StandardJapanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD JAPAN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardJapanSovereign") STANDARD_JAPAN_SOVEREIGN("StandardJapanSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD LATIN AMERICA CORPORATE B.
	 */
	@RosettaEnumValue(value = "StandardLatinAmericaCorporateBond") STANDARD_LATIN_AMERICA_CORPORATE_BOND("StandardLatinAmericaCorporateBond"),
	
	/**
	 * Matrix Transaction Type of STANDARD LATIN AMERICA CORPORATE BL.
	 */
	@RosettaEnumValue(value = "StandardLatinAmericaCorporateBondOrLoan") STANDARD_LATIN_AMERICA_CORPORATE_BOND_OR_LOAN("StandardLatinAmericaCorporateBondOrLoan"),
	
	/**
	 * Matrix Transaction Type of STANDARD LATIN AMERICA FINANCIAL CORPORATE B.
	 */
	@RosettaEnumValue(value = "StandardLatinAmericaFinancialCorporateBond") STANDARD_LATIN_AMERICA_FINANCIAL_CORPORATE_BOND("StandardLatinAmericaFinancialCorporateBond"),
	
	/**
	 * Matrix Transaction Type of STANDARD LATIN AMERICA FINANCIAL CORPORATE BL.
	 */
	@RosettaEnumValue(value = "StandardLatinAmericaFinancialCorporateBondOrLoan") STANDARD_LATIN_AMERICA_FINANCIAL_CORPORATE_BOND_OR_LOAN("StandardLatinAmericaFinancialCorporateBondOrLoan"),
	
	/**
	 * Matrix Transaction Type of STANDARD LATIN AMERICA SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardLatinAmericaSovereign") STANDARD_LATIN_AMERICA_SOVEREIGN("StandardLatinAmericaSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD NEW ZEALAND CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardNewZealandCorporate") STANDARD_NEW_ZEALAND_CORPORATE("StandardNewZealandCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD NEW ZEALAND FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardNewZealandFinancialCorporate") STANDARD_NEW_ZEALAND_FINANCIAL_CORPORATE("StandardNewZealandFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD NEW ZEALAND SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardNewZealandSovereign") STANDARD_NEW_ZEALAND_SOVEREIGN("StandardNewZealandSovereign"),
	
	/**
	 * Matrix Transaction Type of STANDARD NORTH AMERICAN CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardNorthAmericanCorporate") STANDARD_NORTH_AMERICAN_CORPORATE("StandardNorthAmericanCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD NORTH AMERICAN FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardNorthAmericanFinancialCorporate") STANDARD_NORTH_AMERICAN_FINANCIAL_CORPORATE("StandardNorthAmericanFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD SINGAPORE CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardSingaporeCorporate") STANDARD_SINGAPORE_CORPORATE("StandardSingaporeCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD SINGAPORE FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardSingaporeFinancialCorporate") STANDARD_SINGAPORE_FINANCIAL_CORPORATE("StandardSingaporeFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD SINGAPORE SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardSingaporeSovereign") STANDARD_SINGAPORE_SOVEREIGN("StandardSingaporeSovereign"),
	
	/**
	 * Transaction Type of STANDARD SUBORDINATED EUROPEAN INSURANCE CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardSubordinatedEuropeanInsuranceCorporate") STANDARD_SUBORDINATED_EUROPEAN_INSURANCE_CORPORATE("StandardSubordinatedEuropeanInsuranceCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD SUKUK FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "StandardSukukFinancialCorporate") STANDARD_SUKUK_FINANCIAL_CORPORATE("StandardSukukFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of STANDARD U.S. MUNICIPAL FULL FAITH AND CREDIT.
	 */
	@RosettaEnumValue(value = "StandardUSMunicipalFullFaithAndCredit") STANDARD_US_MUNICIPAL_FULL_FAITH_AND_CREDIT("StandardUSMunicipalFullFaithAndCredit"),
	
	/**
	 * Matrix Transaction Type of STANDARD U.S. MUNICIPAL GENERAL FUND.
	 */
	@RosettaEnumValue(value = "StandardUSMunicipalGeneralFund") STANDARD_US_MUNICIPAL_GENERAL_FUND("StandardUSMunicipalGeneralFund"),
	
	/**
	 * Matrix Transaction Type of STANDARD U.S. MUNICIPAL REVENUE.
	 */
	@RosettaEnumValue(value = "StandardUSMunicipalRevenue") STANDARD_US_MUNICIPAL_REVENUE("StandardUSMunicipalRevenue"),
	
	/**
	 * Matrix Transaction Type of STANDARD WESTERN EUROPEAN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "StandardWesternEuropeanSovereign") STANDARD_WESTERN_EUROPEAN_SOVEREIGN("StandardWesternEuropeanSovereign"),
	
	/**
	 * Matrix Transaction Type of SUBORDINATED EUROPEAN INSURANCE CORPORATE.
	 */
	@RosettaEnumValue(value = "SubordinatedEuropeanInsuranceCorporate") SUBORDINATED_EUROPEAN_INSURANCE_CORPORATE("SubordinatedEuropeanInsuranceCorporate"),
	
	/**
	 * Matrix Transaction Type of SUKUK CORPORATE.
	 */
	@RosettaEnumValue(value = "SukukCorporate") SUKUK_CORPORATE("SukukCorporate"),
	
	/**
	 * Matrix Transaction Type of SUKUK FINANCIAL CORPORATE.
	 */
	@RosettaEnumValue(value = "SukukFinancialCorporate") SUKUK_FINANCIAL_CORPORATE("SukukFinancialCorporate"),
	
	/**
	 * Matrix Transaction Type of SUKUK SOVEREIGN.
	 */
	@RosettaEnumValue(value = "SukukSovereign") SUKUK_SOVEREIGN("SukukSovereign"),
	
	/**
	 * Matrix Transaction Type of U.S. MUNICIPAL FULL FAITH AND CREDIT.
	 */
	@RosettaEnumValue(value = "USMunicipalFullFaithAndCredit") US_MUNICIPAL_FULL_FAITH_AND_CREDIT("USMunicipalFullFaithAndCredit"),
	
	/**
	 * Matrix Transaction Type of U.S. MUNICIPAL GENERAL FUND.
	 */
	@RosettaEnumValue(value = "USMunicipalGeneralFund") US_MUNICIPAL_GENERAL_FUND("USMunicipalGeneralFund"),
	
	/**
	 * Matrix Transaction Type of U.S. MUNICIPAL REVENUE.
	 */
	@RosettaEnumValue(value = "USMunicipalRevenue") US_MUNICIPAL_REVENUE("USMunicipalRevenue"),
	
	/**
	 * Matrix Transaction Type of WESTERN EUROPEAN SOVEREIGN.
	 */
	@RosettaEnumValue(value = "WesternEuropeanSovereign") WESTERN_EUROPEAN_SOVEREIGN("WesternEuropeanSovereign")
;
	private static Map<String, MatrixTermEnum> values;
	static {
        Map<String, MatrixTermEnum> map = new ConcurrentHashMap<>();
		for (MatrixTermEnum instance : MatrixTermEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MatrixTermEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MatrixTermEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MatrixTermEnum fromDisplayName(String name) {
		MatrixTermEnum value = values.get(name);
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
