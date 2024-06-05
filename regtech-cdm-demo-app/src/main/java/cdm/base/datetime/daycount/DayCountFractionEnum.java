package cdm.base.datetime.daycount;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the day count fraction.
 * @version ${project.version}
 */
@RosettaEnum("DayCountFractionEnum")
public enum DayCountFractionEnum {

	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (v), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (e) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (d).
	 */
	@RosettaEnumValue(value = "ACT_360", displayName = "ACT/360") ACT_360("ACT_360", "ACT/360"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (ix), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (i).
	 */
	@RosettaEnumValue(value = "ACT_365L", displayName = "ACT/365L") ACT_365L("ACT_365L", "ACT/365L"),
	
	/**
	 * Per CFTC definitions.
	 */
	@RosettaEnumValue(value = "ACT_364", displayName = "ACT/364") ACT_364("ACT_364", "ACT/364"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (iv), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (d) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (c).
	 */
	@RosettaEnumValue(value = "ACT_365_FIXED", displayName = "ACT/365.FIXED") ACT_365_FIXED("ACT_365_FIXED", "ACT/365.FIXED"),
	
	/**
	 * The Fixed/Floating Amount will be calculated in accordance with the &#39;BASE EXACT/EXACT&#39; day count fraction, as defined in the &#39;Definitions Communes plusieurs Additifs Techniques&#39; published by the Association Francaise des Banques in September 1994.
	 */
	@RosettaEnumValue(value = "ACT_ACT_AFB", displayName = "ACT/ACT.AFB") ACT_ACT_AFB("ACT_ACT_AFB", "ACT/ACT.AFB"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (iii), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (c). This day count fraction code is applicable for transactions booked under the 2021 ISDA Definitions and the 2006 ISDA Definitions. Transactions under the 2000 ISDA Definitions should use the ACT/ACT.ISMA code instead.
	 */
	@RosettaEnumValue(value = "ACT_ACT_ICMA", displayName = "ACT/ACT.ICMA") ACT_ACT_ICMA("ACT_ACT_ICMA", "ACT/ACT.ICMA"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (ii), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (b) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (b). Note that going from FpML 2.0 Recommendation to the FpML 3.0 Trial Recommendation the code in FpML 2.0 &#39;ACT/365.ISDA&#39; became &#39;ACT/ACT.ISDA&#39;.
	 */
	@RosettaEnumValue(value = "ACT_ACT_ISDA", displayName = "ACT/ACT.ISDA") ACT_ACT_ISDA("ACT_ACT_ISDA", "ACT/ACT.ISDA"),
	
	/**
	 * The Fixed/Floating Amount will be calculated in accordance with Rule 251 of the statutes, by-laws, rules and recommendations of the International Securities Market Association, as published in April 1999, as applied to straight and convertible bonds issued after December 31, 1998, as though the Fixed/Floating Amount were the interest coupon on such a bond. This day count fraction code is applicable for transactions booked under the 2000 ISDA Definitions. Transactions under the 2021 ISDA Definitions and the 2006 ISDA Definitions should use the ACT/ACT.ICMA code instead.
	 */
	@RosettaEnumValue(value = "ACT_ACT_ISMA", displayName = "ACT/ACT.ISMA") ACT_ACT_ISMA("ACT_ACT_ISMA", "ACT/ACT.ISMA"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (x). Supercedes BUS/252, the number of Business Days in the Calculation Period or Compounding Period in respect of which payment is being made divided by 252.
	 */
	@RosettaEnumValue(value = "CAL_252", displayName = "CAL/252") CAL_252("CAL_252", "CAL/252"),
	
	/**
	 * Per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (a) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (a).
	 */
	@RosettaEnumValue(value = "_1_1", displayName = "1/1") _1_1("_1_1", "1/1"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (vii), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (g) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (f). Note that the algorithm defined for this day count fraction has changed between the 2000 ISDA Definitions and 2006 ISDA Definitions. See Introduction to the 2006 ISDA Definitions for further information relating to this change.
	 */
	@RosettaEnumValue(value = "_30E_360", displayName = "30E/360") _30E_360("_30E_360", "30E/360"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (viii), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (h). Note the algorithm for this day count fraction under the 2006 ISDA Definitions is designed to yield the same results in practice as the version of the 30E/360 day count fraction defined in the 2000 ISDA Definitions. See Introduction to the 2006 ISDA Definitions for further information relating to this change.
	 */
	@RosettaEnumValue(value = "_30E_360_ISDA", displayName = "30E/360.ISDA") _30E_360_ISDA("_30E_360_ISDA", "30E/360.ISDA"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (vi), per 2006 ISDA Definitions, Section 4.16. Day Count Fraction, paragraph (f) or Annex to the 2000 ISDA Definitions (June 2000 Version), Section 4.16. Day Count Fraction, paragraph (e).
	 */
	@RosettaEnumValue(value = "_30_360", displayName = "30/360") _30_360("_30_360", "30/360"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (xi)(a), if Calculation Periods for the Transaction are three months in length (excluding any shorter or longer first and last Calculation Period), 0.25, except that if the first Calculation Period or the final Calculation Period is less than three months, Actual/Actual (ISDA) shall apply to that Calculation Period.&#39; &#39;per 2006 ISDA Definitions Supplement number 43, Day Count Fraction, if &#39;RBA Bond Basis (quarter)&#39; is specified, 0.25. However, Actual/Actual (ISDA) applies to each of the first Calculation Period and the final Calculation Period if such Calculation Period is less than three months&#39;
	 */
	@RosettaEnumValue(value = "RBA_BOND_BASIS_QUARTER", displayName = "RBA Bond Basis (quarter)") RBA_BOND_BASIS_QUARTER("RBA_BOND_BASIS_QUARTER", "RBA Bond Basis (quarter)"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (xi)(b), if Calculation Periods for the Transaction are six months in length (excluding any shorter or longer first and last Calculation Period), 0.5, except that if the first Calculation Period or the final Calculation Period is less than six months, Actual/Actual (ISDA) shall apply to that Calculation Period&#39; &#39;per 2006 ISDA Definitions Supplement number 43, Day Count Fraction, if &#39;RBA Bond Basis (semi-annual)&#39; is specified, 0.5. However, Actual/Actual (ISDA) applies to each of the first Calculation Period and the final Calculation Period if such Calculation Period is less than six months&#39;
	 */
	@RosettaEnumValue(value = "RBA_BOND_BASIS_SEMI_ANNUAL", displayName = "RBA Bond Basis (semi-annual)") RBA_BOND_BASIS_SEMI_ANNUAL("RBA_BOND_BASIS_SEMI_ANNUAL", "RBA Bond Basis (semi-annual)"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (xi)(c), if Calculation Periods for the Transaction are twelve months in length (excluding any shorter or longer first and last Calculation Period), 1, except that if the first Calculation Period or the final Calculation Period is less than twelve months, Actual/Actual (ISDA) shall apply to that Calculation Period&#39; &#39;per 2006 ISDA Definitions Supplement number 43, Day Count Fraction, (k)	if &#39;RBA Bond Basis (semi-annual)&#39; is specified, 0.5. However, Actual/Actual (ISDA) applies to each of the first Calculation Period and the final Calculation Period if such Calculation Period is less than six months&#39;
	 */
	@RosettaEnumValue(value = "RBA_BOND_BASIS_ANNUAL", displayName = "RBA Bond Basis (annual)") RBA_BOND_BASIS_ANNUAL("RBA_BOND_BASIS_ANNUAL", "RBA Bond Basis (annual)"),
	
	/**
	 * Per 2021 ISDA Definitions, Section 4.6.1 Day Count Fraction, paragraph (xi), the calculation mechanics are driven deterministically by the Calculation Period Frequency (i.e. 0.25 if it is three months, 0.5 if it is 6 months, 1 if it is a year), except that if the first Calculation Period or the final Calculation Period is less than the Calculation Period Frequency, Actual/Actual (ISDA) shall apply to that Calculation Period
	 */
	@RosettaEnumValue(value = "RBA_BOND_BASIS", displayName = "RBA Bond Basis") RBA_BOND_BASIS("RBA_BOND_BASIS", "RBA Bond Basis")
;
	private static Map<String, DayCountFractionEnum> values;
	static {
        Map<String, DayCountFractionEnum> map = new ConcurrentHashMap<>();
		for (DayCountFractionEnum instance : DayCountFractionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DayCountFractionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	DayCountFractionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DayCountFractionEnum fromDisplayName(String name) {
		DayCountFractionEnum value = values.get(name);
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
