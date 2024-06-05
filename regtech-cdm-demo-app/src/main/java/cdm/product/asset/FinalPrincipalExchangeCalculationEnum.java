package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * To be specified only for products that embed a redemption payment.
 * @version ${project.version}
 */
@RosettaEnum("FinalPrincipalExchangeCalculationEnum")
public enum FinalPrincipalExchangeCalculationEnum {

	/**
	 * If Floored is set then Principal Exchange takes the form: Notional Amount * Max(1, Index Final/ Index Base).
	 */
	@RosettaEnumValue(value = "Floored") FLOORED("Floored"),
	
	/**
	 * If NonFloored is set then the Principal Exchange takes the form: Notional Amount * Index Final / Index Base.
	 */
	@RosettaEnumValue(value = "NonFloored") NON_FLOORED("NonFloored")
;
	private static Map<String, FinalPrincipalExchangeCalculationEnum> values;
	static {
        Map<String, FinalPrincipalExchangeCalculationEnum> map = new ConcurrentHashMap<>();
		for (FinalPrincipalExchangeCalculationEnum instance : FinalPrincipalExchangeCalculationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	FinalPrincipalExchangeCalculationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	FinalPrincipalExchangeCalculationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static FinalPrincipalExchangeCalculationEnum fromDisplayName(String name) {
		FinalPrincipalExchangeCalculationEnum value = values.get(name);
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
