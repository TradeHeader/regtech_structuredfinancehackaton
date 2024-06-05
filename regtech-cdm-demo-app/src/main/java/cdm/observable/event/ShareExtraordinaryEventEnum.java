package cdm.observable.event;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the consequences of extraordinary events relating to the underlying.
 * @version ${project.version}
 */
@RosettaEnum("ShareExtraordinaryEventEnum")
public enum ShareExtraordinaryEventEnum {

	/**
	 * The trade continues such that the underlying now consists of the New Shares and/or the Other Consideration, if any, and the proceeds of any redemption, if any, that the holder of the underlying Shares would have been entitled to.
	 */
	@RosettaEnumValue(value = "AlternativeObligation") ALTERNATIVE_OBLIGATION("AlternativeObligation"),
	
	/**
	 * The trade is cancelled and a cancellation fee will be paid by one party to the other.
	 */
	@RosettaEnumValue(value = "CancellationAndPayment") CANCELLATION_AND_PAYMENT("CancellationAndPayment"),
	
	/**
	 * The trade will be adjusted by the Calculation Agent in accordance with the adjustments made by any exchange on which options on the underlying are listed.
	 */
	@RosettaEnumValue(value = "OptionsExchange") OPTIONS_EXCHANGE("OptionsExchange"),
	
	/**
	 * The Calculation Agent will determine what adjustment is required to offset any change to the economics of the trade. If the Calculation Agent cannot achieve this, the trade goes to Cancellation and Payment with the Calculation Agent deciding on the value of the cancellation fee. Adjustments may not be made to account solely for changes in volatility, expected dividends, stock loan rate or liquidity.
	 */
	@RosettaEnumValue(value = "CalculationAgent") CALCULATION_AGENT("CalculationAgent"),
	
	/**
	 * The Calculation Agent will determine what adjustment is required to offset any change to the economics of the trade. If the Calculation Agent cannot achieve this, the trade goes to Cancellation and Payment with the Calculation Agent deciding on the value of the cancellation fee. Adjustments to account for changes in volatility, expected dividends, stock loan rate or liquidity are allowed.
	 */
	@RosettaEnumValue(value = "ModifiedCalculationAgent") MODIFIED_CALCULATION_AGENT("ModifiedCalculationAgent"),
	
	/**
	 * Applies to Basket Transactions. The portion of the Basket made up by the affected Share will be cancelled and a cancellation fee will be paid from one party to the other. The remainder of the trade continues.
	 */
	@RosettaEnumValue(value = "PartialCancellationAndPayment") PARTIAL_CANCELLATION_AND_PAYMENT("PartialCancellationAndPayment"),
	
	/**
	 * If this is a Share-for-Combined merger event (Shares are replaced with New Shares and Other Consideration), then different treatment can be applied to each component if the parties have specified this.
	 */
	@RosettaEnumValue(value = "Component") COMPONENT("Component")
;
	private static Map<String, ShareExtraordinaryEventEnum> values;
	static {
        Map<String, ShareExtraordinaryEventEnum> map = new ConcurrentHashMap<>();
		for (ShareExtraordinaryEventEnum instance : ShareExtraordinaryEventEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ShareExtraordinaryEventEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ShareExtraordinaryEventEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ShareExtraordinaryEventEnum fromDisplayName(String name) {
		ShareExtraordinaryEventEnum value = values.get(name);
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
