package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("MasterAgreementVariantIdentifierEnum")
public enum MasterAgreementVariantIdentifierEnum {

	/**
	 * Agreement is Undated
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_001_01") ISLA_GMSLA_001_01("ISLA_GMSLA_001_01"),
	
	/**
	 * Agreement is Dated
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_001_02") ISLA_GMSLA_001_02("ISLA_GMSLA_001_02"),
	
	/**
	 * Name and Place of Incorporation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_002_01") ISLA_GMSLA_002_01("ISLA_GMSLA_002_01"),
	
	/**
	 * Names and Place of Incorporation plus Additional Information
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_002_02") ISLA_GMSLA_002_02("ISLA_GMSLA_002_02"),
	
	/**
	 * Defining the Party&#39;s Role as Lender or Borrower
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_002_03") ISLA_GMSLA_002_03("ISLA_GMSLA_002_03"),
	
	/**
	 * Non-specific Roles
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_003_01") ISLA_GMSLA_003_01("ISLA_GMSLA_003_01"),
	
	/**
	 * Specific Roles
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_003_02") ISLA_GMSLA_003_02("ISLA_GMSLA_003_02"),
	
	/**
	 * GMSLA Schedule
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_004_01") ISLA_GMSLA_004_01("ISLA_GMSLA_004_01"),
	
	/**
	 * Outside of GMSLA
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_004_02") ISLA_GMSLA_004_02("ISLA_GMSLA_004_02"),
	
	/**
	 * Additional Criteria
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_004_03") ISLA_GMSLA_004_03("ISLA_GMSLA_004_03"),
	
	/**
	 * GMSLA Schedule
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_005_01") ISLA_GMSLA_005_01("ISLA_GMSLA_005_01"),
	
	/**
	 * Outside of GMSLA
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_005_02") ISLA_GMSLA_005_02("ISLA_GMSLA_005_02"),
	
	/**
	 * Aggregation Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006_01") ISLA_GMSLA_006_01("ISLA_GMSLA_006_01"),
	
	/**
	 * Aggregation Does Not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006_02") ISLA_GMSLA_006_02("ISLA_GMSLA_006_02"),
	
	/**
	 * Aggregation Applies Separately to Loan Groups
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006_03") ISLA_GMSLA_006_03("ISLA_GMSLA_006_03"),
	
	/**
	 * Aggregation Applies to Some but Not All Loans
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006_04") ISLA_GMSLA_006_04("ISLA_GMSLA_006_04"),
	
	/**
	 * Neither Aggregation nor Loan by Loan Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006_05") ISLA_GMSLA_006_05("ISLA_GMSLA_006_05"),
	
	/**
	 * Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_007_01") ISLA_GMSLA_007_01("ISLA_GMSLA_007_01"),
	
	/**
	 * Collateral Disapplied
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_007_02") ISLA_GMSLA_007_02("ISLA_GMSLA_007_02"),
	
	/**
	 * Netting of Collateral Shall Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_008_01") ISLA_GMSLA_008_01("ISLA_GMSLA_008_01"),
	
	/**
	 * Netting of Collateral Shall Not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_008_02") ISLA_GMSLA_008_02("ISLA_GMSLA_008_02"),
	
	/**
	 * Netting of Collateral Shall Apply with Multiple Payments or Delivery Options
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_008_03") ISLA_GMSLA_008_03("ISLA_GMSLA_008_03"),
	
	/**
	 * Netting of Collateral Shall Apply Separately per Group of Loans
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_008_04") ISLA_GMSLA_008_04("ISLA_GMSLA_008_04"),
	
	/**
	 * Specified Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_009_01") ISLA_GMSLA_009_01("ISLA_GMSLA_009_01"),
	
	/**
	 * Notification Time by Collateral Type
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_009_02") ISLA_GMSLA_009_02("ISLA_GMSLA_009_02"),
	
	/**
	 * Notification Time as Agreed
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_009_03") ISLA_GMSLA_009_03("ISLA_GMSLA_009_03"),
	
	/**
	 * No Notification Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_009_04") ISLA_GMSLA_009_04("ISLA_GMSLA_009_04"),
	
	/**
	 * Indemnity Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_010_01") ISLA_GMSLA_010_01("ISLA_GMSLA_010_01"),
	
	/**
	 * Indemnity does not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_010_02") ISLA_GMSLA_010_02("ISLA_GMSLA_010_02"),
	
	/**
	 * Single Base Currency
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_011_01") ISLA_GMSLA_011_01("ISLA_GMSLA_011_01"),
	
	/**
	 * Single Base Currency with Fallback
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_011_02") ISLA_GMSLA_011_02("ISLA_GMSLA_011_02"),
	
	/**
	 * Single Base Currency with Multiple Fallback Options
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_011_03") ISLA_GMSLA_011_03("ISLA_GMSLA_011_03"),
	
	/**
	 * Locations are Specified Without Reference to Party
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_012_01") ISLA_GMSLA_012_01("ISLA_GMSLA_012_01"),
	
	/**
	 * Locations are Specified Separately per Party
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_012_02") ISLA_GMSLA_012_02("ISLA_GMSLA_012_02"),
	
	/**
	 * Not all Places of Business Have to be Open
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_012_03") ISLA_GMSLA_012_03("ISLA_GMSLA_012_03"),
	
	/**
	 * Standard Bid Price
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_01") ISLA_GMSLA_013_01("ISLA_GMSLA_013_01"),
	
	/**
	 * Standard Mid Price
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_02") ISLA_GMSLA_013_02("ISLA_GMSLA_013_02"),
	
	/**
	 * 2018 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_03") ISLA_GMSLA_013_03("ISLA_GMSLA_013_03"),
	
	/**
	 * Borrowers Agreement to Pricing Source
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_04") ISLA_GMSLA_013_04("ISLA_GMSLA_013_04"),
	
	/**
	 * Pre-agreed Pricing Source
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_05") ISLA_GMSLA_013_05("ISLA_GMSLA_013_05"),
	
	/**
	 * Time Variation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013_06") ISLA_GMSLA_013_06("ISLA_GMSLA_013_06"),
	
	/**
	 * Automatic Early Termination does not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014_01") ISLA_GMSLA_014_01("ISLA_GMSLA_014_01"),
	
	/**
	 * Automatic Early Termination Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014_02") ISLA_GMSLA_014_02("ISLA_GMSLA_014_02"),
	
	/**
	 * Automatic Early Termination Applies in Modified Form)
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014_03") ISLA_GMSLA_014_03("ISLA_GMSLA_014_03"),
	
	/**
	 * Automatic Early Termination is specified separately for each Principal
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014_04") ISLA_GMSLA_014_04("ISLA_GMSLA_014_04"),
	
	/**
	 * Automatic Early Termination is not applicable unless required due to the systems of law
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014_05") ISLA_GMSLA_014_05("ISLA_GMSLA_014_05"),
	
	/**
	 * Party Specifies a Single Designated Office
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_015_01") ISLA_GMSLA_015_01("ISLA_GMSLA_015_01"),
	
	/**
	 * Party Specifies Multiple Designated Offices
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_015_02") ISLA_GMSLA_015_02("ISLA_GMSLA_015_02"),
	
	/**
	 * 2000 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_01") ISLA_GMSLA_016_01("ISLA_GMSLA_016_01"),
	
	/**
	 * 2010 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_02") ISLA_GMSLA_016_02("ISLA_GMSLA_016_02"),
	
	/**
	 * 2018 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_03") ISLA_GMSLA_016_03("ISLA_GMSLA_016_03"),
	
	/**
	 * Plus Email
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_04") ISLA_GMSLA_016_04("ISLA_GMSLA_016_04"),
	
	/**
	 * Separate Address for Legal and Operational Notices
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_05") ISLA_GMSLA_016_05("ISLA_GMSLA_016_05"),
	
	/**
	 * Special Instructions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016_06") ISLA_GMSLA_016_06("ISLA_GMSLA_016_06"),
	
	/**
	 * No Process Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_017_01") ISLA_GMSLA_017_01("ISLA_GMSLA_017_01"),
	
	/**
	 * Process Agent Specified
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_017_02") ISLA_GMSLA_017_02("ISLA_GMSLA_017_02"),
	
	/**
	 * Process Agent to be Appointed
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_017_03") ISLA_GMSLA_017_03("ISLA_GMSLA_017_03"),
	
	/**
	 * A Party will not act as Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_018_01") ISLA_GMSLA_018_01("ISLA_GMSLA_018_01"),
	
	/**
	 * A Party may act as Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_018_02") ISLA_GMSLA_018_02("ISLA_GMSLA_018_02"),
	
	/**
	 * A Party will always act as Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_018_03") ISLA_GMSLA_018_03("ISLA_GMSLA_018_03"),
	
	/**
	 * Pooled Principal Transactions Shall Not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_019_01") ISLA_GMSLA_019_01("ISLA_GMSLA_019_01"),
	
	/**
	 * Pooled Principal Transactions Shall  Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_019_02") ISLA_GMSLA_019_02("ISLA_GMSLA_019_02"),
	
	/**
	 * Pooled Principal Transactions May Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_019_03") ISLA_GMSLA_019_03("ISLA_GMSLA_019_03"),
	
	/**
	 * Simple Election
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_020_01") ISLA_GMSLA_020_01("ISLA_GMSLA_020_01"),
	
	/**
	 * Election with Modifications
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_020_02") ISLA_GMSLA_020_02("ISLA_GMSLA_020_02"),
	
	/**
	 * Term Rate
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021_01") ISLA_GMSLA_021_01("ISLA_GMSLA_021_01"),
	
	/**
	 * Overnight Rate
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021_02") ISLA_GMSLA_021_02("ISLA_GMSLA_021_02"),
	
	/**
	 * Risk Free Rate
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021_03") ISLA_GMSLA_021_03("ISLA_GMSLA_021_03"),
	
	/**
	 * Non-Defaulting Party Election
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021_04") ISLA_GMSLA_021_04("ISLA_GMSLA_021_04"),
	
	/**
	 * Spread
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021_05") ISLA_GMSLA_021_05("ISLA_GMSLA_021_05"),
	
	/**
	 * Agreement Covers Existing Loans
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_022_01") ISLA_GMSLA_022_01("ISLA_GMSLA_022_01"),
	
	/**
	 * Agreement Does Not Cover Existing Loans
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_022_02") ISLA_GMSLA_022_02("ISLA_GMSLA_022_02"),
	
	/**
	 * Automation Does Not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_023_01") ISLA_GMSLA_023_01("ISLA_GMSLA_023_01"),
	
	/**
	 * Automation May Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_023_02") ISLA_GMSLA_023_02("ISLA_GMSLA_023_02"),
	
	/**
	 * Standard Pre-Print
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_024_01") ISLA_GMSLA_024_01("ISLA_GMSLA_024_01"),
	
	/**
	 * Grace Period Amendment
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_024_02") ISLA_GMSLA_024_02("ISLA_GMSLA_024_02"),
	
	/**
	 * Jurisdictional Amendments
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_024_03") ISLA_GMSLA_024_03("ISLA_GMSLA_024_03"),
	
	/**
	 * Transferor Pays Costs and Expenses
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_025_01") ISLA_GMSLA_025_01("ISLA_GMSLA_025_01"),
	
	/**
	 * Transferor Pays Costs and Expenses other than those arising from Negligence
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_025_02") ISLA_GMSLA_025_02("ISLA_GMSLA_025_02"),
	
	/**
	 * Transferor only Liable for Cost and Expenses if Reasonable Notice of Buy-in
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_025_03") ISLA_GMSLA_025_03("ISLA_GMSLA_025_03"),
	
	/**
	 * Buy-in Expanded to Cover Buy-in Exercised by an Exchange
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_025_04") ISLA_GMSLA_025_04("ISLA_GMSLA_025_04"),
	
	/**
	 * Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_026_01") ISLA_GMSLA_026_01("ISLA_GMSLA_026_01"),
	
	/**
	 * Selecting Party other than Lender
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_026_02") ISLA_GMSLA_026_02("ISLA_GMSLA_026_02"),
	
	/**
	 * Variation of Exchange Rate Source
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_026_03") ISLA_GMSLA_026_03("ISLA_GMSLA_026_03"),
	
	/**
	 * Standard Scope
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_027_01") ISLA_GMSLA_027_01("ISLA_GMSLA_027_01"),
	
	/**
	 * Limited Scope
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_027_02") ISLA_GMSLA_027_02("ISLA_GMSLA_027_02"),
	
	/**
	 * Same Day
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028_01") ISLA_GMSLA_028_01("ISLA_GMSLA_028_01"),
	
	/**
	 * Alternative Delivery Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028_02") ISLA_GMSLA_028_02("ISLA_GMSLA_028_02"),
	
	/**
	 * Same Day with Notification Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028_03") ISLA_GMSLA_028_03("ISLA_GMSLA_028_03"),
	
	/**
	 * Alternative Delivery Time with Notification Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028_04") ISLA_GMSLA_028_04("ISLA_GMSLA_028_04"),
	
	/**
	 * Asset Dependent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028_05") ISLA_GMSLA_028_05("ISLA_GMSLA_028_05"),
	
	/**
	 * Simultaneous delivery of securities and collateral
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_029_01") ISLA_GMSLA_029_01("ISLA_GMSLA_029_01"),
	
	/**
	 * Collateral Delivery as specified in the Security Agreement
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_029_02") ISLA_GMSLA_029_02("ISLA_GMSLA_029_02"),
	
	/**
	 * Lender to Deliver Securities once Collateral is Delivered
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_029_03") ISLA_GMSLA_029_03("ISLA_GMSLA_029_03"),
	
	/**
	 * Borrower Request
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_030_01") ISLA_GMSLA_030_01("ISLA_GMSLA_030_01"),
	
	/**
	 * Borrower Request/Lender Consent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_030_02") ISLA_GMSLA_030_02("ISLA_GMSLA_030_02"),
	
	/**
	 * Lender or Borrower Request
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_030_03") ISLA_GMSLA_030_03("ISLA_GMSLA_030_03"),
	
	/**
	 * Pre-approval of Alternative Collateral
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_030_04") ISLA_GMSLA_030_04("ISLA_GMSLA_030_04"),
	
	/**
	 * Manufactured Payment of Amount Such Party Would Be Entitled to Receive
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031_01") ISLA_GMSLA_031_01("ISLA_GMSLA_031_01"),
	
	/**
	 * Manufactured Payment of Amount Such Lender Would Be Entitled to Receive
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031_02") ISLA_GMSLA_031_02("ISLA_GMSLA_031_02"),
	
	/**
	 * Manufactured Payment Only in Relation to Loaned Securities
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031_03") ISLA_GMSLA_031_03("ISLA_GMSLA_031_03"),
	
	/**
	 * Additional Sum to Be Paid to Cover Tax Relief
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031_04") ISLA_GMSLA_031_04("ISLA_GMSLA_031_04"),
	
	/**
	 * Notice Requirement
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031_05") ISLA_GMSLA_031_05("ISLA_GMSLA_031_05"),
	
	/**
	 * Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_032_01") ISLA_GMSLA_032_01("ISLA_GMSLA_032_01"),
	
	/**
	 * Reasonable Notice Defined
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_032_02") ISLA_GMSLA_032_02("ISLA_GMSLA_032_02"),
	
	/**
	 * No Right to Instruct
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_032_03") ISLA_GMSLA_032_03("ISLA_GMSLA_032_03"),
	
	/**
	 * Payment Within a Week
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_033_01") ISLA_GMSLA_033_01("ISLA_GMSLA_033_01"),
	
	/**
	 * Payment Within 10 Days
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_033_02") ISLA_GMSLA_033_02("ISLA_GMSLA_033_02"),
	
	/**
	 * Payment Upon Maturity
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_033_03") ISLA_GMSLA_033_03("ISLA_GMSLA_033_03"),
	
	/**
	 * Such Rate as Agreed
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_034_01") ISLA_GMSLA_034_01("ISLA_GMSLA_034_01"),
	
	/**
	 * VAT Added
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_034_02") ISLA_GMSLA_034_02("ISLA_GMSLA_034_02"),
	
	/**
	 * No Deduction
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_034_03") ISLA_GMSLA_034_03("ISLA_GMSLA_034_03"),
	
	/**
	 * No Rate Payable
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_034_04") ISLA_GMSLA_034_04("ISLA_GMSLA_034_04"),
	
	/**
	 * Lender May Terminate a Loan at any Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_035_01") ISLA_GMSLA_035_01("ISLA_GMSLA_035_01"),
	
	/**
	 * Lender May Not Terminate a Loan
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_035_02") ISLA_GMSLA_035_02("ISLA_GMSLA_035_02"),
	
	/**
	 * Borrower May Terminate a Loan at Any Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_036_01") ISLA_GMSLA_036_01("ISLA_GMSLA_036_01"),
	
	/**
	 * Borrower May Terminate a Loan Subject to Notice
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_036_02") ISLA_GMSLA_036_02("ISLA_GMSLA_036_02"),
	
	/**
	 * Borrower May Terminate a Loan Subject to Limitations Concerning Corporate Actions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_036_03") ISLA_GMSLA_036_03("ISLA_GMSLA_036_03"),
	
	/**
	 * Borrower May Terminate a Loan Subject to Paying the Rate for the Full Term
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_036_04") ISLA_GMSLA_036_04("ISLA_GMSLA_036_04"),
	
	/**
	 * Failure to Deliver Event of Default Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_037_01") ISLA_GMSLA_037_01("ISLA_GMSLA_037_01"),
	
	/**
	 * Failure to Deliver Event of Default does not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_037_02") ISLA_GMSLA_037_02("ISLA_GMSLA_037_02"),
	
	/**
	 * Failure to Deliver Event of Default does not Apply to Lender
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_037_03") ISLA_GMSLA_037_03("ISLA_GMSLA_037_03"),
	
	/**
	 * 2000 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_038_01") ISLA_GMSLA_038_01("ISLA_GMSLA_038_01"),
	
	/**
	 * 2010 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_038_02") ISLA_GMSLA_038_02("ISLA_GMSLA_038_02"),
	
	/**
	 * 2018 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_038_03") ISLA_GMSLA_038_03("ISLA_GMSLA_038_03"),
	
	/**
	 * 2000 Modified No Lender Close Out
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_038_04") ISLA_GMSLA_038_04("ISLA_GMSLA_038_04"),
	
	/**
	 * 2000 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_039_01") ISLA_GMSLA_039_01("ISLA_GMSLA_039_01"),
	
	/**
	 * 2010/2018 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_039_02") ISLA_GMSLA_039_02("ISLA_GMSLA_039_02"),
	
	/**
	 * Hybrid
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_039_03") ISLA_GMSLA_039_03("ISLA_GMSLA_039_03"),
	
	/**
	 * 2000 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_040_01") ISLA_GMSLA_040_01("ISLA_GMSLA_040_01"),
	
	/**
	 * 2010/2018 Standard
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_040_02") ISLA_GMSLA_040_02("ISLA_GMSLA_040_02"),
	
	/**
	 * Hybrid
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_040_03") ISLA_GMSLA_040_03("ISLA_GMSLA_040_03"),
	
	/**
	 * Standard Costs and Expenses
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_041_01") ISLA_GMSLA_041_01("ISLA_GMSLA_041_01"),
	
	/**
	 * Limitation of Costs and Expenses
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_041_02") ISLA_GMSLA_041_02("ISLA_GMSLA_041_02"),
	
	/**
	 * Expansion of Costs and Expenses
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_041_03") ISLA_GMSLA_041_03("ISLA_GMSLA_041_03"),
	
	/**
	 * No Contractual Set-Off
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_042_01") ISLA_GMSLA_042_01("ISLA_GMSLA_042_01"),
	
	/**
	 * Simple Contractual Set-Off
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_042_02") ISLA_GMSLA_042_02("ISLA_GMSLA_042_02"),
	
	/**
	 * Set-Off with Unascertained Obligations Amendment
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_042_03") ISLA_GMSLA_042_03("ISLA_GMSLA_042_03"),
	
	/**
	 * Standard Paragraph 11.2(a)
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_043_01") ISLA_GMSLA_043_01("ISLA_GMSLA_043_01"),
	
	/**
	 * Amended Paragraph 11.2,(a) applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_043_02") ISLA_GMSLA_043_02("ISLA_GMSLA_043_02"),
	
	/**
	 * Consent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_044_01") ISLA_GMSLA_044_01("ISLA_GMSLA_044_01"),
	
	/**
	 * Consent with Standard Exclusions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_044_02") ISLA_GMSLA_044_02("ISLA_GMSLA_044_02"),
	
	/**
	 * Consent with Additional Exclusions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_044_03") ISLA_GMSLA_044_03("ISLA_GMSLA_044_03"),
	
	/**
	 * Pre-approved Assignments
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_044_04") ISLA_GMSLA_044_04("ISLA_GMSLA_044_04"),
	
	/**
	 * Parties May Record All Conversations
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_045_01") ISLA_GMSLA_045_01("ISLA_GMSLA_045_01"),
	
	/**
	 * Parties Agree to Obtain Consent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_045_02") ISLA_GMSLA_045_02("ISLA_GMSLA_045_02"),
	
	/**
	 * Parties Limit the Conversations that May be Recorded
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_045_03") ISLA_GMSLA_045_03("ISLA_GMSLA_045_03"),
	
	/**
	 * Submission as Evidence
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_045_04") ISLA_GMSLA_045_04("ISLA_GMSLA_045_04"),
	
	/**
	 * Standard Waiver of Immunity Applies
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_046_01") ISLA_GMSLA_046_01("ISLA_GMSLA_046_01"),
	
	/**
	 * Waiver of Immunity may Not Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_046_02") ISLA_GMSLA_046_02("ISLA_GMSLA_046_02"),
	
	/**
	 * No Additional Documentation Required
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_047_01") ISLA_GMSLA_047_01("ISLA_GMSLA_047_01"),
	
	/**
	 * Additional Documentation Required
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_047_02") ISLA_GMSLA_047_02("ISLA_GMSLA_047_02"),
	
	/**
	 * Collateral Transfer Details not included
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_048_01") ISLA_GMSLA_048_01("ISLA_GMSLA_048_01"),
	
	/**
	 * Collateral Transfer Details included
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_048_02") ISLA_GMSLA_048_02("ISLA_GMSLA_048_02"),
	
	/**
	 * Confidentiality Clause
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_049_01") ISLA_GMSLA_049_01("ISLA_GMSLA_049_01"),
	
	/**
	 * Permitted Disclosure Clause
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_049_02") ISLA_GMSLA_049_02("ISLA_GMSLA_049_02"),
	
	/**
	 * Paragraph 20.1 Amended to Refer  Paragraph 6
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_050_01") ISLA_GMSLA_050_01("ISLA_GMSLA_050_01"),
	
	/**
	 * Paragraph 27.2 Amended to refer to the 2010 GMSLA
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_050_02") ISLA_GMSLA_050_02("ISLA_GMSLA_050_02"),
	
	/**
	 * MCTA  Delivery only
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_051_01") ISLA_GMSLA_051_01("ISLA_GMSLA_051_01"),
	
	/**
	 * MCTA  Delivery and Re-Delivery
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_051_02") ISLA_GMSLA_051_02("ISLA_GMSLA_051_02"),
	
	/**
	 * MCTA  Drops to Zero for a Defaulting Party
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_051_03") ISLA_GMSLA_051_03("ISLA_GMSLA_051_03"),
	
	/**
	 * No Non-Reliance Representation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_052_01") ISLA_GMSLA_052_01("ISLA_GMSLA_052_01"),
	
	/**
	 * Non-Reliance Representation Added
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_052_02") ISLA_GMSLA_052_02("ISLA_GMSLA_052_02"),
	
	/**
	 * No Records and Statements Clause
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_053_01") ISLA_GMSLA_053_01("ISLA_GMSLA_053_01"),
	
	/**
	 * Records and Statements Clause Added
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_053_02") ISLA_GMSLA_053_02("ISLA_GMSLA_053_02"),
	
	/**
	 * Recovery and Resolution not Included
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_054_01") ISLA_GMSLA_054_01("ISLA_GMSLA_054_01"),
	
	/**
	 * Recovery and Resolution Included in GMSLA
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_054_02") ISLA_GMSLA_054_02("ISLA_GMSLA_054_02"),
	
	/**
	 * Recovery and Resolution Included by Protocol
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_054_03") ISLA_GMSLA_054_03("ISLA_GMSLA_054_03"),
	
	/**
	 * Recovery and Resolution Incorporated by Reference
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_054_04") ISLA_GMSLA_054_04("ISLA_GMSLA_054_04"),
	
	/**
	 * Security Agreement Details Included
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_055_01") ISLA_GMSLA_055_01("ISLA_GMSLA_055_01"),
	
	/**
	 * Triparty Services Not Referenced
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_056_01") ISLA_GMSLA_056_01("ISLA_GMSLA_056_01"),
	
	/**
	 * Triparty Services May Apply
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_056_02") ISLA_GMSLA_056_02("ISLA_GMSLA_056_02")
;
	private static Map<String, MasterAgreementVariantIdentifierEnum> values;
	static {
        Map<String, MasterAgreementVariantIdentifierEnum> map = new ConcurrentHashMap<>();
		for (MasterAgreementVariantIdentifierEnum instance : MasterAgreementVariantIdentifierEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MasterAgreementVariantIdentifierEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MasterAgreementVariantIdentifierEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MasterAgreementVariantIdentifierEnum fromDisplayName(String name) {
		MasterAgreementVariantIdentifierEnum value = values.get(name);
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
