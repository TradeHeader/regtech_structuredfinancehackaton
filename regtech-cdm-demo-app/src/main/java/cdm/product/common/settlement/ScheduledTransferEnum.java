package cdm.product.common.settlement;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The qualification of the type of cash flows associated with OTC derivatives contracts and their lifecycle events.
 * @version ${project.version}
 */
@RosettaEnum("ScheduledTransferEnum")
public enum ScheduledTransferEnum {

	/**
	 * A cash flow corresponding to a corporate action event.
	 */
	@RosettaEnumValue(value = "CorporateAction") CORPORATE_ACTION("CorporateAction"),
	
	/**
	 * A cash flow corresponding to the periodic accrued interests.
	 */
	@RosettaEnumValue(value = "Coupon") COUPON("Coupon"),
	
	/**
	 * A cashflow resulting from a credit event.
	 */
	@RosettaEnumValue(value = "CreditEvent") CREDIT_EVENT("CreditEvent"),
	
	/**
	 * A cash flow corresponding to the synthetic dividend of an equity underlier asset traded through a derivative instrument.
	 */
	@RosettaEnumValue(value = "DividendReturn") DIVIDEND_RETURN("DividendReturn"),
	
	/**
	 * A cash flow associated with an exercise lifecycle event.
	 */
	@RosettaEnumValue(value = "Exercise") EXERCISE("Exercise"),
	
	/**
	 * A cash flow corresponding to the return of the fixed interest rate portion of a derivative instrument that has different types of underlying assets, such as a total return swap.
	 */
	@RosettaEnumValue(value = "FixedRateReturn") FIXED_RATE_RETURN("FixedRateReturn"),
	
	/**
	 * A cash flow corresponding to the return of the floating interest rate portion of a derivative instrument that has different types of underlying assets, such as a total return swap.
	 */
	@RosettaEnumValue(value = "FloatingRateReturn") FLOATING_RATE_RETURN("FloatingRateReturn"),
	
	/**
	 * A cash flow corresponding to the compensation for missing assets due to the rounding of digits in the original number of assets to be delivered as per payout calculation.
	 */
	@RosettaEnumValue(value = "FractionalAmount") FRACTIONAL_AMOUNT("FractionalAmount"),
	
	/**
	 * A cash flow corresponding to the return of the interest rate portion of a derivative instrument that has different types of underlying assets, such as a total return swap.
	 */
	@RosettaEnumValue(value = "InterestReturn") INTEREST_RETURN("InterestReturn"),
	
	/**
	 * Net interest across payout components. Applicable to products such as interest rate swaps.
	 */
	@RosettaEnumValue(value = "NetInterest") NET_INTEREST("NetInterest"),
	
	/**
	 * A cash flow corresponding to a performance return.  The settlementOrigin attribute on the Transfer should point to the relevant Payout defining the performance calculation.
	 */
	@RosettaEnumValue(value = "Performance") PERFORMANCE("Performance"),
	
	/**
	 * A cashflow which amount typically corresponds to the notional amount of the contract for various business reasons e.g. PhysicalSettlement, PrincipalExchange etc. else to a portion of the notional amount interim payments e.g. for the purpose of resetting the Notional Amount of a Cross Currency Swap variying leg, as part of a final Principal Exchange related to a Non-Deliverable currency leg, etc.
	 */
	@RosettaEnumValue(value = "PrincipalPayment") PRINCIPAL_PAYMENT("PrincipalPayment")
;
	private static Map<String, ScheduledTransferEnum> values;
	static {
        Map<String, ScheduledTransferEnum> map = new ConcurrentHashMap<>();
		for (ScheduledTransferEnum instance : ScheduledTransferEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ScheduledTransferEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ScheduledTransferEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ScheduledTransferEnum fromDisplayName(String name) {
		ScheduledTransferEnum value = values.get(name);
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
