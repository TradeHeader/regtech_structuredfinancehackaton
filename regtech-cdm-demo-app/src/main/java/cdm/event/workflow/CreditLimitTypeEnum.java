package cdm.event.workflow;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumeration values to qualify the type of credit limits.
 * @version ${project.version}
 */
@RosettaEnum("CreditLimitTypeEnum")
public enum CreditLimitTypeEnum {

	/**
	 * The type of credit line expressed in CS01. The sensitivity with respect to changes in the CDS spread.
	 */
	@RosettaEnumValue(value = "CS01") CS01("CS01"),
	
	/**
	 * The type of credit line expressed in DV01. The dollar value of a one basis point decrease in interest rates. It shows the change in a bond&#39;s price compared to a decrease in the bond&#39;s yield.
	 */
	@RosettaEnumValue(value = "DV01") DV01("DV01"),
	
	/**
	 * The type of credit line expressed in Initial Margin value.
	 */
	@RosettaEnumValue(value = "IM") IM("IM"),
	
	/**
	 * The type of credit line expressed in Notional amount.
	 */
	@RosettaEnumValue(value = "Notional") NOTIONAL("Notional"),
	
	/**
	 * The type of credit line expressed as a Net Present Value.
	 */
	@RosettaEnumValue(value = "NPV") NPV("NPV"),
	
	/**
	 * The type of credit line expressed in PV01. The value of a one dollar or one basis point annuity.
	 */
	@RosettaEnumValue(value = "PV01") PV01("PV01")
;
	private static Map<String, CreditLimitTypeEnum> values;
	static {
        Map<String, CreditLimitTypeEnum> map = new ConcurrentHashMap<>();
		for (CreditLimitTypeEnum instance : CreditLimitTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditLimitTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditLimitTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditLimitTypeEnum fromDisplayName(String name) {
		CreditLimitTypeEnum value = values.get(name);
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
