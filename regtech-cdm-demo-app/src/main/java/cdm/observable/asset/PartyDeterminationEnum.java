package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify how a calculation agent will be determined.
 * @version ${project.version}
 */
@RosettaEnum("PartyDeterminationEnum")
public enum PartyDeterminationEnum {

	/**
	 * The party that gives notice of exercise. Per 2000 ISDA Definitions, Section 11.1. Parties, paragraph (d).
	 */
	@RosettaEnumValue(value = "ExercisingParty") EXERCISING_PARTY("ExercisingParty"),
	
	/**
	 * The party that is given notice of exercise. Per 2000 ISDA Definitions, Section 11.1. Parties, paragraph (e).
	 */
	@RosettaEnumValue(value = "NonExercisingParty") NON_EXERCISING_PARTY("NonExercisingParty"),
	
	/**
	 * The Calculation Agent is determined by reference to the relevant master agreement.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInMasterAgreement") AS_SPECIFIED_IN_MASTER_AGREEMENT("AsSpecifiedInMasterAgreement"),
	
	/**
	 * The Calculation Agent is determined by reference to the relevant standard terms supplement.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInStandardTermsSupplement") AS_SPECIFIED_IN_STANDARD_TERMS_SUPPLEMENT("AsSpecifiedInStandardTermsSupplement"),
	
	/**
	 * Both parties with joined rights to be a calculation agent.
	 */
	@RosettaEnumValue(value = "Both") BOTH("Both")
;
	private static Map<String, PartyDeterminationEnum> values;
	static {
        Map<String, PartyDeterminationEnum> map = new ConcurrentHashMap<>();
		for (PartyDeterminationEnum instance : PartyDeterminationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PartyDeterminationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PartyDeterminationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PartyDeterminationEnum fromDisplayName(String name) {
		PartyDeterminationEnum value = values.get(name);
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
