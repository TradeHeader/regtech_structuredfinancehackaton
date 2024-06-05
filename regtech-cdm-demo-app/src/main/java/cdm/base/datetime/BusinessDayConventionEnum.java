package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the convention for adjusting any relevant date if it would otherwise fall on a day that is not a valid business day.
 * @version ${project.version}
 */
@RosettaEnum("BusinessDayConventionEnum")
public enum BusinessDayConventionEnum {

	/**
	 * The non-business date will be adjusted to the first following day that is a business day
	 */
	@RosettaEnumValue(value = "FOLLOWING") FOLLOWING("FOLLOWING"),
	
	/**
	 * Per 2000 ISDA Definitions, Section 4.11. FRN Convention; Eurodollar Convention. FRN is included here as a type of business day convention although it does not strictly fall within ISDA&#39;s definition of a Business Day Convention and does not conform to the simple definition given above.
	 */
	@RosettaEnumValue(value = "FRN") FRN("FRN"),
	
	/**
	 * The non-business date will be adjusted to the first following day that is a business day unless that day falls in the next calendar month, in which case that date will be the first preceding day that is a business day.
	 */
	@RosettaEnumValue(value = "MODFOLLOWING") MODFOLLOWING("MODFOLLOWING"),
	
	/**
	 * The non-business day will be adjusted to the first preceding day that is a business day.
	 */
	@RosettaEnumValue(value = "PRECEDING") PRECEDING("PRECEDING"),
	
	/**
	 * The non-business date will be adjusted to the first preceding day that is a business day unless that day falls in the previous calendar month, in which case that date will be the first following day that us a business day.
	 */
	@RosettaEnumValue(value = "MODPRECEDING") MODPRECEDING("MODPRECEDING"),
	
	/**
	 * The non-business date will be adjusted to the nearest day that is a business day - i.e. if the non-business day falls on any day other than a Sunday or a Monday, it will be the first preceding day that is a business day, and will be the first following business day if it falls on a Sunday or a Monday.
	 */
	@RosettaEnumValue(value = "NEAREST") NEAREST("NEAREST"),
	
	/**
	 * The date will not be adjusted if it falls on a day that is not a business day.
	 */
	@RosettaEnumValue(value = "NONE") NONE("NONE"),
	
	/**
	 * The date adjustments conventions are defined elsewhere, so it is not required to specify them here.
	 */
	@RosettaEnumValue(value = "NotApplicable") NOT_APPLICABLE("NotApplicable")
;
	private static Map<String, BusinessDayConventionEnum> values;
	static {
        Map<String, BusinessDayConventionEnum> map = new ConcurrentHashMap<>();
		for (BusinessDayConventionEnum instance : BusinessDayConventionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	BusinessDayConventionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	BusinessDayConventionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static BusinessDayConventionEnum fromDisplayName(String name) {
		BusinessDayConventionEnum value = values.get(name);
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
