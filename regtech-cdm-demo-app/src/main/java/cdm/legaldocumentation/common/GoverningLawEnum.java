package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import com.rosetta.model.lib.annotations.RosettaSynonym;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the law governing the contract or legal document.
 * @version ${project.version}
 */
@RosettaEnum("GoverningLawEnum")
public enum GoverningLawEnum {

	/**
	 * The Governing Law is determined by reference to the relevant master agreement.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInMasterAgreement") AS_SPECIFIED_IN_MASTER_AGREEMENT("AsSpecifiedInMasterAgreement"),
	
	/**
	 * Belgian law
	 */
	@RosettaEnumValue(value = "BE") BE("BE"),
	
	/**
	 * Alberta law
	 */
	@RosettaEnumValue(value = "CAAB") CAAB("CAAB"),
	
	/**
	 * British Columbia Law
	 */
	@RosettaEnumValue(value = "CABC") CABC("CABC"),
	
	/**
	 * Manitoba law
	 */
	@RosettaEnumValue(value = "CAMN") CAMN("CAMN"),
	
	/**
	 * Ontario law
	 */
	@RosettaEnumValue(value = "CAON") CAON("CAON"),
	
	/**
	 * Quebec law
	 */
	@RosettaEnumValue(value = "CAQC") CAQC("CAQC"),
	
	/**
	 * German law
	 */
	@RosettaEnumValue(value = "DE") DE("DE"),
	
	/**
	 * French law
	 */
	@RosettaEnumValue(value = "FR") FR("FR"),
	
	/**
	 * English law
	 */
	@RosettaSynonym(value = "ENGLISH", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "GBEN") GBEN("GBEN"),
	
	/**
	 * The law of the island of Guernsey
	 */
	@RosettaEnumValue(value = "GBGY") GBGY("GBGY"),
	
	/**
	 * The law of the Isle of Man
	 */
	@RosettaEnumValue(value = "GBIM") GBIM("GBIM"),
	
	/**
	 * The law of the island of Jersey
	 */
	@RosettaEnumValue(value = "GBJY") GBJY("GBJY"),
	
	/**
	 * Scottish law
	 */
	@RosettaEnumValue(value = "GBSC") GBSC("GBSC"),
	
	/**
	 * Irish law
	 */
	@RosettaEnumValue(value = "IE") IE("IE"),
	
	/**
	 * Japanese law
	 */
	@RosettaSynonym(value = "JAPAN", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "JP") JP("JP"),
	
	/**
	 * Luxembourg law
	 */
	@RosettaEnumValue(value = "LU") LU("LU"),
	
	/**
	 * As agreed in the ISDA Master Agreement
	 */
	@RosettaEnumValue(value = "RelatedMasterAgreement") RELATED_MASTER_AGREEMENT("RelatedMasterAgreement"),
	
	/**
	 * Californian law
	 */
	@RosettaEnumValue(value = "USCA") USCA("USCA"),
	
	/**
	 * Delaware law
	 */
	@RosettaEnumValue(value = "USDE") USDE("USDE"),
	
	/**
	 * Illinois law
	 */
	@RosettaEnumValue(value = "USIL") USIL("USIL"),
	
	/**
	 * New York law
	 */
	@RosettaSynonym(value = "NY", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "USNY") USNY("USNY")
;
	private static Map<String, GoverningLawEnum> values;
	static {
        Map<String, GoverningLawEnum> map = new ConcurrentHashMap<>();
		for (GoverningLawEnum instance : GoverningLawEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	GoverningLawEnum(String rosettaName) {
		this(rosettaName, null);
	}

	GoverningLawEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static GoverningLawEnum fromDisplayName(String name) {
		GoverningLawEnum value = values.get(name);
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
