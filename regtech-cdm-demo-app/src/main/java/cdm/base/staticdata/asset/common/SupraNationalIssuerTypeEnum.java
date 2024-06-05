package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the type of supranational entity issuing the asset.
 * @version ${project.version}
 */
@RosettaEnum("SupraNationalIssuerTypeEnum")
public enum SupraNationalIssuerTypeEnum {

	/**
	 * Specifies International Financial Institution.
	 */
	@RosettaEnumValue(value = "InternationalOrganisation") INTERNATIONAL_ORGANISATION("InternationalOrganisation"),
	
	/**
	 * Specifies Multilateral Bank or Multilateral Development Bank.
	 */
	@RosettaEnumValue(value = "MultilateralBank") MULTILATERAL_BANK("MultilateralBank")
;
	private static Map<String, SupraNationalIssuerTypeEnum> values;
	static {
        Map<String, SupraNationalIssuerTypeEnum> map = new ConcurrentHashMap<>();
		for (SupraNationalIssuerTypeEnum instance : SupraNationalIssuerTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SupraNationalIssuerTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SupraNationalIssuerTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SupraNationalIssuerTypeEnum fromDisplayName(String name) {
		SupraNationalIssuerTypeEnum value = values.get(name);
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
