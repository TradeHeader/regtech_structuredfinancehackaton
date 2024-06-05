package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines whether the bank holidays are treated as weekdays or weekends in terms of delivery profile in the context of commodity products, in particular those with peak or off-peak delivery profiles.
 * @version ${project.version}
 */
@RosettaEnum("BankHolidayTreatmentEnum")
public enum BankHolidayTreatmentEnum {

	/**
	 * Bank holidays treated as weekdays.
	 */
	@RosettaEnumValue(value = "AsWeekday") AS_WEEKDAY("AsWeekday"),
	
	/**
	 * Bank holidays treated as weekends.
	 */
	@RosettaEnumValue(value = "AsWeekend") AS_WEEKEND("AsWeekend")
;
	private static Map<String, BankHolidayTreatmentEnum> values;
	static {
        Map<String, BankHolidayTreatmentEnum> map = new ConcurrentHashMap<>();
		for (BankHolidayTreatmentEnum instance : BankHolidayTreatmentEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	BankHolidayTreatmentEnum(String rosettaName) {
		this(rosettaName, null);
	}

	BankHolidayTreatmentEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static BankHolidayTreatmentEnum fromDisplayName(String name) {
		BankHolidayTreatmentEnum value = values.get(name);
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
