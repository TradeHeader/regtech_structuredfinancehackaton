package cdm.base.datetime;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the period term as part of a periodic schedule, i.e. the calculation period end date within the regular part of the calculation period. The value could be a rule, e.g. IMM Settlement Dates, which is the 3rd Wednesday of the month, or it could be a specific day of the month, such as the first day of the applicable month.
 * @version ${project.version}
 */
@RosettaEnum("RollConventionEnum")
public enum RollConventionEnum {

	/**
	 * Rolls on month end dates irrespective of the length of the month and the previous roll day.
	 */
	@RosettaEnumValue(value = "EOM") EOM("EOM"),
	
	/**
	 * Roll days are determined according to the FRN Convention or Euro-dollar Convention as described in ISDA 2000 definitions.
	 */
	@RosettaEnumValue(value = "FRN") FRN("FRN"),
	
	/**
	 * IMM Settlement Dates. The third Wednesday of the (delivery) month.
	 */
	@RosettaEnumValue(value = "IMM") IMM("IMM"),
	
	/**
	 * The last trading day/expiration day of the Canadian Derivatives Exchange (Bourse de Montreal Inc) Three-month Canadian Bankers&#39; Acceptance Futures (Ticker Symbol BAX). The second London banking day prior to the third Wednesday of the contract month. If the determined day is a Bourse or bank holiday in Montreal or Toronto, the last trading day shall be the previous bank business day. Per Canadian Derivatives Exchange BAX contract specification.
	 */
	@RosettaEnumValue(value = "IMMCAD") IMMCAD("IMMCAD"),
	
	/**
	 * The last trading day of the Sydney Futures Exchange 90 Day Bank Accepted Bills Futures contract (see http://www.sfe.com.au/content/sfe/trading/con_specs.pdf). One Sydney business day preceding the second Friday of the relevant settlement.
	 */
	@RosettaEnumValue(value = "IMMAUD") IMMAUD("IMMAUD"),
	
	/**
	 * The last trading day of the Sydney Futures Exchange NZ 90 Day Bank Bill Futures contract (see http://www.sfe.com.au/content/sfe/trading/con_specs.pdf). The first Wednesday after the ninth day of the relevant settlement month.
	 */
	@RosettaEnumValue(value = "IMMNZD") IMMNZD("IMMNZD"),
	
	/**
	 * Sydney Futures Exchange 90-Day Bank Accepted Bill Futures Settlement Dates. The second Friday of the (delivery) month
	 */
	@RosettaEnumValue(value = "SFE") SFE("SFE"),
	
	/**
	 * The roll convention is not required. For example, in the case of a daily calculation frequency.
	 */
	@RosettaEnumValue(value = "NONE") NONE("NONE"),
	
	/**
	 * 13-week and 26-week U.S. Treasury Bill Auction Dates. Each Monday except for U.S. (New York) holidays when it will occur on a Tuesday.
	 */
	@RosettaEnumValue(value = "TBILL") TBILL("TBILL"),
	
	/**
	 * Rolls on the 1st day of the month.
	 */
	@RosettaEnumValue(value = "_1", displayName = "1") _1("_1", "1"),
	
	/**
	 * Rolls on the 2nd day of the month.
	 */
	@RosettaEnumValue(value = "_2", displayName = "2") _2("_2", "2"),
	
	/**
	 * Rolls on the 3rd day of the month.
	 */
	@RosettaEnumValue(value = "_3", displayName = "3") _3("_3", "3"),
	
	/**
	 * Rolls on the 4th day of the month.
	 */
	@RosettaEnumValue(value = "_4", displayName = "4") _4("_4", "4"),
	
	/**
	 * Rolls on the 5th day of the month.
	 */
	@RosettaEnumValue(value = "_5", displayName = "5") _5("_5", "5"),
	
	/**
	 * Rolls on the 6th day of the month.
	 */
	@RosettaEnumValue(value = "_6", displayName = "6") _6("_6", "6"),
	
	/**
	 * Rolls on the 7th day of the month.
	 */
	@RosettaEnumValue(value = "_7", displayName = "7") _7("_7", "7"),
	
	/**
	 * Rolls on the 8th day of the month.
	 */
	@RosettaEnumValue(value = "_8", displayName = "8") _8("_8", "8"),
	
	/**
	 * Rolls on the 9th day of the month.
	 */
	@RosettaEnumValue(value = "_9", displayName = "9") _9("_9", "9"),
	
	/**
	 * Rolls on the 10th day of the month.
	 */
	@RosettaEnumValue(value = "_10", displayName = "10") _10("_10", "10"),
	
