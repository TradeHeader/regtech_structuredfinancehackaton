package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify whether the dividend is paid with respect to the Dividend Period.
 * @version ${project.version}
 */
@RosettaEnum("DividendAmountTypeEnum")
public enum DividendAmountTypeEnum {

	/**
	 * The record date for a dividend occurs during a dividend period.
	 */
	@RosettaEnumValue(value = "RecordAmount") RECORD_AMOUNT("RecordAmount"),
	
	/**
	 * The ex-date for a dividend occurs during a dividend period.
	 */
	@RosettaEnumValue(value = "ExAmount") EX_AMOUNT("ExAmount"),
	
	/**
	 * The payment date for a dividend occurs during a dividend period.
	 */
	@RosettaEnumValue(value = "PaidAmount") PAID_AMOUNT("PaidAmount"),
	
	/**
	 * The Amount is determined as provided in the relevant Master Confirmation.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInMasterConfirmation") AS_SPECIFIED_IN_MASTER_CONFIRMATION("AsSpecifiedInMasterConfirmation")
;
	private static Map<String, DividendAmountTypeEnum> values;
	static {
        Map<String, DividendAmountTypeEnum> map = new ConcurrentHashMap<>();
		for (DividendAmountTypeEnum instance : DividendAmountTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DividendAmountTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DividendAmountTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DividendAmountTypeEnum fromDisplayName(String name) {
		DividendAmountTypeEnum value = values.get(name);
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
