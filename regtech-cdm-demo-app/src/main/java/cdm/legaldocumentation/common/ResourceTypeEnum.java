package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of a resource (e.g. document).
 * @version ${project.version}
 */
@RosettaEnum("ResourceTypeEnum")
public enum ResourceTypeEnum {

	/**
	 * Document describing the legal terms of a transaction.
	 */
	@RosettaEnumValue(value = "Confirmation") CONFIRMATION("Confirmation"),
	
	/**
	 * Document providing supplemental material economic terms to the FpML data representation. The initial intended usage is to fulfill the CFTC Part 45 rule requirement to report ‘Any other terms(s) of the swap matched or affirmed by the counterparties in verifying the swap’ when the reporting is done via the generic FpML representation.
	 */
	@RosettaEnumValue(value = "SupplementalMaterialEconomicTerms") SUPPLEMENTAL_MATERIAL_ECONOMIC_TERMS("SupplementalMaterialEconomicTerms"),
	
	/**
	 * Document describing the economic characteristics of a transaction.
	 */
	@RosettaEnumValue(value = "TermSheet") TERM_SHEET("TermSheet")
;
	private static Map<String, ResourceTypeEnum> values;
	static {
        Map<String, ResourceTypeEnum> map = new ConcurrentHashMap<>();
		for (ResourceTypeEnum instance : ResourceTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ResourceTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ResourceTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ResourceTypeEnum fromDisplayName(String name) {
		ResourceTypeEnum value = values.get(name);
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
