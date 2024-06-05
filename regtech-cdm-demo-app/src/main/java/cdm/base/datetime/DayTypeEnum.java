package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Lists the enumerated values to specify the day type classification used in counting the number of days between two dates.
 * @version ${project.version}
 */
@RosettaEnum("DayTypeEnum")
public enum DayTypeEnum {

	/**
	 * Applies when calculating the number of days between two dates the count includes only business days.
	 */
	@RosettaEnumValue(value = "Business") BUSINESS("Business"),
	
	/**
	 * Applies when calculating the number of days between two dates the count includes all calendar days.
	 */
	@RosettaEnumValue(value = "Calendar") CALENDAR("Calendar"),
	
	/**
	 * Applies when calculating the number of days between two dates the count includes only currency business days.
	 */
	@RosettaEnumValue(value = "CurrencyBusiness") CURRENCY_BUSINESS("CurrencyBusiness"),
	
	/**
	 * Applies when calculating the number of days between two dates the count includes only stock exchange business days.
	 */
	@RosettaEnumValue(value = "ExchangeBusiness") EXCHANGE_BUSINESS("ExchangeBusiness"),
	
	/**
	 * Applies when calculating the number of days between two dates the count includes only scheduled trading days.
	 */
	@RosettaEnumValue(value = "ScheduledTradingDay") SCHEDULED_TRADING_DAY("ScheduledTradingDay")
;
	private static Map<String, DayTypeEnum> values;
	static {
        Map<String, DayTypeEnum> map = new ConcurrentHashMap<>();
		for (DayTypeEnum instance : DayTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DayTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DayTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DayTypeEnum fromDisplayName(String name) {
		DayTypeEnum value = values.get(name);
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
