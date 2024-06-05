package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of master confirmation agreement governing the transaction. While FpML positions the date a prefix, the CDM positions it as the suffix to handle grammar type constraints.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/master-confirmation-type"
 *
 * Provision 
 *
 */
@RosettaEnum("MasterConfirmationTypeEnum")
public enum MasterConfirmationTypeEnum {

	/**
	 * Used for CDS Index trades. Relevant Master Confirmation determined by the contents of the creditDefaultSwap element. Best practice is to use the most specific code that applies.
	 */
	@RosettaEnumValue(value = "_2003CreditIndex", displayName = "2003CreditIndex") _2003_CREDIT_INDEX("_2003CreditIndex", "2003CreditIndex"),
	
	/**
	 * A privately negotiated European Interdealer Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "_2004EquityEuropeanInterdealer", displayName = "2004EquityEuropeanInterdealer") _2004_EQUITY_EUROPEAN_INTERDEALER("_2004EquityEuropeanInterdealer", "2004EquityEuropeanInterdealer"),
	
	/**
	 * A privately negotiated European Interdealer Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "_2005VarianceSwapEuropeanInterdealer", displayName = "2005VarianceSwapEuropeanInterdealer") _2005_VARIANCE_SWAP_EUROPEAN_INTERDEALER("_2005VarianceSwapEuropeanInterdealer", "2005VarianceSwapEuropeanInterdealer"),
	
	/**
	 * A European Interdealer Master Confirmation Agreement not defined by ISDA, and modified by the parties to the transaction applies.
	 */
	@RosettaEnumValue(value = "_2006DividendSwapEuropean", displayName = "2006DividendSwapEuropean") _2006_DIVIDEND_SWAP_EUROPEAN("_2006DividendSwapEuropean", "2006DividendSwapEuropean"),
	
