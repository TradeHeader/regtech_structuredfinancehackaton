package cdm.product.collateral;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * How is collateral interest to be handled?
 * @version ${project.version}
 */
@RosettaEnum("CollateralInterestHandlingEnum")
public enum CollateralInterestHandlingEnum {

	/**
	 *  Transfer the interest each period 
	 */
	@RosettaEnumValue(value = "Transfer") TRANSFER("Transfer"),
	
	/**
	 *  Adjust the collateral balance to include the interest amount 
	 */
	@RosettaEnumValue(value = "Adjust") ADJUST("Adjust"),
	
	/**
	 *  Transfer the interest if it meets certain criteria, or otherwise adjust the collateral balance to reflect the interest amount  
	 */
	@RosettaEnumValue(value = "Transfer_or_Adjust") TRANSFER_OR_ADJUST("Transfer_or_Adjust")
;
	private static Map<String, CollateralInterestHandlingEnum> values;
	static {
        Map<String, CollateralInterestHandlingEnum> map = new ConcurrentHashMap<>();
		for (CollateralInterestHandlingEnum instance : CollateralInterestHandlingEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CollateralInterestHandlingEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CollateralInterestHandlingEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CollateralInterestHandlingEnum fromDisplayName(String name) {
		CollateralInterestHandlingEnum value = values.get(name);
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