	/**
	 * Rolls on the 11th day of the month.
	 */
	@RosettaEnumValue(value = "_11", displayName = "11") _11("_11", "11"),
	
	/**
	 * Rolls on the 12th day of the month.
	 */
	@RosettaEnumValue(value = "_12", displayName = "12") _12("_12", "12"),
	
	/**
	 * Rolls on the 13th day of the month.
	 */
	@RosettaEnumValue(value = "_13", displayName = "13") _13("_13", "13"),
	
	/**
	 * Rolls on the 14th day of the month.
	 */
	@RosettaEnumValue(value = "_14", displayName = "14") _14("_14", "14"),
	
	/**
	 * Rolls on the 15th day of the month.
	 */
	@RosettaEnumValue(value = "_15", displayName = "15") _15("_15", "15"),
	
	/**
	 * Rolls on the 16th day of the month.
	 */
	@RosettaEnumValue(value = "_16", displayName = "16") _16("_16", "16"),
	
	/**
	 * Rolls on the 17th day of the month.
	 */
	@RosettaEnumValue(value = "_17", displayName = "17") _17("_17", "17"),
	
	/**
	 * Rolls on the 18th day of the month.
	 */
	@RosettaEnumValue(value = "_18", displayName = "18") _18("_18", "18"),
	
	/**
	 * Rolls on the 19th day of the month.
	 */
	@RosettaEnumValue(value = "_19", displayName = "19") _19("_19", "19"),
	
	/**
	 * Rolls on the 20th day of the month.
	 */
	@RosettaEnumValue(value = "_20", displayName = "20") _20("_20", "20"),
	
	/**
	 * Rolls on the 21st day of the month.
	 */
	@RosettaEnumValue(value = "_21", displayName = "21") _21("_21", "21"),
	
	/**
	 * Rolls on the 22nd day of the month.
	 */
	@RosettaEnumValue(value = "_22", displayName = "22") _22("_22", "22"),
	
	/**
	 * Rolls on the 23rd day of the month.
	 */
	@RosettaEnumValue(value = "_23", displayName = "23") _23("_23", "23"),
	
	/**
	 * Rolls on the 24th day of the month.
	 */
	@RosettaEnumValue(value = "_24", displayName = "24") _24("_24", "24"),
	
	/**
	 * Rolls on the 25th day of the month.
	 */
	@RosettaEnumValue(value = "_25", displayName = "25") _25("_25", "25"),
	
	/**
	 * Rolls on the 26th day of the month.
	 */
	@RosettaEnumValue(value = "_26", displayName = "26") _26("_26", "26"),
	
	/**
	 * Rolls on the 27th day of the month.
	 */
	@RosettaEnumValue(value = "_27", displayName = "27") _27("_27", "27"),
	
	/**
	 * Rolls on the 28th day of the month.
	 */
	@RosettaEnumValue(value = "_28", displayName = "28") _28("_28", "28"),
	
	/**
	 * Rolls on the 29th day of the month.
	 */
	@RosettaEnumValue(value = "_29", displayName = "29") _29("_29", "29"),
	
	/**
	 * Rolls on the 30th day of the month.
	 */
	@RosettaEnumValue(value = "_30", displayName = "30") _30("_30", "30"),
	
	/**
	 * Rolling weekly on a Monday.
	 */
	@RosettaEnumValue(value = "MON") MON("MON"),
	
	/**
	 * Rolling weekly on a Tuesday
	 */
	@RosettaEnumValue(value = "TUE") TUE("TUE"),
	
	/**
	 * Rolling weekly on a Wednesday
	 */
	@RosettaEnumValue(value = "WED") WED("WED"),
	
	/**
	 * Rolling weekly on a Thursday
	 */
	@RosettaEnumValue(value = "THU") THU("THU"),
	
	/**
	 * Rolling weekly on a Friday
	 */
	@RosettaEnumValue(value = "FRI") FRI("FRI"),
	
	/**
	 * Rolling weekly on a Saturday
	 */
	@RosettaEnumValue(value = "SAT") SAT("SAT"),
	
	/**
	 * Rolling weekly on a Sunday
	 */
	@RosettaEnumValue(value = "SUN") SUN("SUN")
;
	private static Map<String, RollConventionEnum> values;
	static {
        Map<String, RollConventionEnum> map = new ConcurrentHashMap<>();
		for (RollConventionEnum instance : RollConventionEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RollConventionEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RollConventionEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RollConventionEnum fromDisplayName(String name) {
		RollConventionEnum value = values.get(name);
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
