package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to define the supplements to a base set of ISDA Definitions that are applicable to the transaction.
 * @version ${project.version}
 */
@RosettaEnum("ContractualSupplementTypeEnum")
public enum ContractualSupplementTypeEnum {

	/**
	 * Standard Terms Supplement for ABX Transactions.
	 */
	@RosettaEnumValue(value = "ABX") ABX("ABX"),
	
	/**
	 * Standard Terms Supplement for Asset-Backed Tranche Transactions.
	 */
	@RosettaEnumValue(value = "ABXTranche") ABX_TRANCHE("ABXTranche"),
	
	/**
	 * ISDA Standard Terms Supplement for use with Credit Derivative Transactions on Leveraged Loans.
	 */
	@RosettaEnumValue(value = "CDSonLeveragedLoans") CD_SON_LEVERAGED_LOANS("CDSonLeveragedLoans"),
	
	/**
	 * ISDA Standard Terms Supplement for use with Credit Derivative Transactions on Mortgage-backed Security with Pay-As-You-Go or Physical Settlement.
	 */
	@RosettaEnumValue(value = "CDSonMBS") CD_SON_MBS("CDSonMBS"),
	
	/**
	 * Standard Terms Supplement for CDX Untranched Transactions.
	 */
	@RosettaEnumValue(value = "CDX") CDX("CDX"),
	
	/**
	 * Standard Terms Supplement for CDX Emerging Markets Untranched Transactions.
	 */
	@RosettaEnumValue(value = "CDXEmergingMarkets") CDX_EMERGING_MARKETS("CDXEmergingMarkets"),
	
	/**
	 * Standard Terms Supplement for CDX Emerging Markets Diversified Untranched Transactions..
	 */
	@RosettaEnumValue(value = "CDXEmergingMarketsDiversified") CDX_EMERGING_MARKETS_DIVERSIFIED("CDXEmergingMarketsDiversified"),
	
	/**
	 * Standard Terms Supplement for CDX Swaption Transactions.
	 */
	@RosettaEnumValue(value = "CDXSwaption") CDX_SWAPTION("CDXSwaption"),
	
	/**
	 * Standard Terms Supplement for Dow Jones CDX Tranche Transactions.
	 */
	@RosettaEnumValue(value = "CDXTranche") CDX_TRANCHE("CDXTranche"),
	
	/**
	 * Standard Terms Supplement for CMBX Transactions.
	 */
	@RosettaEnumValue(value = "CMBX") CMBX("CMBX"),
	
	/**
	 * Standard Terms Supplement for Single Name European CMBS Transactions.
	 */
	@RosettaEnumValue(value = "EuropeanCMBS") EUROPEAN_CMBS("EuropeanCMBS"),
	
	/**
	 * Standard Terms Supplement for Single Name European RMBS Transactions.
	 */
	@RosettaEnumValue(value = "EuropeanRMBS") EUROPEAN_RMBS("EuropeanRMBS"),
	
	/**
	 * Standard Terms Supplement for IOS Transactions.
	 */
	@RosettaEnumValue(value = "IOS") IOS("IOS"),
	
	/**
	 * Supplement to the 1999 ISDA Credit Derivatives Definitions Relating to Convertible, Exchangeable or Accreting Obligations dated November 9, 2001.
	 */
	@RosettaEnumValue(value = "ISDA1999CreditConvertibleExchangeableAccretingObligations") ISDA_1999_CREDIT_CONVERTIBLE_EXCHANGEABLE_ACCRETING_OBLIGATIONS("ISDA1999CreditConvertibleExchangeableAccretingObligations"),
	
	/**
	 * Restructuring Supplement to the 1999 ISDA Credit Derivatives Definitions dated May 11, 2001.
	 */
	@RosettaEnumValue(value = "ISDA1999CreditRestructuring") ISDA_1999_CREDIT_RESTRUCTURING("ISDA1999CreditRestructuring"),
	
