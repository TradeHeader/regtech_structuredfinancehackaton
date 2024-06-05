package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the type of telephone number, e.g. work vs. mobile.
 * @version ${project.version}
 */
@RosettaEnum("TelephoneTypeEnum")
public enum TelephoneTypeEnum {

	/**
	 * A number used primarily for work-related calls. Includes home office numbers used primarily for work purposes.
	 */
	@RosettaEnumValue(value = "Work") WORK("Work"),
	
	/**
	 * A number on a mobile telephone that is often or usually used for work-related calls. This type of number can be used for urgent work related business when a work number is not sufficient to contact the person or firm.
	 */
	@RosettaEnumValue(value = "Mobile") MOBILE("Mobile"),
	
	/**
	 * A number used primarily for work-related facsimile transmissions.
	 */
	@RosettaEnumValue(value = "Fax") FAX("Fax"),
	
	/**
	 * A number used primarily for non work-related calls. (Normally this type of number would be used only as an emergency backup number, not as a regular course of business).
	 */
	@RosettaEnumValue(value = "Personal") PERSONAL("Personal")
;
	private static Map<String, TelephoneTypeEnum> values;
	static {
        Map<String, TelephoneTypeEnum> map = new ConcurrentHashMap<>();
		for (TelephoneTypeEnum instance : TelephoneTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TelephoneTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TelephoneTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TelephoneTypeEnum fromDisplayName(String name) {
		TelephoneTypeEnum value = values.get(name);
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
