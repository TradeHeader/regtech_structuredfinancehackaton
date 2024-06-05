package cdm.base.staticdata.party;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the reference entity types corresponding to a list of types defined in the ISDA First to Default documentation.
 * @version ${project.version}
 */
@RosettaEnum("EntityTypeEnum")
public enum EntityTypeEnum {

	/**
	 * Entity Type of Asian.
	 */
	@RosettaEnumValue(value = "Asian") ASIAN("Asian"),
	
	/**
	 * Entity Type of Australian and New Zealand.
	 */
	@RosettaEnumValue(value = "AustralianAndNewZealand") AUSTRALIAN_AND_NEW_ZEALAND("AustralianAndNewZealand"),
	
	/**
	 * Entity Type of European Emerging Markets.
	 */
	@RosettaEnumValue(value = "EuropeanEmergingMarkets") EUROPEAN_EMERGING_MARKETS("EuropeanEmergingMarkets"),
	
	/**
	 * Entity Type of Japanese.
	 */
	@RosettaEnumValue(value = "Japanese") JAPANESE("Japanese"),
	
	/**
	 * Entity Type of North American High Yield.
	 */
	@RosettaEnumValue(value = "NorthAmericanHighYield") NORTH_AMERICAN_HIGH_YIELD("NorthAmericanHighYield"),
	
	/**
	 * Entity Type of North American Insurance.
	 */
	@RosettaEnumValue(value = "NorthAmericanInsurance") NORTH_AMERICAN_INSURANCE("NorthAmericanInsurance"),
	
	/**
	 * Entity Type of North American Investment Grade.
	 */
	@RosettaEnumValue(value = "NorthAmericanInvestmentGrade") NORTH_AMERICAN_INVESTMENT_GRADE("NorthAmericanInvestmentGrade"),
	
	/**
	 * Entity Type of Singaporean.
	 */
	@RosettaEnumValue(value = "Singaporean") SINGAPOREAN("Singaporean"),
	
	/**
	 * Entity Type of Western European.
	 */
	@RosettaEnumValue(value = "WesternEuropean") WESTERN_EUROPEAN("WesternEuropean"),
	
	/**
	 * Entity Type of Western European Insurance.
	 */
	@RosettaEnumValue(value = "WesternEuropeanInsurance") WESTERN_EUROPEAN_INSURANCE("WesternEuropeanInsurance")
;
	private static Map<String, EntityTypeEnum> values;
	static {
        Map<String, EntityTypeEnum> map = new ConcurrentHashMap<>();
		for (EntityTypeEnum instance : EntityTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	EntityTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	EntityTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static EntityTypeEnum fromDisplayName(String name) {
		EntityTypeEnum value = values.get(name);
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
