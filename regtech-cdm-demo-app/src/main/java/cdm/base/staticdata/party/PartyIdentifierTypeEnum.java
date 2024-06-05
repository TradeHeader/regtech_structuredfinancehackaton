package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values associated with party identifier sources.
 * @version ${project.version}
 */
@RosettaEnum("PartyIdentifierTypeEnum")
public enum PartyIdentifierTypeEnum {

	/**
	 * The Bank Identifier Code.
	 */
	@RosettaEnumValue(value = "BIC") BIC("BIC"),
	
	/**
	 * The ISO 17442:2012 Legal Entity Identifier.
	 */
	@RosettaEnumValue(value = "LEI") LEI("LEI"),
	
	/**
	 * The ISO 10383 Market Identifier Code (MIC).
	 */
	@RosettaEnumValue(value = "MIC") MIC("MIC")
;
	private static Map<String, PartyIdentifierTypeEnum> values;
	static {
        Map<String, PartyIdentifierTypeEnum> map = new ConcurrentHashMap<>();
		for (PartyIdentifierTypeEnum instance : PartyIdentifierTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PartyIdentifierTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	PartyIdentifierTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PartyIdentifierTypeEnum fromDisplayName(String name) {
		PartyIdentifierTypeEnum value = values.get(name);
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
