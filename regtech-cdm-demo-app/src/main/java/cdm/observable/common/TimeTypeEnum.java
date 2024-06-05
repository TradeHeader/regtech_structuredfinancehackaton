package cdm.observable.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify points in the day when option exercise and valuation can occur.
 * @version ${project.version}
 */
@RosettaEnum("TimeTypeEnum")
public enum TimeTypeEnum {

	/**
	 * The official closing time of the exchange on the valuation date.
	 */
	@RosettaEnumValue(value = "Close") CLOSE("Close"),
	
	/**
	 * The official opening time of the exchange on the valuation date.
	 */
	@RosettaEnumValue(value = "Open") OPEN("Open"),
	
	/**
	 * The time at which the official settlement price is determined.
	 */
	@RosettaEnumValue(value = "OSP") OSP("OSP"),
	
	/**
	 * The time specified in the element equityExpirationTime or valuationTime (as appropriate).
	 */
	@RosettaEnumValue(value = "SpecificTime") SPECIFIC_TIME("SpecificTime"),
	
	/**
	 * The time at which the official settlement price (following the auction by the exchange) is determined by the exchange.
	 */
	@RosettaEnumValue(value = "XETRA") XETRA("XETRA"),
	
	/**
	 * The official closing time of the derivatives exchange on which a derivative contract is listed on that security underlier.
	 */
	@RosettaEnumValue(value = "DerivativesClose") DERIVATIVES_CLOSE("DerivativesClose"),
	
	/**
	 * The time is determined as provided in the relevant Master Confirmation.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInMasterConfirmation") AS_SPECIFIED_IN_MASTER_CONFIRMATION("AsSpecifiedInMasterConfirmation")
;
	private static Map<String, TimeTypeEnum> values;
	static {
        Map<String, TimeTypeEnum> map = new ConcurrentHashMap<>();
		for (TimeTypeEnum instance : TimeTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	TimeTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	TimeTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static TimeTypeEnum fromDisplayName(String name) {
		TimeTypeEnum value = values.get(name);
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
