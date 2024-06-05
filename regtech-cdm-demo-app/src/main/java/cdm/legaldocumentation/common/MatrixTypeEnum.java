package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the identification the form of applicable matrix.
 * @version ${project.version}
 */
@RosettaEnum("MatrixTypeEnum")
public enum MatrixTypeEnum {

	/**
	 * The ISDA-published Credit Derivatives Physical Settlement Matrix.
	 */
	@RosettaEnumValue(value = "CreditDerivativesPhysicalSettlementMatrix") CREDIT_DERIVATIVES_PHYSICAL_SETTLEMENT_MATRIX("CreditDerivativesPhysicalSettlementMatrix"),
	
	/**
	 * The ISDA-published Equity Derivatives Matrix.
	 */
	@RosettaEnumValue(value = "EquityDerivativesMatrix") EQUITY_DERIVATIVES_MATRIX("EquityDerivativesMatrix"),
	
	/**
	 * The ISDA-published 2000 ISDA Definitions Settlement Matrix for Early Terminations and Swaptions.
	 */
	@RosettaEnumValue(value = "SettlementMatrix") SETTLEMENT_MATRIX("SettlementMatrix")
;
	private static Map<String, MatrixTypeEnum> values;
	static {
        Map<String, MatrixTypeEnum> map = new ConcurrentHashMap<>();
		for (MatrixTypeEnum instance : MatrixTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	MatrixTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	MatrixTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static MatrixTypeEnum fromDisplayName(String name) {
		MatrixTypeEnum value = values.get(name);
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
