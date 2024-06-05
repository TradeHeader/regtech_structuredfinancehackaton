package cdm.base.math;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides enumerated values for capacity units, generally used in the context of defining quantities for commodities.
 * @version ${project.version}
 */
@RosettaEnum("CapacityUnitEnum")
public enum CapacityUnitEnum {

	/**
	 * Denotes Allowances as standard unit.
	 */
	@RosettaEnumValue(value = "ALW") ALW("ALW"),
	
	/**
	 * Denotes a Barrel as a standard unit.
	 */
	@RosettaEnumValue(value = "BBL") BBL("BBL"),
	
	/**
	 * Denotes Billion Cubic Feet as a standard unit.
	 */
	@RosettaEnumValue(value = "BCF") BCF("BCF"),
	
	/**
	 * Denotes Board Feet as a standard unit.
	 */
	@RosettaEnumValue(value = "BDFT") BDFT("BDFT"),
	
	/**
	 * Denotes Cubic Meters as a standard unit.
	 */
	@RosettaEnumValue(value = "CBM") CBM("CBM"),
	
	/**
	 * Denotes Certified Emissions Reduction as a standard unit.
	 */
	@RosettaEnumValue(value = "CER") CER("CER"),
	
	/**
	 * Denotes Climate Reserve Tonnes as a standard unit.
	 */
	@RosettaEnumValue(value = "CRT") CRT("CRT"),
	
	/**
	 * Denotes 10 grams as a standard unit used in precious metals contracts (e.g MCX).
	 */
	@RosettaEnumValue(value = "DAG") DAG("DAG"),
	
	/**
	 * Denotes a single day as a standard unit used in time charter trades.
	 */
	@RosettaEnumValue(value = "DAY") DAY("DAY"),
	
	/**
	 * Denotes Dry Metric Ton (Tonne) Units - Consists of a metric ton of mass excluding moisture.
	 */
	@RosettaEnumValue(value = "DMTU") DMTU("DMTU"),
	
	/**
	 * Denotes Environmental Credit as a standard unit.
	 */
	@RosettaEnumValue(value = "ENVCRD") ENVCRD("ENVCRD"),
	
	/**
	 * Denotes Environmental Offset as a standard unit.
	 */
	@RosettaEnumValue(value = "ENVOFST") ENVOFST("ENVOFST"),
	
	/**
	 * Denotes a 40 ft. Equivalent Unit container as a standard unit.
	 */
	@RosettaEnumValue(value = "FEU") FEU("FEU"),
	
	/**
	 * Denotes a Gram as a standard unit.
	 */
	@RosettaEnumValue(value = "G") G("G"),
	
	/**
	 * Denotes a GB Bushel as a standard unit.
	 */
	@RosettaEnumValue(value = "GBBSH") GBBSH("GBBSH"),
	
	/**
	 * Denotes a GB British Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "GBBTU") GBBTU("GBBTU"),
	
	/**
	 * Denotes a GB Hundredweight unit as standard unit.
	 */
	@RosettaEnumValue(value = "GBCWT") GBCWT("GBCWT"),
	
	/**
	 * Denotes a GB Gallon unit as standard unit.
	 */
	@RosettaEnumValue(value = "GBGAL") GBGAL("GBGAL"),
	
	/**
	 * Denotes a Thousand GB British Thermal Units as a standard unit.
	 */
	@RosettaEnumValue(value = "GBMBTU") GBMBTU("GBMBTU"),
	
	/**
	 * Denotes a Million GB British Thermal Units as a standard unit.
	 */
	@RosettaEnumValue(value = "GBMMBTU") GBMMBTU("GBMMBTU"),
	
	/**
	 * Denotes a GB Ton as a standard unit.
	 */
	@RosettaEnumValue(value = "GBT") GBT("GBT"),
	
	/**
	 * Denotes a GB Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "GBTHM") GBTHM("GBTHM"),
	
	/**
	 * Denotes a Gigajoule as a standard unit.
	 */
	@RosettaEnumValue(value = "GJ") GJ("GJ"),
	
	/**
	 * Denotes a Gigawatt as a standard unit.
	 */
	@RosettaEnumValue(value = "GW") GW("GW"),
	
	/**
	 * Denotes a Gigawatt-hour as a standard unit.
	 */
	@RosettaEnumValue(value = "GWH") GWH("GWH"),
	
	/**
	 * Denotes a Hectolitre as a standard unit.
	 */
	@RosettaEnumValue(value = "HL") HL("HL"),
	
	/**
	 * Denotes a 100-troy ounces Gold Bar as a standard unit.
	 */
	@RosettaEnumValue(value = "HOGB") HOGB("HOGB"),
	
	/**
	 * Denotes an ISO British Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "ISOBTU") ISOBTU("ISOBTU"),
	
	/**
	 * Denotes a Thousand ISO British Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "ISOMBTU") ISOMBTU("ISOMBTU"),
	
	/**
	 * Denotes a Million ISO British Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "ISOMMBTU") ISOMMBTU("ISOMMBTU"),
	
	/**
	 * Denotes an ISO Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "ISOTHM") ISOTHM("ISOTHM"),
	
	/**
	 * Denotes a Kilogram as a standard unit.
	 */
	@RosettaEnumValue(value = "KG") KG("KG"),
	
	/**
	 * Denotes a Kilolitre as a standard unit.
	 */
	@RosettaEnumValue(value = "KL") KL("KL"),
	
