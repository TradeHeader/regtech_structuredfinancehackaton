package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The time of day at which the equity option expires, for example the official closing time of the exchange.
 * @version ${project.version}
 */
@RosettaEnum("ExpirationTimeTypeEnum")
public enum ExpirationTimeTypeEnum {

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
	 * The time specified in the element equityExpirationTime or valuationTime (as appropriate)
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
	private static Map<String, ExpirationTimeTypeEnum> values;
	static {
        Map<String, ExpirationTimeTypeEnum> map = new ConcurrentHashMap<>();
		for (ExpirationTimeTypeEnum instance : ExpirationTimeTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ExpirationTimeTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ExpirationTimeTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ExpirationTimeTypeEnum fromDisplayName(String name) {
		ExpirationTimeTypeEnum value = values.get(name);
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
