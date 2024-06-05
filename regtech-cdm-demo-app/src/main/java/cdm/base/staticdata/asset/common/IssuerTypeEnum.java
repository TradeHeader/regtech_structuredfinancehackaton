package cdm.base.staticdata.asset.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents an enumeration list to identify the type of entity issuing the asset.
 * @version ${project.version}
 */
@RosettaEnum("IssuerTypeEnum")
public enum IssuerTypeEnum {

	/**
	 * Specifies debt issued by international organisations and multilateral banks, entities constituted by treaties or with multiple sovereign members includes Multilateral development Banks.
	 */
	@RosettaEnumValue(value = "SupraNational") SUPRA_NATIONAL("SupraNational"),
	
	/**
	 * Specifies Sovereign, Government Debt Securities including Central Banks.
	 */
	@RosettaEnumValue(value = "SovereignCentralBank") SOVEREIGN_CENTRAL_BANK("SovereignCentralBank"),
	
	/**
	 * Specifies debt issues by institutions or bodies, typically constituted by statute, with a function mandated by the government and subject to government supervision inclusive of profit- and non-profit making bodies. Includes the US Agencies and GSEs and the EU concept of public sector entities. Excluding any entities which are also Regional Government.
	 */
	@RosettaEnumValue(value = "QuasiGovernment") QUASI_GOVERNMENT("QuasiGovernment"),
	
	/**
	 * Specifies Regional Government Issued Debt including states within countries, local authorities and municipalities.
	 */
	@RosettaEnumValue(value = "RegionalGovernment") REGIONAL_GOVERNMENT("RegionalGovernment"),
	
	/**
	 * Specifies debt issued Securities by corporate bodies including Banks.
	 */
	@RosettaEnumValue(value = "Corporate") CORPORATE("Corporate"),
	
	/**
	 * Specifies a vehicle (with or without separate legal personality) designed for the purposes of collective investment towards a defined investment goal.
	 */
	@RosettaEnumValue(value = "Fund") FUND("Fund"),
	
	/**
	 * Specifies a vehicle setup for the purpose of acquisition and financing of specific assets on a limited recourse basis. E.g. asset backed securities, including securitisations.
	 */
	@RosettaEnumValue(value = "SpecialPurposeVehicle") SPECIAL_PURPOSE_VEHICLE("SpecialPurposeVehicle")
;
	private static Map<String, IssuerTypeEnum> values;
	static {
        Map<String, IssuerTypeEnum> map = new ConcurrentHashMap<>();
		for (IssuerTypeEnum instance : IssuerTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	IssuerTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	IssuerTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static IssuerTypeEnum fromDisplayName(String name) {
		IssuerTypeEnum value = values.get(name);
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