	/**
	 * Supplement Relating to Successor and Credit Events to the 1999 ISDA Credit Derivatives Definitions dated November 28, 2001.
	 */
	@RosettaEnumValue(value = "ISDA1999CreditSuccessorAndCreditEvents") ISDA_1999_CREDIT_SUCCESSOR_AND_CREDIT_EVENTS("ISDA1999CreditSuccessorAndCreditEvents"),
	
	/**
	 * Additional Provisions for LPN dated December 6, 2007.
	 */
	@RosettaEnumValue(value = "ISDA2003AdditionalProvisionsLPN") ISDA_2003_ADDITIONAL_PROVISIONS_LPN("ISDA2003AdditionalProvisionsLPN"),
	
	/**
	 * Additional Provisions for Contingent Credit Spread Transactions dated August 15, 2008.
	 */
	@RosettaEnumValue(value = "ISDA2003ContingentCreditSpreadTransaction") ISDA_2003_CONTINGENT_CREDIT_SPREAD_TRANSACTION("ISDA2003ContingentCreditSpreadTransaction"),
	
	/**
	 * 2005 Matrix Supplement to the 2003 ISDA Credit Derivatives.
	 */
	@RosettaEnumValue(value = "ISDA2003Credit2005MatrixSupplement") ISDA_2003_CREDIT_2005_MATRIX_SUPPLEMENT("ISDA2003Credit2005MatrixSupplement"),
	
	/**
	 * Additional Provisions for the Argentine Republic: Excluded Obligations and Excluded Deliverable Obligations dated December 21, 2005.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditArgentineRepublic") ISDA_2003_CREDIT_ARGENTINE_REPUBLIC("ISDA2003CreditArgentineRepublic"),
	
	/**
	 * ISDA Credit Derivatives Determinations Committees and Auction Settlement Supplement to the 2003 ISDA Credit Derivatives Definitions (published on [TBD]).
	 */
	@RosettaEnumValue(value = "ISDA2003CreditAuctionSupplement") ISDA_2003_CREDIT_AUCTION_SUPPLEMENT("ISDA2003CreditAuctionSupplement"),
	
	/**
	 * May 2003 Supplement to the 2003 ISDA Credit Derivatives Definitions.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditMay2003") ISDA_2003_CREDIT_MAY_2003("ISDA2003CreditMay2003"),
	
	/**
	 * Additional Provisions for Physically Settled Default Swaps Monoline Insurer as Reference Entity dated May 9, 2003.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditMonolineInsurers") ISDA_2003_CREDIT_MONOLINE_INSURERS("ISDA2003CreditMonolineInsurers"),
	
	/**
	 * Additional Provisions for Physically Settled Default Swaps Monoline Insurer as Reference Entity dated January 21, 2005.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditMonolineInsurers2005") ISDA_2003_CREDIT_MONOLINE_INSURERS_2005("ISDA2003CreditMonolineInsurers2005"),
	
	/**
	 * Additional Provisions for the Republic of Hungary: Obligation Characteristics and Deliverable Obligation Characteristics dated August 13, 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditRepublicOfHungary") ISDA_2003_CREDIT_REPUBLIC_OF_HUNGARY("ISDA2003CreditRepublicOfHungary"),
	
	/**
	 * Additional Provisions for the Republic of Hungary: Obligation Characteristics and Deliverable Obligation Characteristics dated February 14, 2005. 
	 */
	@RosettaEnumValue(value = "ISDA2003CreditRepublicOfHungary2005") ISDA_2003_CREDIT_REPUBLIC_OF_HUNGARY_2005("ISDA2003CreditRepublicOfHungary2005"),
	
	/**
	 * Additional Provisions for the Russian Federation: Obligation Characteristics and Deliverable Obligation Characteristics dated August 13, 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditRussianFederation") ISDA_2003_CREDIT_RUSSIAN_FEDERATION("ISDA2003CreditRussianFederation"),
	
	/**
	 * Additional Provisions for Credit Derivative Transactions - U.S. Municipal Entity as Reference Entity dated September 17, 2004.
	 */
	@RosettaEnumValue(value = "ISDA2003CreditUSMunicipals") ISDA_2003_CREDIT_US_MUNICIPALS("ISDA2003CreditUSMunicipals"),
	
