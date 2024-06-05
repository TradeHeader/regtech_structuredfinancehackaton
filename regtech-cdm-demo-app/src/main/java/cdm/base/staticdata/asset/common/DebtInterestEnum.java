package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list that specifies the general rule for periodic interest rate payment.
 * @version ${project.version}
 */
@RosettaEnum("DebtInterestEnum")
public enum DebtInterestEnum {

	/**
	 * Denotes payment calculated with reference to a fixed interest rate.
	 */
	@RosettaEnumValue(value = "Fixed") FIXED("Fixed"),
	
	/**
	 * Denotes payment calculated with reference to a floating interest rate.
	 */
	@RosettaEnumValue(value = "Floating") FLOATING("Floating"),
	
	/**
	 * Denotes payment calculated with reference to one or more specified inflation rates.
	 */
	@RosettaEnumValue(value = "InflationLinked") INFLATION_LINKED("InflationLinked"),
	
	/**
	 * Denotes payment calculated with reference to one or more price or other indices (other than inflation rates).
	 */
	@RosettaEnumValue(value = "IndexLinked") INDEX_LINKED("IndexLinked"),
	
	/**
	 * Denotes a stripped bond representing only the interest component.
	 */
	@RosettaEnumValue(value = "InterestOnly") INTEREST_ONLY("InterestOnly"),
	
	/**
	 * Denotes payment calculated with reference to other underlyings (not being floating interest rates, inflation rates or indices) or with a non-linear relationship to floating interest rates, inflation rates or indices.
	 */
	@RosettaEnumValue(value = "OtherStructured") OTHER_STRUCTURED("OtherStructured"),
	
	/**
	 * Denotes payment calculated with reference to the inverse of a floating interest rate.
	 */
	@RosettaEnumValue(value = "InverseFloating") INVERSE_FLOATING("InverseFloating"),
	
	/**
	 * Denotes a zero coupon bond that does not pay intetrest.
	 */
	@RosettaEnumValue(value = "ZeroCoupon") ZERO_COUPON("ZeroCoupon")
;
	private static Map<String, DebtInterestEnum> values;
	static {
        Map<String, DebtInterestEnum> map = new ConcurrentHashMap<>();
		for (DebtInterestEnum instance : DebtInterestEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DebtInterestEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DebtInterestEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DebtInterestEnum fromDisplayName(String name) {
		DebtInterestEnum value = values.get(name);
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
