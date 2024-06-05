package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the enumerated values to specify the ancillary roles to the transaction. The product is agnostic to the actual parties involved in the transaction, with the party references abstracted away from the product definition and replaced by the AncillaryRoleEnum. The AncillaryRoleEnum can then be positioned in the product and the AncillaryParty type, which is positioned outside of the product definition, allows the AncillaryRoleEnum to be associated with an actual party reference.
 * @version ${project.version}
 */
@RosettaEnum("AncillaryRoleEnum")
public enum AncillaryRoleEnum {

	/**
	 * Specifies the party which determines additional disruption events.
	 */
	@RosettaEnumValue(value = "DisruptionEventsDeterminingParty") DISRUPTION_EVENTS_DETERMINING_PARTY("DisruptionEventsDeterminingParty"),
	
	/**
	 * Specifies the party which determines if dividends are extraordinary in relation to normal levels.
	 */
	@RosettaEnumValue(value = "ExtraordinaryDividendsParty") EXTRAORDINARY_DIVIDENDS_PARTY("ExtraordinaryDividendsParty"),
	
	/**
	 * Specifies the clearing organization (CCP, DCO) which the trade should be cleared.
	 */
	@RosettaEnumValue(value = "PredeterminedClearingOrganizationParty") PREDETERMINED_CLEARING_ORGANIZATION_PARTY("PredeterminedClearingOrganizationParty"),
	
	/**
	 * Specifies the party to which notice of a manual exercise should be given.
	 */
	@RosettaEnumValue(value = "ExerciseNoticeReceiverPartyManual") EXERCISE_NOTICE_RECEIVER_PARTY_MANUAL("ExerciseNoticeReceiverPartyManual"),
	
	/**
	 * Specifies the party to which notice of a optional early termination exercise should be given.
	 */
	@RosettaEnumValue(value = "ExerciseNoticeReceiverPartyOptionalEarlyTermination") EXERCISE_NOTICE_RECEIVER_PARTY_OPTIONAL_EARLY_TERMINATION("ExerciseNoticeReceiverPartyOptionalEarlyTermination"),
	
	/**
	 * Specifies the party to which notice of a cancelable provision exercise should be given.
	 */
	@RosettaEnumValue(value = "ExerciseNoticeReceiverPartyCancelableProvision") EXERCISE_NOTICE_RECEIVER_PARTY_CANCELABLE_PROVISION("ExerciseNoticeReceiverPartyCancelableProvision"),
	
	/**
	 * Specifies the party to which notice of a extendible provision exercise should be given.
	 */
	@RosettaEnumValue(value = "ExerciseNoticeReceiverPartyExtendibleProvision") EXERCISE_NOTICE_RECEIVER_PARTY_EXTENDIBLE_PROVISION("ExerciseNoticeReceiverPartyExtendibleProvision"),
	
	/**
	 * Specifies the party responsible for performing calculation agent duties as defined in the applicable product definition.
	 */
	@RosettaEnumValue(value = "CalculationAgentIndependent") CALCULATION_AGENT_INDEPENDENT("CalculationAgentIndependent"),
	
	/**
	 * Specifies the party responsible for performing calculation agent duties associated with an optional early termination.
	 */
	@RosettaEnumValue(value = "CalculationAgentOptionalEarlyTermination") CALCULATION_AGENT_OPTIONAL_EARLY_TERMINATION("CalculationAgentOptionalEarlyTermination"),
	
	/**
	 * Specifies the party responsible for performing calculation agent duties associated with an mandatory early termination.
	 */
	@RosettaEnumValue(value = "CalculationAgentMandatoryEarlyTermination") CALCULATION_AGENT_MANDATORY_EARLY_TERMINATION("CalculationAgentMandatoryEarlyTermination"),
	
	/**
	 * Specifies the party responsible for deciding the fallback rate.
	 */
	@RosettaEnumValue(value = "CalculationAgentFallback") CALCULATION_AGENT_FALLBACK("CalculationAgentFallback")
;
	private static Map<String, AncillaryRoleEnum> values;
	static {
        Map<String, AncillaryRoleEnum> map = new ConcurrentHashMap<>();
		for (AncillaryRoleEnum instance : AncillaryRoleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AncillaryRoleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	AncillaryRoleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AncillaryRoleEnum fromDisplayName(String name) {
		AncillaryRoleEnum value = values.get(name);
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