	/**
	 * A European Interdealer Master Confirmation Agreement not defined by ISDA applies.
	 */
	@RosettaEnumValue(value = "_2006DividendSwapEuropeanInterdealer", displayName = "2006DividendSwapEuropeanInterdealer") _2006_DIVIDEND_SWAP_EUROPEAN_INTERDEALER("_2006DividendSwapEuropeanInterdealer", "2006DividendSwapEuropeanInterdealer"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value AsiaCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditAsia", displayName = "2014CreditAsia") _2014_CREDIT_ASIA("_2014CreditAsia", "2014CreditAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value AsiaFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditAsiaFinancial", displayName = "2014CreditAsiaFinancial") _2014_CREDIT_ASIA_FINANCIAL("_2014CreditAsiaFinancial", "2014CreditAsiaFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value AustraliaCorporate/NewZealandCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditAustraliaNewZealand", displayName = "2014CreditAustraliaNewZealand") _2014_CREDIT_AUSTRALIA_NEW_ZEALAND("_2014CreditAustraliaNewZealand", "2014CreditAustraliaNewZealand"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value AustraliaFinancialCorporate/NewZealandFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditAustraliaNewZealandFinancial", displayName = "2014CreditAustraliaNewZealandFinancial") _2014_CREDIT_AUSTRALIA_NEW_ZEALAND_FINANCIAL("_2014CreditAustraliaNewZealandFinancial", "2014CreditAustraliaNewZealandFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value EuropeanCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditEuropean", displayName = "2014CreditEuropean") _2014_CREDIT_EUROPEAN("_2014CreditEuropean", "2014CreditEuropean"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value EuropeanCoCoFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditEuropeanCoCoFinancial", displayName = "2014CreditEuropeanCoCoFinancial") _2014_CREDIT_EUROPEAN_CO_CO_FINANCIAL("_2014CreditEuropeanCoCoFinancial", "2014CreditEuropeanCoCoFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value EuropeanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditEuropeanFinancial", displayName = "2014CreditEuropeanFinancial") _2014_CREDIT_EUROPEAN_FINANCIAL("_2014CreditEuropeanFinancial", "2014CreditEuropeanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value JapanCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditJapan", displayName = "2014CreditJapan") _2014_CREDIT_JAPAN("_2014CreditJapan", "2014CreditJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value JapanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditJapanFinancial", displayName = "2014CreditJapanFinancial") _2014_CREDIT_JAPAN_FINANCIAL("_2014CreditJapanFinancial", "2014CreditJapanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value NorthAmericanCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditNorthAmerican", displayName = "2014CreditNorthAmerican") _2014_CREDIT_NORTH_AMERICAN("_2014CreditNorthAmerican", "2014CreditNorthAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value NorthAmericanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditNorthAmericanFinancial", displayName = "2014CreditNorthAmericanFinancial") _2014_CREDIT_NORTH_AMERICAN_FINANCIAL("_2014CreditNorthAmericanFinancial", "2014CreditNorthAmericanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values SingaporeCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditSingapore", displayName = "2014CreditSingapore") _2014_CREDIT_SINGAPORE("_2014CreditSingapore", "2014CreditSingapore"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values SingaporeFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014CreditSingaporeFinancial", displayName = "2014CreditSingaporeFinancial") _2014_CREDIT_SINGAPORE_FINANCIAL("_2014CreditSingaporeFinancial", "2014CreditSingaporeFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value AsiaSovereign.
	 */
	@RosettaEnumValue(value = "_2014CreditSovereignAsia", displayName = "2014CreditSovereignAsia") _2014_CREDIT_SOVEREIGN_ASIA("_2014CreditSovereignAsia", "2014CreditSovereignAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value EmergingEuropeanAndMiddleEasternSovereign.
	 */
	@RosettaEnumValue(value = "_2014CreditSovereignEmergingEuropeanAndMiddleEastern", displayName = "2014CreditSovereignEmergingEuropeanAndMiddleEastern") _2014_CREDIT_SOVEREIGN_EMERGING_EUROPEAN_AND_MIDDLE_EASTERN("_2014CreditSovereignEmergingEuropeanAndMiddleEastern", "2014CreditSovereignEmergingEuropeanAndMiddleEastern"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value JapanSovereign.
	 */
	@RosettaEnumValue(value = "_2014CreditSovereignJapan", displayName = "2014CreditSovereignJapan") _2014_CREDIT_SOVEREIGN_JAPAN("_2014CreditSovereignJapan", "2014CreditSovereignJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value LatinAmericaSovereign.
	 */
	@RosettaEnumValue(value = "_2014CreditSovereignLatinAmerican", displayName = "2014CreditSovereignLatinAmerican") _2014_CREDIT_SOVEREIGN_LATIN_AMERICAN("_2014CreditSovereignLatinAmerican", "2014CreditSovereignLatinAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value WesternEuropeanSovereign.
	 */
	@RosettaEnumValue(value = "_2014CreditSovereignWesternEuropean", displayName = "2014CreditSovereignWesternEuropean") _2014_CREDIT_SOVEREIGN_WESTERN_EUROPEAN("_2014CreditSovereignWesternEuropean", "2014CreditSovereignWesternEuropean"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAsiaCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditAsia", displayName = "2014StandardCreditAsia") _2014_STANDARD_CREDIT_ASIA("_2014StandardCreditAsia", "2014StandardCreditAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAsiaFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditAsiaFinancial", displayName = "2014StandardCreditAsiaFinancial") _2014_STANDARD_CREDIT_ASIA_FINANCIAL("_2014StandardCreditAsiaFinancial", "2014StandardCreditAsiaFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAustraliaCorporate and StandardNewZealandCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditAustraliaNewZealand", displayName = "2014StandardCreditAustraliaNewZealand") _2014_STANDARD_CREDIT_AUSTRALIA_NEW_ZEALAND("_2014StandardCreditAustraliaNewZealand", "2014StandardCreditAustraliaNewZealand"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAustraliaFinancialCorporate and StandardNewZealandFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditAustraliaNewZealandFinancial", displayName = "2014StandardCreditAustraliaNewZealandFinancial") _2014_STANDARD_CREDIT_AUSTRALIA_NEW_ZEALAND_FINANCIAL("_2014StandardCreditAustraliaNewZealandFinancial", "2014StandardCreditAustraliaNewZealandFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEuropeanCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditEuropean", displayName = "2014StandardCreditEuropean") _2014_STANDARD_CREDIT_EUROPEAN("_2014StandardCreditEuropean", "2014StandardCreditEuropean"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEuropeanCoCoFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditEuropeanCoCoFinancial", displayName = "2014StandardCreditEuropeanCoCoFinancial") _2014_STANDARD_CREDIT_EUROPEAN_CO_CO_FINANCIAL("_2014StandardCreditEuropeanCoCoFinancial", "2014StandardCreditEuropeanCoCoFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEuropeanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditEuropeanFinancial", displayName = "2014StandardCreditEuropeanFinancial") _2014_STANDARD_CREDIT_EUROPEAN_FINANCIAL("_2014StandardCreditEuropeanFinancial", "2014StandardCreditEuropeanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardJapanCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditJapan", displayName = "2014StandardCreditJapan") _2014_STANDARD_CREDIT_JAPAN("_2014StandardCreditJapan", "2014StandardCreditJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardJapanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditJapanFinancial", displayName = "2014StandardCreditJapanFinancial") _2014_STANDARD_CREDIT_JAPAN_FINANCIAL("_2014StandardCreditJapanFinancial", "2014StandardCreditJapanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardNorthAmericanCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditNorthAmerican", displayName = "2014StandardCreditNorthAmerican") _2014_STANDARD_CREDIT_NORTH_AMERICAN("_2014StandardCreditNorthAmerican", "2014StandardCreditNorthAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value standardNorthAmericanFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditNorthAmericanFinancial", displayName = "2014StandardCreditNorthAmericanFinancial") _2014_STANDARD_CREDIT_NORTH_AMERICAN_FINANCIAL("_2014StandardCreditNorthAmericanFinancial", "2014StandardCreditNorthAmericanFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardSingaporeCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSingapore", displayName = "2014StandardCreditSingapore") _2014_STANDARD_CREDIT_SINGAPORE("_2014StandardCreditSingapore", "2014StandardCreditSingapore"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardSingaporeFinancialCorporate.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSingaporeFinancial", displayName = "2014StandardCreditSingaporeFinancial") _2014_STANDARD_CREDIT_SINGAPORE_FINANCIAL("_2014StandardCreditSingaporeFinancial", "2014StandardCreditSingaporeFinancial"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardAsiaSovereign.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSovereignAsia", displayName = "2014StandardCreditSovereignAsia") _2014_STANDARD_CREDIT_SOVEREIGN_ASIA("_2014StandardCreditSovereignAsia", "2014StandardCreditSovereignAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEmergingEuropeanAndMiddleEasternSovereign.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSovereignEmergingEuropeanAndMiddleEastern", displayName = "2014StandardCreditSovereignEmergingEuropeanAndMiddleEastern") _2014_STANDARD_CREDIT_SOVEREIGN_EMERGING_EUROPEAN_AND_MIDDLE_EASTERN("_2014StandardCreditSovereignEmergingEuropeanAndMiddleEastern", "2014StandardCreditSovereignEmergingEuropeanAndMiddleEastern"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardJapanSovereign.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSovereignJapan", displayName = "2014StandardCreditSovereignJapan") _2014_STANDARD_CREDIT_SOVEREIGN_JAPAN("_2014StandardCreditSovereignJapan", "2014StandardCreditSovereignJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardLatinAmericaSovereign.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSovereignLatinAmerican", displayName = "2014StandardCreditSovereignLatinAmerican") _2014_STANDARD_CREDIT_SOVEREIGN_LATIN_AMERICAN("_2014StandardCreditSovereignLatinAmerican", "2014StandardCreditSovereignLatinAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardWesternEuropeanSovereign.
	 */
	@RosettaEnumValue(value = "_2014StandardCreditSovereignWesternEuropean", displayName = "2014StandardCreditSovereignWesternEuropean") _2014_STANDARD_CREDIT_SOVEREIGN_WESTERN_EUROPEAN("_2014StandardCreditSovereignWesternEuropean", "2014StandardCreditSovereignWesternEuropean"),
	
	/**
	 * Used for CDS Index trades executed under the Dow Jones CDX Emerging Markets Master Confirmation.
	 */
	@RosettaEnumValue(value = "DJ_CDX_EM", displayName = "DJ.CDX.EM") DJ_CDX_EM("DJ_CDX_EM", "DJ.CDX.EM"),
	
	/**
	 * Used for CDS Index trades executed under the Dow Jones CDX Emerging Markets Diversified Master Confirmation.
	 */
	@RosettaEnumValue(value = "DJ_CDX_EM_DIV", displayName = "DJ.CDX.EM.DIV") DJ_CDX_EM_DIV("DJ_CDX_EM_DIV", "DJ.CDX.EM.DIV"),
	
	/**
	 * Used for CDS Index trades executed under the Dow Jones CDX Master Confirmation that covers CDX.NA.IG, CDX.NA.HY, and CDX.NA.XO.
	 */
	@RosettaEnumValue(value = "DJ_CDX_NA", displayName = "DJ.CDX.NA") DJ_CDX_NA("DJ_CDX_NA", "DJ.CDX.NA"),
	
	/**
	 * Used for CDS Index trades executed under the Dow Jones iTraxx Europe Master Confirmation Agreement.
	 */
	@RosettaEnumValue(value = "DJ_iTraxx_Europe", displayName = "DJ.iTraxx.Europe") DJ_I_TRAXX_EUROPE("DJ_iTraxx_Europe", "DJ.iTraxx.Europe"),
	
	/**
	 * A general reference to the types of Americas Master Confirmation Agreements. Use the more specific values to reference a specific type of Americas Master Confirmation Agreement.
	 */
	@RosettaEnumValue(value = "EquityAmericas") EQUITY_AMERICAS("EquityAmericas"),
	
	/**
	 * A general reference to the types of Asia Master Confirmation Agreements. Use the more specific values to reference a specific type of Asia Master Confirmation Agreement.
	 */
	@RosettaEnumValue(value = "EquityAsia") EQUITY_ASIA("EquityAsia"),
	
	/**
	 * A general reference to the types of European Master Confirmation Agreements. Use the more specific values to reference a specific type of European Master Confirmation Agreement.
	 */
	@RosettaEnumValue(value = "EquityEuropean") EQUITY_EUROPEAN("EquityEuropean"),
	
	/**
	 * ISDA 1999 Master Credit Derivatives Confirmation Agreement
	 */
	@RosettaEnumValue(value = "ISDA1999Credit") ISDA_1999_CREDIT("ISDA1999Credit"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Asia had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditAsia") ISDA_2003_CREDIT_ASIA("ISDA2003CreditAsia"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Australia and New Zealand had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditAustraliaNewZealand") ISDA_2003_CREDIT_AUSTRALIA_NEW_ZEALAND("ISDA2003CreditAustraliaNewZealand"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if European had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditEuropean") ISDA_2003_CREDIT_EUROPEAN("ISDA2003CreditEuropean"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Japan had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditJapan") ISDA_2003_CREDIT_JAPAN("ISDA2003CreditJapan"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if North American had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditNorthAmerican") ISDA_2003_CREDIT_NORTH_AMERICAN("ISDA2003CreditNorthAmerican"),
	
	/**
	 * ISDA 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Singapore had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSingapore") ISDA_2003_CREDIT_SINGAPORE("ISDA2003CreditSingapore"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Asia had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignAsia") ISDA_2003_CREDIT_SOVEREIGN_ASIA("ISDA2003CreditSovereignAsia"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Central and Eastern Europe had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignCentralAndEasternEurope") ISDA_2003_CREDIT_SOVEREIGN_CENTRAL_AND_EASTERN_EUROPE("ISDA2003CreditSovereignCentralAndEasternEurope"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Japan had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignJapan") ISDA_2003_CREDIT_SOVEREIGN_JAPAN("ISDA2003CreditSovereignJapan"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Latin America had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignLatinAmerica") ISDA_2003_CREDIT_SOVEREIGN_LATIN_AMERICA("ISDA2003CreditSovereignLatinAmerica"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Middle East had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignMiddleEast") ISDA_2003_CREDIT_SOVEREIGN_MIDDLE_EAST("ISDA2003CreditSovereignMiddleEast"),
	
	/**
	 * ISDA Sovereign 2003 Master Credit Derivatives Confirmation Agreement interpreted as if Western Europe had been specified as the relevant Transaction Type in the Transaction Supplement. The 2003 Sovereign Master Confirmation has been superceded by the 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditSovereignWesternEurope") ISDA_2003_CREDIT_SOVEREIGN_WESTERN_EUROPE("ISDA2003CreditSovereignWesternEurope"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAsiaCorporate.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditAsia") ISDA_2003_STANDARD_CREDIT_ASIA("ISDA2003StandardCreditAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAustraliaCorporate/Sovereign and StandardNewZealandCorporate/Sovereign.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditAustraliaNewZealand") ISDA_2003_STANDARD_CREDIT_AUSTRALIA_NEW_ZEALAND("ISDA2003StandardCreditAustraliaNewZealand"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEuropeanCorporate.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditEuropean") ISDA_2003_STANDARD_CREDIT_EUROPEAN("ISDA2003StandardCreditEuropean"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardJapanCorporate.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditJapan") ISDA_2003_STANDARD_CREDIT_JAPAN("ISDA2003StandardCreditJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardNorthAmericanCorporate.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditNorthAmerican") ISDA_2003_STANDARD_CREDIT_NORTH_AMERICAN("ISDA2003StandardCreditNorthAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardSingaporeCorporate/Sovereign.
	 */
	@RosettaEnumValue(value = "ISDA2003StandardCreditSingapore") ISDA_2003_STANDARD_CREDIT_SINGAPORE("ISDA2003StandardCreditSingapore"),
	
	/**
	 * ISDA Sovereign 2004 Master Credit Derivatives Confirmation Agreement interpreted as if Asia had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2004CreditSovereignAsia") ISDA_2004_CREDIT_SOVEREIGN_ASIA("ISDA2004CreditSovereignAsia"),
	
	/**
	 * ISDA Sovereign 2004 Master Credit Derivatives Confirmation Agreement interpreted as if Emerging European and Middle Eastern had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2004CreditSovereignEmergingEuropeanAndMiddleEastern") ISDA_2004_CREDIT_SOVEREIGN_EMERGING_EUROPEAN_AND_MIDDLE_EASTERN("ISDA2004CreditSovereignEmergingEuropeanAndMiddleEastern"),
	
	/**
	 * ISDA Sovereign 2004 Master Credit Derivatives Confirmation Agreement interpreted as if Japan had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2004CreditSovereignJapan") ISDA_2004_CREDIT_SOVEREIGN_JAPAN("ISDA2004CreditSovereignJapan"),
	
	/**
	 * ISDA Sovereign 2004 Master Credit Derivatives Confirmation Agreement interpreted as if Latin American had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2004CreditSovereignLatinAmerican") ISDA_2004_CREDIT_SOVEREIGN_LATIN_AMERICAN("ISDA2004CreditSovereignLatinAmerican"),
	
	/**
	 * ISDA Sovereign 2004 Master Credit Derivatives Confirmation Agreement interpreted as if Western European had been specified as the relevant Transaction Type in the Transaction Supplement.
	 */
	@RosettaEnumValue(value = "ISDA2004CreditSovereignWesternEuropean") ISDA_2004_CREDIT_SOVEREIGN_WESTERN_EUROPEAN("ISDA2004CreditSovereignWesternEuropean"),
	
	/**
	 * The ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2004EquityAmericasInterdealer") ISDA_2004_EQUITY_AMERICAS_INTERDEALER("ISDA2004EquityAmericasInterdealer"),
	
	/**
	 * The Revised ISDA 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2004EquityAmericasInterdealerRev1") ISDA_2004_EQUITY_AMERICAS_INTERDEALER_REV_1("ISDA2004EquityAmericasInterdealerRev1"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardAsiaSovereign.
	 */
	@RosettaEnumValue(value = "ISDA2004StandardCreditSovereignAsia") ISDA_2004_STANDARD_CREDIT_SOVEREIGN_ASIA("ISDA2004StandardCreditSovereignAsia"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardEmergingEuropeanAndMiddleEasternSovereign.
	 */
	@RosettaEnumValue(value = "ISDA2004StandardCreditSovereignEmergingEuropeanAndMiddleEastern") ISDA_2004_STANDARD_CREDIT_SOVEREIGN_EMERGING_EUROPEAN_AND_MIDDLE_EASTERN("ISDA2004StandardCreditSovereignEmergingEuropeanAndMiddleEastern"),
	
	/**
	 * Dummy MCA value mirroring the matrix term values StandardJapanSovereign.
	 */
	@RosettaEnumValue(value = "ISDA2004StandardCreditSovereignJapan") ISDA_2004_STANDARD_CREDIT_SOVEREIGN_JAPAN("ISDA2004StandardCreditSovereignJapan"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardLatinAmericaSovereign.
	 */
	@RosettaEnumValue(value = "ISDA2004StandardCreditSovereignLatinAmerican") ISDA_2004_STANDARD_CREDIT_SOVEREIGN_LATIN_AMERICAN("ISDA2004StandardCreditSovereignLatinAmerican"),
	
	/**
	 * Dummy MCA value mirroring the matrix term value StandardWesternEuropeanSovereign.
	 */
	@RosettaEnumValue(value = "ISDA2004StandardCreditSovereignWesternEuropean") ISDA_2004_STANDARD_CREDIT_SOVEREIGN_WESTERN_EUROPEAN("ISDA2004StandardCreditSovereignWesternEuropean"),
	
	/**
	 * ISDA 2005 AEJ (Asia Excluding Japan) Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2005EquityAsiaExcludingJapanInterdealer") ISDA_2005_EQUITY_ASIA_EXCLUDING_JAPAN_INTERDEALER("ISDA2005EquityAsiaExcludingJapanInterdealer"),
	
	/**
	 * Second Revised ISDA 2005 AEJ (Asia Excluding Japan) Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2005EquityAsiaExcludingJapanInterdealerRev2") ISDA_2005_EQUITY_ASIA_EXCLUDING_JAPAN_INTERDEALER_REV_2("ISDA2005EquityAsiaExcludingJapanInterdealerRev2"),
	
	/**
	 * The ISDA 2005 Japanese Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2005EquityJapaneseInterdealer") ISDA_2005_EQUITY_JAPANESE_INTERDEALER("ISDA2005EquityJapaneseInterdealer"),
	
	/**
	 * ISDA 2006 Variance Swap Japanese Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2006VarianceSwapJapanese") ISDA_2006_VARIANCE_SWAP_JAPANESE("ISDA2006VarianceSwapJapanese"),
	
	/**
	 * ISDA 2006 Variance Swap Japanese Interdealer Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2006VarianceSwapJapaneseInterdealer") ISDA_2006_VARIANCE_SWAP_JAPANESE_INTERDEALER("ISDA2006VarianceSwapJapaneseInterdealer"),
	
	/**
	 * The ISDA 2007 European Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007EquityEuropean") ISDA_2007_EQUITY_EUROPEAN("ISDA2007EquityEuropean"),
	
	/**
	 * The ISDA 2007 Americas Master Variance Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapAmericas") ISDA_2007_VARIANCE_SWAP_AMERICAS("ISDA2007VarianceSwapAmericas"),
	
	/**
	 * The ISDA 2007 AEJ Master Variance Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapAsiaExcludingJapan") ISDA_2007_VARIANCE_SWAP_ASIA_EXCLUDING_JAPAN("ISDA2007VarianceSwapAsiaExcludingJapan"),
	
	/**
	 * The Revised ISDA 2007 AEJ Master Variance Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapAsiaExcludingJapanRev1") ISDA_2007_VARIANCE_SWAP_ASIA_EXCLUDING_JAPAN_REV_1("ISDA2007VarianceSwapAsiaExcludingJapanRev1"),
	
	/**
	 * The Second Revised ISDA 2007 AEJ Master Variance Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapAsiaExcludingJapanRev2") ISDA_2007_VARIANCE_SWAP_ASIA_EXCLUDING_JAPAN_REV_2("ISDA2007VarianceSwapAsiaExcludingJapanRev2"),
	
	/**
	 * The ISDA 2007 European Variance Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapEuropean") ISDA_2007_VARIANCE_SWAP_EUROPEAN("ISDA2007VarianceSwapEuropean"),
	
	/**
	 * The Revised ISDA 2007 European Variance Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2007VarianceSwapEuropeanRev1") ISDA_2007_VARIANCE_SWAP_EUROPEAN_REV_1("ISDA2007VarianceSwapEuropeanRev1"),
	
	/**
	 * The ISDA 2008 Japanese Dividend Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008DividendSwapJapan") ISDA_2008_DIVIDEND_SWAP_JAPAN("ISDA2008DividendSwapJapan"),
	
	/**
	 * The Revised ISDA 2008 Japanese Dividend Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008DividendSwapJapaneseRev1") ISDA_2008_DIVIDEND_SWAP_JAPANESE_REV_1("ISDA2008DividendSwapJapaneseRev1"),
	
	/**
	 * The ISDA 2008 Americas Master Designated/Exchange-Traded Contract Option Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityAmericas") ISDA_2008_EQUITY_AMERICAS("ISDA2008EquityAmericas"),
	
	/**
	 * The ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityAsiaExcludingJapan") ISDA_2008_EQUITY_ASIA_EXCLUDING_JAPAN("ISDA2008EquityAsiaExcludingJapan"),
	
	/**
	 * The Revised ISDA 2008 AEJ (Asia Excluding Japan) Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityAsiaExcludingJapanRev1") ISDA_2008_EQUITY_ASIA_EXCLUDING_JAPAN_REV_1("ISDA2008EquityAsiaExcludingJapanRev1"),
	
	/**
	 * The ISDA 2008 Japanese Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2008EquityJapan") ISDA_2008_EQUITY_JAPAN("ISDA2008EquityJapan"),
	
	/**
	 * The ISDA 2009 Americas Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009EquityAmericas") ISDA_2009_EQUITY_AMERICAS("ISDA2009EquityAmericas"),
	
	/**
	 * The ISDA 2009 European Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009EquityEuropeanInterdealer") ISDA_2009_EQUITY_EUROPEAN_INTERDEALER("ISDA2009EquityEuropeanInterdealer"),
	
	/**
	 * 2009 Pan-Asia Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2009EquityPanAsia") ISDA_2009_EQUITY_PAN_ASIA("ISDA2009EquityPanAsia"),
	
	/**
	 * The ISDA 2010 EMEA EM Interdealer Master Equity Derivatives Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2010EquityEMEAInterdealer") ISDA_2010_EQUITY_EMEA_INTERDEALER("ISDA2010EquityEMEAInterdealer"),
	
	/**
	 * The ISDA 2013 Americas Master Volatility Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2013VolatilitySwapAmericas") ISDA_2013_VOLATILITY_SWAP_AMERICAS("ISDA2013VolatilitySwapAmericas"),
	
	/**
	 * The ISDA 2013 AEJ Master Volatility Swap Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2013VolatilitySwapAsiaExcludingJapan") ISDA_2013_VOLATILITY_SWAP_ASIA_EXCLUDING_JAPAN("ISDA2013VolatilitySwapAsiaExcludingJapan"),
	
	/**
	 * The ISDA 2013 European Volatility Swap Master Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2013VolatilitySwapEuropean") ISDA_2013_VOLATILITY_SWAP_EUROPEAN("ISDA2013VolatilitySwapEuropean"),
	
	/**
	 * The ISDA 2013 Volatility Swap Japanese Confirmation Agreement applies.
	 */
	@RosettaEnumValue(value = "ISDA2013VolatilitySwapJapanese") ISDA_2013_VOLATILITY_SWAP_JAPANESE("ISDA2013VolatilitySwapJapanese")
;
	private static Map<String, MasterConfirmationTypeEnum> values;
	static {
        Map<String, MasterConfirmationTypeEnum> map = new ConcurrentHashMap<>();
		for (MasterConfirmationTypeEnum instance : MasterConfirmationTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MasterConfirmationTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MasterConfirmationTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MasterConfirmationTypeEnum fromDisplayName(String name) {
		MasterConfirmationTypeEnum value = values.get(name);
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