	/**
	 * Additional Provisions for STMicroelectronics NV dated December 6, 2007.
	 */
	@RosettaEnumValue(value = "ISDA2003STMicroelectronicsNV") ISDA_2003_ST_MICROELECTRONICS_NV("ISDA2003STMicroelectronicsNV"),
	
	/**
	 * 2007 Full Lookthrough Depository Receipt Supplement to the 2002 Equity Derivatives Definitions.
	 */
	@RosettaEnumValue(value = "ISDA2007FullLookthroughDepositoryReceiptSupplement") ISDA_2007_FULL_LOOKTHROUGH_DEPOSITORY_RECEIPT_SUPPLEMENT("ISDA2007FullLookthroughDepositoryReceiptSupplement"),
	
	/**
	 * 2007 Partial Lookthrough Depository Receipt Supplement to the 2002 ISDA Equity Derivatives Definitions.
	 */
	@RosettaEnumValue(value = "ISDA2007PartialLookthroughDepositoryReceiptSupplement") ISDA_2007_PARTIAL_LOOKTHROUGH_DEPOSITORY_RECEIPT_SUPPLEMENT("ISDA2007PartialLookthroughDepositoryReceiptSupplement"),
	
	/**
	 * Additional Provisions for Physically Settled Default Swaps Monoline Insurer.
	 */
	@RosettaEnumValue(value = "ISDACreditMonolineInsurers") ISDA_CREDIT_MONOLINE_INSURERS("ISDACreditMonolineInsurers"),
	
	/**
	 * Additional Provisions for Fixed Recovery Credit Default Swap Transactions
	 */
	@RosettaEnumValue(value = "ISDADeliveryRestrictions") ISDA_DELIVERY_RESTRICTIONS("ISDADeliveryRestrictions"),
	
	/**
	 * Additional Provisions for Fixed Recovery Credit Default Swap Transactions.
	 */
	@RosettaEnumValue(value = "ISDAFixedRecovery") ISDA_FIXED_RECOVERY("ISDAFixedRecovery"),
	
	/**
	 * Additional Provisions for LPN Reference Entities.
	 */
	@RosettaEnumValue(value = "ISDALPNReferenceEntities") ISDALPN_REFERENCE_ENTITIES("ISDALPNReferenceEntities"),
	
	/**
	 * Canadian Supplement to the 2004 Americas Interdealer Master Equity Derivatives Confirmation Agreement dated March 29, 2004.
	 */
	@RosettaEnumValue(value = "ISDAMarch2004EquityCanadianSupplement") ISDA_MARCH_2004_EQUITY_CANADIAN_SUPPLEMENT("ISDAMarch2004EquityCanadianSupplement"),
	
	/**
	 * Additional Provisions for Recovery Lock Credit Default Swap Transactions.
	 */
	@RosettaEnumValue(value = "ISDARecoveryLock") ISDA_RECOVERY_LOCK("ISDARecoveryLock"),
	
	/**
	 * Additional Provisions for Secured Deliverable Obligation Characteristic.
	 */
	@RosettaEnumValue(value = "ISDASecuredDeliverableObligationCharacteristic") ISDA_SECURED_DELIVERABLE_OBLIGATION_CHARACTERISTIC("ISDASecuredDeliverableObligationCharacteristic"),
	
	/**
	 * Standard Terms Supplement for Syndicated Secured Loan Credit Default Swap Index Transactions.
	 */
	@RosettaEnumValue(value = "LCDX") LCDX("LCDX"),
	
	/**
	 * Standard Terms Supplement for Syndicated Secured Loan Credit Default Swap Index Tranche Transactions.
	 */
	@RosettaEnumValue(value = "LCDXTranche") LCDX_TRANCHE("LCDXTranche"),
	
