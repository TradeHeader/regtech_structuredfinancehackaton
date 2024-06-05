package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the specified entity terms for the Event of Default or Termination Event specified.
 * @version ${project.version}
 */
@RosettaEnum("SpecifiedEntityTermsEnum")
public enum SpecifiedEntityTermsEnum {

	/**
	 * No Specified Entity is provided
	 */
	@RosettaEnumValue(value = "None") NONE("None"),
	
	/**
	 * Any Affiliate is a Specified Entity.
	 */
	@RosettaEnumValue(value = "AnyAffiliate") ANY_AFFILIATE("AnyAffiliate"),
	
	/**
	 * The Specified Entity is provided.
	 */
	@RosettaEnumValue(value = "NamedSpecifiedEntity") NAMED_SPECIFIED_ENTITY("NamedSpecifiedEntity"),
	
	/**
	 * Any Material Subsidiary.
	 */
	@RosettaEnumValue(value = "MaterialSubsidiary") MATERIAL_SUBSIDIARY("MaterialSubsidiary"),
	
	/**
	 * Non standard Specified Entity terms are provided.
	 */
	@RosettaEnumValue(value = "OtherSpecifiedEntity") OTHER_SPECIFIED_ENTITY("OtherSpecifiedEntity")
;
	private static Map<String, SpecifiedEntityTermsEnum> values;
	static {
        Map<String, SpecifiedEntityTermsEnum> map = new ConcurrentHashMap<>();
		for (SpecifiedEntityTermsEnum instance : SpecifiedEntityTermsEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SpecifiedEntityTermsEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SpecifiedEntityTermsEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SpecifiedEntityTermsEnum fromDisplayName(String name) {
		SpecifiedEntityTermsEnum value = values.get(name);
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
