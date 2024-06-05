package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify a mortgage typology.
 * @version ${project.version}
 */
@RosettaEnum("MortgageSectorEnum")
public enum MortgageSectorEnum {

	/**
	 * Asset Backed Security.
	 */
	@RosettaEnumValue(value = "ABS") ABS("ABS"),
	
	/**
	 * Collateralized Debt Obligation.
	 */
	@RosettaEnumValue(value = "CDO") CDO("CDO"),
	
	/**
	 * Commercial Mortgage Backed Security.
	 */
	@RosettaEnumValue(value = "CMBS") CMBS("CMBS"),
	
	/**
	 * Residential Mortgage Backed Security.
	 */
	@RosettaEnumValue(value = "RMBS") RMBS("RMBS")
;
	private static Map<String, MortgageSectorEnum> values;
	static {
        Map<String, MortgageSectorEnum> map = new ConcurrentHashMap<>();
		for (MortgageSectorEnum instance : MortgageSectorEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MortgageSectorEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MortgageSectorEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MortgageSectorEnum fromDisplayName(String name) {
		MortgageSectorEnum value = values.get(name);
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