	/**
	 * Denotes a Kilowatt as a standard unit.
	 */
	@RosettaEnumValue(value = "KW") KW("KW"),
	
	/**
	 * Denotes a Kilowatt-day as a standard unit.
	 */
	@RosettaEnumValue(value = "KWD") KWD("KWD"),
	
	/**
	 * Denotes a Kilowatt-hour as a standard unit.
	 */
	@RosettaEnumValue(value = "KWH") KWH("KWH"),
	
	/**
	 * Denotes a Kilowatt-month as a standard unit.
	 */
	@RosettaEnumValue(value = "KWM") KWM("KWM"),
	
	/**
	 * Denotes a Kilowatt-minute as a standard unit.
	 */
	@RosettaEnumValue(value = "KWMIN") KWMIN("KWMIN"),
	
	/**
	 * Denotes a Kilowatt-year as a standard unit.
	 */
	@RosettaEnumValue(value = "KWY") KWY("KWY"),
	
	/**
	 * Denotes a Litre as a standard unit.
	 */
	@RosettaEnumValue(value = "L") L("L"),
	
	/**
	 * Denotes a Pound as a standard unit.
	 */
	@RosettaEnumValue(value = "LB") LB("LB"),
	
	/**
	 * Denotes a Thousand Barrels as a standard unit.
	 */
	@RosettaEnumValue(value = "MB") MB("MB"),
	
	/**
	 * Denotes a Thousand board feet, which are used in contracts on forestry underlyers as a standard unit.
	 */
	@RosettaEnumValue(value = "MBF") MBF("MBF"),
	
	/**
	 * Denotes a Megajoule as a standard unit.
	 */
	@RosettaEnumValue(value = "MJ") MJ("MJ"),
	
	/**
	 * Denotes a Million board feet, which are used in contracts on forestry underlyers as a standard unit.
	 */
	@RosettaEnumValue(value = "MMBF") MMBF("MMBF"),
	
	/**
	 * Denotes a Million Barrels as a standard unit.
	 */
	@RosettaEnumValue(value = "MMBBL") MMBBL("MMBBL"),
	
	/**
	 * Denotes a Thousand square feet as a standard unit.
	 */
	@RosettaEnumValue(value = "MSF") MSF("MSF"),
	
	/**
	 * Denotes a Metric Ton as a standard unit.
	 */
	@RosettaEnumValue(value = "MT") MT("MT"),
	
	/**
	 * Denotes a Megawatt as a standard unit.
	 */
	@RosettaEnumValue(value = "MW") MW("MW"),
	
	/**
	 * Denotes a Megawatt-day as a standard unit.
	 */
	@RosettaEnumValue(value = "MWD") MWD("MWD"),
	
	/**
	 * Denotes a Megawatt-hour as a standard unit.
	 */
	@RosettaEnumValue(value = "MWH") MWH("MWH"),
	
	/**
	 * Denotes a Megawatt-month as a standard unit.
	 */
	@RosettaEnumValue(value = "MWM") MWM("MWM"),
	
	/**
	 * Denotes a Megawatt-minute as a standard unit.
	 */
	@RosettaEnumValue(value = "MWMIN") MWMIN("MWMIN"),
	
	/**
	 * Denotes a Megawatt-year as a standard unit.
	 */
	@RosettaEnumValue(value = "MWY") MWY("MWY"),
	
	/**
	 * Denotes a Troy Ounce as a standard unit.
	 */
	@RosettaEnumValue(value = "OZT") OZT("OZT"),
	
	/**
	 * Denotes a Standard Gold Bar as a standard unit.
	 */
	@RosettaEnumValue(value = "SGB") SGB("SGB"),
	
	/**
	 * Denotes a 20 ft. Equivalent Unit container as a standard unit.
	 */
	@RosettaEnumValue(value = "TEU") TEU("TEU"),
	
	/**
	 * Denotes a US Bushel as a standard unit.
	 */
	@RosettaEnumValue(value = "USBSH") USBSH("USBSH"),
	
	/**
	 * Denotes a US British Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "USBTU") USBTU("USBTU"),
	
	/**
	 * Denotes US Hundredweight unit as a standard unit.
	 */
	@RosettaEnumValue(value = "USCWT") USCWT("USCWT"),
	
	/**
	 * Denotes a US Gallon unit as a standard unit.
	 */
	@RosettaEnumValue(value = "USGAL") USGAL("USGAL"),
	
	/**
	 * Denotes a Thousand US British Thermal Units as a standard unit.
	 */
	@RosettaEnumValue(value = "USMBTU") USMBTU("USMBTU"),
	
	/**
	 * Denotes a Million US British Thermal Units as a standard unit.
	 */
	@RosettaEnumValue(value = "USMMBTU") USMMBTU("USMMBTU"),
	
	/**
	 * Denotes a US Ton as a standard unit.
	 */
	@RosettaEnumValue(value = "UST") UST("UST"),
	
	/**
	 * Denotes a US Thermal Unit as a standard unit.
	 */
	@RosettaEnumValue(value = "USTHM") USTHM("USTHM")
;
	private static Map<String, CapacityUnitEnum> values;
	static {
        Map<String, CapacityUnitEnum> map = new ConcurrentHashMap<>();
		for (CapacityUnitEnum instance : CapacityUnitEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CapacityUnitEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CapacityUnitEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CapacityUnitEnum fromDisplayName(String name) {
		CapacityUnitEnum value = values.get(name);
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