	/**
	 * Standard Terms Supplement for MBX Transactions.
	 */
	@RosettaEnumValue(value = "MBX") MBX("MBX"),
	
	/**
	 * Standard Terms Supplement for Municipal CDX Untranched Transactions.
	 */
	@RosettaEnumValue(value = "MCDX") MCDX("MCDX"),
	
	/**
	 * Standard Terms Supplement for PO Index Transactions.
	 */
	@RosettaEnumValue(value = "PO") PO("PO"),
	
	/**
	 * Standard Terms Supplement for PrimeX Transactions.
	 */
	@RosettaEnumValue(value = "PrimeX") PRIME_X("PrimeX"),
	
	/**
	 * Standard Terms Supplement for Standard CDX Tranche Transactions.
	 */
	@RosettaEnumValue(value = "StandardCDXTranche") STANDARD_CDX_TRANCHE("StandardCDXTranche"),
	
	/**
	 * Standard Syndicated Secured Loan Credit Default Swap Standard Terms Supplement.
	 */
	@RosettaEnumValue(value = "StandardLCDS") STANDARD_LCDS("StandardLCDS"),
	
	/**
	 * Standard Terms Supplement for Standard Syndicated Secured Loan Credit Default Swap Bullet Transactions.
	 */
	@RosettaEnumValue(value = "StandardLCDSBullet") STANDARD_LCDS_BULLET("StandardLCDSBullet"),
	
	/**
	 * Standard Terms Supplement for Standard Syndicated Secured Loan Credit Default Swap Index Bullet Transactions.
	 */
	@RosettaEnumValue(value = "StandardLCDXBullet") STANDARD_LCDX_BULLET("StandardLCDXBullet"),
	
	/**
	 * Standard Terms Supplement for Standard Syndicated Secured Loan Credit Default Swap Index Bullet Tranche Transactions.
	 */
	@RosettaEnumValue(value = "StandardLCDXBulletTranche") STANDARD_LCDX_BULLET_TRANCHE("StandardLCDXBulletTranche"),
	
	/**
	 * Standard Terms Supplement for Standard iTraxx Europe Tranched Transactions.
	 */
	@RosettaEnumValue(value = "StandardiTraxxEuropeTranche") STANDARDI_TRAXX_EUROPE_TRANCHE("StandardiTraxxEuropeTranche"),
	
	/**
	 * Syndicated Secured Loan Credit Default Swap Standard Terms Supplement.
	 */
	@RosettaEnumValue(value = "SyndicatedSecuredLoanCDS") SYNDICATED_SECURED_LOAN_CDS("SyndicatedSecuredLoanCDS"),
	
	/**
	 * Standard Terms Supplement for TRX Transactions.
	 */
	@RosettaEnumValue(value = "TRX") TRX("TRX"),
	
	/**
	 * Standard Terms Supplement for TRX.II Transactions.
	 */
	@RosettaEnumValue(value = "TRX_II", displayName = "TRX.II") TRX_II("TRX_II", "TRX.II"),
	
	/**
	 * Standard Terms Supplement for iTraxx Asia Excluding Japan.
	 */
	@RosettaEnumValue(value = "iTraxxAsiaExJapan") I_TRAXX_ASIA_EX_JAPAN("iTraxxAsiaExJapan"),
	
	/**
	 * Standard Terms Supplement for iTraxx Asia Ex-Japan Swaption Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxAsiaExJapanSwaption") I_TRAXX_ASIA_EX_JAPAN_SWAPTION("iTraxxAsiaExJapanSwaption"),
	
	/**
	 * Standard Terms Supplement for iTraxx Asia Excluding Japan Tranched Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxAsiaExJapanTranche") I_TRAXX_ASIA_EX_JAPAN_TRANCHE("iTraxxAsiaExJapanTranche"),
	
	/**
	 * Standard Terms Supplement for iTraxx Australia.
	 */
	@RosettaEnumValue(value = "iTraxxAustralia") I_TRAXX_AUSTRALIA("iTraxxAustralia"),
	
