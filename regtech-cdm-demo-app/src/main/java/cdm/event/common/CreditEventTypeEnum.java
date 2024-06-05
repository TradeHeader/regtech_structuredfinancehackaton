package cdm.event.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify a credit event type.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/credit-event-type"
 *
 * Provision 
 *
 */
@RosettaEnum("CreditEventTypeEnum")
public enum CreditEventTypeEnum {

	/**
	 * The reference entity has been dissolved or has become insolvent. It also covers events that may be a precursor to insolvency such as instigation of bankruptcy or insolvency proceedings. Sovereign trades are not subject to Bankruptcy as &#39;technically&#39; a Sovereign cannot become bankrupt. ISDA 2003 Term: Bankruptcy.
	 */
	@RosettaEnumValue(value = "Bankruptcy") BANKRUPTCY("Bankruptcy"),
	
	/**
	 * Results from the fact that the rating of the reference obligation is downgraded to a distressed rating level. From a usage standpoint, this credit event is typically not applicable in case of RMBS trades.
	 */
	@RosettaEnumValue(value = "DistressedRatingsDowngrade") DISTRESSED_RATINGS_DOWNGRADE("DistressedRatingsDowngrade"),
	
	/**
	 * This credit event triggers, after the expiration of any applicable grace period, if the reference entity fails to make due payments in an aggregrate amount of not less than the payment requirement on one or more obligations (e.g. a missed coupon payment). ISDA 2003 Term: Failure to Pay.
	 */
	@RosettaEnumValue(value = "FailureToPay") FAILURE_TO_PAY("FailureToPay"),
	
	/**
	 * Corresponds to the failure by the Reference Entity to pay an expected interest amount or the payment of an actual interest amount that is less than the expected interest amount. ISDA 2003 Term: Failure to Pay Interest.
	 */
	@RosettaEnumValue(value = "FailureToPayInterest") FAILURE_TO_PAY_INTEREST("FailureToPayInterest"),
	
	/**
	 * Corresponds to the failure by the Reference Entity to pay an expected principal amount or the payment of an actual principal amount that is less than the expected principal amount. ISDA 2003 Term: Failure to Pay Principal.
	 */
	@RosettaEnumValue(value = "FailureToPayPrincipal") FAILURE_TO_PAY_PRINCIPAL("FailureToPayPrincipal"),
	
	/**
	 * A governmental intervention is an event resulting from an action by a governmental authority that materially impacts the reference entity&#39;s obligations, such as an interest rate reduction, principal reduction, deferral of interest or principal, change in priority ranking, or change in currency or composition of payment. ISDA 2014 Term: Governmental Intervention.
	 */
	@RosettaEnumValue(value = "GovernmentalIntervention") GOVERNMENTAL_INTERVENTION("GovernmentalIntervention"),
	
	/**
	 * Results from the fact that losses occur to the underlying instruments that do not result in reductions of the outstanding principal of the reference obligation.
	 */
	@RosettaEnumValue(value = "ImpliedWritedown") IMPLIED_WRITEDOWN("ImpliedWritedown"),
	
	/**
	 * Results from the fact that the underlier fails to make principal payments as expected.
	 */
	@RosettaEnumValue(value = "MaturityExtension") MATURITY_EXTENSION("MaturityExtension"),
	
	/**
	 * One or more of the obligations have been declared due and payable before they would otherwise have been due and payable as a result of, or on the basis of, the occurrence of a default, event of default or other similar condition or event other than failure to pay (preferred by the market over Obligation Default, because more definitive and encompasses the definition of Obligation Default - this is more favorable to the Seller). Subject to the default requirement amount. ISDA 2003 Term: Obligation Acceleration.
	 */
	@RosettaEnumValue(value = "ObligationAcceleration") OBLIGATION_ACCELERATION("ObligationAcceleration"),
	
	/**
	 * One or more of the obligations have become capable of being declared due and payable before they would otherwise have been due and payable as a result of, or on the basis of, the occurrence of a default, event of default or other similar condition or event other than failure to pay. ISDA 2003 Term: Obligation Default.
	 */
	@RosettaEnumValue(value = "ObligationDefault") OBLIGATION_DEFAULT("ObligationDefault"),
	
	/**
	 * The reference entity, or a governmental authority, either refuses to recognise or challenges the validity of one or more obligations of the reference entity, or imposes a moratorium thereby postponing payments on one or more of the obligations of the reference entity. Subject to the default requirement amount. ISDA 2003 Term: Repudiation/Moratorium.
	 */
	@RosettaEnumValue(value = "RepudiationMoratorium") REPUDIATION_MORATORIUM("RepudiationMoratorium"),
	
	/**
	 * A restructuring is an event that materially impacts the reference entity&#39;s obligations, such as an interest rate reduction, principal reduction, deferral of interest or principal, change in priority ranking, or change in currency or composition of payment. ISDA 2003 Term: Restructuring.
	 */
	@RosettaEnumValue(value = "Restructuring") RESTRUCTURING("Restructuring"),
	
	/**
	 * Results from the fact that the underlier writes down its outstanding principal amount.
	 */
	@RosettaEnumValue(value = "Writedown") WRITEDOWN("Writedown")
;
	private static Map<String, CreditEventTypeEnum> values;
	static {
        Map<String, CreditEventTypeEnum> map = new ConcurrentHashMap<>();
		for (CreditEventTypeEnum instance : CreditEventTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditEventTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditEventTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditEventTypeEnum fromDisplayName(String name) {
		CreditEventTypeEnum value = values.get(name);
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
