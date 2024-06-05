package cdm.base.staticdata.asset.credit;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values used in both the obligations and deliverable obligations of the credit default swap to represent a class or type of securities which apply.
 * @version ${project.version}
 */
@RosettaEnum("ObligationCategoryEnum")
public enum ObligationCategoryEnum {

	/**
	 * ISDA term &#39;Payment&#39;.
	 */
	@RosettaEnumValue(value = "Payment") PAYMENT("Payment"),
	
	/**
	 * ISDA term &#39;Borrowed Money&#39;.
	 */
	@RosettaEnumValue(value = "BorrowedMoney") BORROWED_MONEY("BorrowedMoney"),
	
	/**
	 * ISDA term &#39;Reference Obligations Only&#39;.
	 */
	@RosettaEnumValue(value = "ReferenceObligationsOnly") REFERENCE_OBLIGATIONS_ONLY("ReferenceObligationsOnly"),
	
	/**
	 * ISDA term &#39;Bond&#39;.
	 */
	@RosettaEnumValue(value = "Bond") BOND("Bond"),
	
	/**
	 * ISDA term &#39;Loan&#39;.
	 */
	@RosettaEnumValue(value = "Loan") LOAN("Loan"),
	
	/**
	 * ISDA term &#39;Bond or Loan&#39;.
	 */
	@RosettaEnumValue(value = "BondOrLoan") BOND_OR_LOAN("BondOrLoan")
;
	private static Map<String, ObligationCategoryEnum> values;
	static {
        Map<String, ObligationCategoryEnum> map = new ConcurrentHashMap<>();
		for (ObligationCategoryEnum instance : ObligationCategoryEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ObligationCategoryEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ObligationCategoryEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ObligationCategoryEnum fromDisplayName(String name) {
		ObligationCategoryEnum value = values.get(name);
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
