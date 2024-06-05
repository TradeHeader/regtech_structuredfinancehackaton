package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("MasterAgreementClauseIdentifierEnum")
public enum MasterAgreementClauseIdentifierEnum {

	/**
	 * Date of Agreement
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_001") ISLA_GMSLA_001("ISLA_GMSLA_001"),
	
	/**
	 * Parties
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_002") ISLA_GMSLA_002("ISLA_GMSLA_002"),
	
	/**
	 * Specific Roles
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_003") ISLA_GMSLA_003("ISLA_GMSLA_003"),
	
	/**
	 * Eligible Collateral
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_004") ISLA_GMSLA_004("ISLA_GMSLA_004"),
	
	/**
	 * Margin
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_005") ISLA_GMSLA_005("ISLA_GMSLA_005"),
	
	/**
	 * Aggregation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_006") ISLA_GMSLA_006("ISLA_GMSLA_006"),
	
	/**
	 * Collateral Disapplication
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_007") ISLA_GMSLA_007("ISLA_GMSLA_007"),
	
	/**
	 * Settlement Netting
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_008") ISLA_GMSLA_008("ISLA_GMSLA_008"),
	
	/**
	 * Notification Time
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_009") ISLA_GMSLA_009("ISLA_GMSLA_009"),
	
	/**
	 * Indemnity
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_010") ISLA_GMSLA_010("ISLA_GMSLA_010"),
	
	/**
	 * Base Currency
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_011") ISLA_GMSLA_011("ISLA_GMSLA_011"),
	
	/**
	 * Places of Business
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_012") ISLA_GMSLA_012("ISLA_GMSLA_012"),
	
	/**
	 * Value
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_013") ISLA_GMSLA_013("ISLA_GMSLA_013"),
	
	/**
	 * Automatic Early Termination
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_014") ISLA_GMSLA_014("ISLA_GMSLA_014"),
	
	/**
	 * Designated Offices
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_015") ISLA_GMSLA_015("ISLA_GMSLA_015"),
	
	/**
	 * Address for Notices
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_016") ISLA_GMSLA_016("ISLA_GMSLA_016"),
	
	/**
	 * Process Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_017") ISLA_GMSLA_017("ISLA_GMSLA_017"),
	
	/**
	 * Party Acting as Agent
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_018") ISLA_GMSLA_018("ISLA_GMSLA_018"),
	
	/**
	 * Pooled Principal Transactions 
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_019") ISLA_GMSLA_019("ISLA_GMSLA_019"),
	
	/**
	 * Party Preparing the Agreement 
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_020") ISLA_GMSLA_020("ISLA_GMSLA_020"),
	
	/**
	 * Default Interest Rate
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_021") ISLA_GMSLA_021("ISLA_GMSLA_021"),
	
	/**
	 * Existing Transactions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_022") ISLA_GMSLA_022("ISLA_GMSLA_022"),
	
	/**
	 * Automation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_023") ISLA_GMSLA_023("ISLA_GMSLA_023"),
	
	/**
	 * Act of Insolvency
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_024") ISLA_GMSLA_024("ISLA_GMSLA_024"),
	
	/**
	 * Buy-In
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_025") ISLA_GMSLA_025("ISLA_GMSLA_025"),
	
	/**
	 * Currency Conversions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_026") ISLA_GMSLA_026("ISLA_GMSLA_026"),
	
	/**
	 * Scope
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_027") ISLA_GMSLA_027("ISLA_GMSLA_027"),
	
	/**
	 * Collateral Delivery Timings
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_028") ISLA_GMSLA_028("ISLA_GMSLA_028"),
	
	/**
	 * Delivery
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_029") ISLA_GMSLA_029("ISLA_GMSLA_029"),
	
	/**
	 * Substitution of Collateral
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_030") ISLA_GMSLA_030("ISLA_GMSLA_030"),
	
	/**
	 * Manufactured Payments
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_031") ISLA_GMSLA_031("ISLA_GMSLA_031"),
	
	/**
	 * Corporate Actions
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_032") ISLA_GMSLA_032("ISLA_GMSLA_032"),
	
	/**
	 * Payment of Rates
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_033") ISLA_GMSLA_033("ISLA_GMSLA_033"),
	
	/**
	 * Rate Applicable to Loaned Securities
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_034") ISLA_GMSLA_034("ISLA_GMSLA_034"),
	
	/**
	 * Lender&#39;s Right to Terminate a Loan
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_035") ISLA_GMSLA_035("ISLA_GMSLA_035"),
	
	/**
	 * Borrower&#39;s Right to Terminate a Loan
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_036") ISLA_GMSLA_036("ISLA_GMSLA_036"),
	
	/**
	 * Failure to Deliver Event of Default
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_037") ISLA_GMSLA_037("ISLA_GMSLA_037"),
	
	/**
	 * Failure to Redeliver
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_038") ISLA_GMSLA_038("ISLA_GMSLA_038"),
	
	/**
	 * Assets Transferred to a Trustee
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_039") ISLA_GMSLA_039("ISLA_GMSLA_039"),
	
	/**
	 * Suspension Event of Default
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_040") ISLA_GMSLA_040("ISLA_GMSLA_040"),
	
	/**
	 * Costs and Expenses
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_041") ISLA_GMSLA_041("ISLA_GMSLA_041"),
	
	/**
	 * Set-Off
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_042") ISLA_GMSLA_042("ISLA_GMSLA_042"),
	
	/**
	 * Default Market Value Fallbacks
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_043") ISLA_GMSLA_043("ISLA_GMSLA_043"),
	
	/**
	 * Assignment
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_044") ISLA_GMSLA_044("ISLA_GMSLA_044"),
	
	/**
	 * Telephone Recordings
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_045") ISLA_GMSLA_045("ISLA_GMSLA_045"),
	
	/**
	 * Waiver of Immunity
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_046") ISLA_GMSLA_046("ISLA_GMSLA_046"),
	
	/**
	 * Agreement to Deliver Documents
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_047") ISLA_GMSLA_047("ISLA_GMSLA_047"),
	
	/**
	 * Collateral Transfer Details
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_048") ISLA_GMSLA_048("ISLA_GMSLA_048"),
	
	/**
	 * Confidentiality
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_049") ISLA_GMSLA_049("ISLA_GMSLA_049"),
	
	/**
	 * Correction
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_050") ISLA_GMSLA_050("ISLA_GMSLA_050"),
	
	/**
	 * Minimum Collateral Transfer Amount
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_051") ISLA_GMSLA_051("ISLA_GMSLA_051"),
	
	/**
	 * Non-Reliance Representation
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_052") ISLA_GMSLA_052("ISLA_GMSLA_052"),
	
	/**
	 * Records and Statements
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_053") ISLA_GMSLA_053("ISLA_GMSLA_053"),
	
	/**
	 * Recovery and Resolution
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_054") ISLA_GMSLA_054("ISLA_GMSLA_054"),
	
	/**
	 * Security Agreement Details
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_055") ISLA_GMSLA_055("ISLA_GMSLA_055"),
	
	/**
	 * Triparty Services
	 */
	@RosettaEnumValue(value = "ISLA_GMSLA_056") ISLA_GMSLA_056("ISLA_GMSLA_056")
;
	private static Map<String, MasterAgreementClauseIdentifierEnum> values;
	static {
        Map<String, MasterAgreementClauseIdentifierEnum> map = new ConcurrentHashMap<>();
		for (MasterAgreementClauseIdentifierEnum instance : MasterAgreementClauseIdentifierEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MasterAgreementClauseIdentifierEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MasterAgreementClauseIdentifierEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MasterAgreementClauseIdentifierEnum fromDisplayName(String name) {
		MasterAgreementClauseIdentifierEnum value = values.get(name);
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