	/**
	 * Standard Terms Supplement for iTraxx Australia Swaption Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxAustraliaSwaption") I_TRAXX_AUSTRALIA_SWAPTION("iTraxxAustraliaSwaption"),
	
	/**
	 * Standard Terms Supplement for iTraxx Australia Tranched Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxAustraliaTranche") I_TRAXX_AUSTRALIA_TRANCHE("iTraxxAustraliaTranche"),
	
	/**
	 * Standard Terms Supplement for iTraxx CJ.
	 */
	@RosettaEnumValue(value = "iTraxxCJ") I_TRAXX_CJ("iTraxxCJ"),
	
	/**
	 * Standard Terms Supplement for iTraxx CJ Tranched Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxCJTranche") I_TRAXX_CJ_TRANCHE("iTraxxCJTranche"),
	
	/**
	 * Standard Terms Supplement for iTraxx Europe Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxEurope") I_TRAXX_EUROPE("iTraxxEurope"),
	
	/**
	 * Standard Terms Supplement for iTraxx Europe Dealer Form.
	 */
	@RosettaEnumValue(value = "iTraxxEuropeDealer") I_TRAXX_EUROPE_DEALER("iTraxxEuropeDealer"),
	
	/**
	 * Standard Terms Supplement for iTraxx Europe Non-Dealer Form.
	 */
	@RosettaEnumValue(value = "iTraxxEuropeNonDealer") I_TRAXX_EUROPE_NON_DEALER("iTraxxEuropeNonDealer"),
	
	/**
	 * Standard Terms Supplement for iTraxx Europe Swaption Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxEuropeSwaption") I_TRAXX_EUROPE_SWAPTION("iTraxxEuropeSwaption"),
	
	/**
	 * Standard Terms Supplement for iTraxx Europe Tranched Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxEuropeTranche") I_TRAXX_EUROPE_TRANCHE("iTraxxEuropeTranche"),
	
	/**
	 * Standard Terms Supplement for iTraxx Japan.
	 */
	@RosettaEnumValue(value = "iTraxxJapan") I_TRAXX_JAPAN("iTraxxJapan"),
	
	/**
	 * Standard Terms Supplement for iTraxx Japan Swaption Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxJapanSwaption") I_TRAXX_JAPAN_SWAPTION("iTraxxJapanSwaption"),
	
	/**
	 * Standard Terms Supplement for iTraxx Japan Tranched Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxJapanTranche") I_TRAXX_JAPAN_TRANCHE("iTraxxJapanTranche"),
	
	/**
	 * Standard Terms Supplement for iTraxx LevX.
	 */
	@RosettaEnumValue(value = "iTraxxLevX") I_TRAXX_LEV_X("iTraxxLevX"),
	
	/**
	 * Standard Terms Supplement for iTraxx SDI 75 Dealer Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxSDI75Dealer") I_TRAXX_SDI_75_DEALER("iTraxxSDI75Dealer"),
	
	/**
	 * Standard Terms Supplement for iTraxx SDI 75 Non-Dealer Transactions.
	 */
	@RosettaEnumValue(value = "iTraxxSDI75NonDealer") I_TRAXX_SDI_75_NON_DEALER("iTraxxSDI75NonDealer"),
	
	/**
	 * Standard Terms Supplement for iTraxx SovX.
	 */
	@RosettaEnumValue(value = "iTraxxSovX") I_TRAXX_SOV_X("iTraxxSovX")
;
	private static Map<String, ContractualSupplementTypeEnum> values;
	static {
        Map<String, ContractualSupplementTypeEnum> map = new ConcurrentHashMap<>();
		for (ContractualSupplementTypeEnum instance : ContractualSupplementTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ContractualSupplementTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ContractualSupplementTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ContractualSupplementTypeEnum fromDisplayName(String name) {
		ContractualSupplementTypeEnum value = values.get(name);
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
