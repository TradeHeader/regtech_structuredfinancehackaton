package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Seniority of debt instruments comprising the index.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/credit-seniority"
 *
 * Provision 
 *
 */
@RosettaEnum("CreditSeniorityEnum")
public enum CreditSeniorityEnum {

	/**
	 * Other as defined under EMIR.
	 */
	@RosettaEnumValue(value = "Other") OTHER("Other"),
	
	/**
	 * Senior Loss Absorbing Capacity (RED Tier Code: SNRLAC).
	 */
	@RosettaEnumValue(value = "SeniorLossAbsorbingCapacity") SENIOR_LOSS_ABSORBING_CAPACITY("SeniorLossAbsorbingCapacity"),
	
	/**
	 * Senior domestic (RED Tier Code: SECDOM).
	 */
	@RosettaEnumValue(value = "SeniorSec") SENIOR_SEC("SeniorSec"),
	
	/**
	 * Senior foreign (RED Tier Code: SNRFOR).
	 */
	@RosettaEnumValue(value = "SeniorUnSec") SENIOR_UN_SEC("SeniorUnSec"),
	
	/**
	 * Subordinate, Lower Tier 2 (RED Tier Code: SUBLT2).
	 */
	@RosettaEnumValue(value = "SubLowerTier2") SUB_LOWER_TIER_2("SubLowerTier2"),
	
	/**
	 * Subordinate Tier 1 (RED Tier Code: PREFT1).
	 */
	@RosettaEnumValue(value = "SubTier1") SUB_TIER_1("SubTier1"),
	
	/**
	 * Subordinate, Tier 3.
	 */
	@RosettaEnumValue(value = "SubTier3") SUB_TIER_3("SubTier3"),
	
	/**
	 * Subordinate, Upper Tier 2 (RED Tier Code: JRSUBUT2).
	 */
	@RosettaEnumValue(value = "SubUpperTier2") SUB_UPPER_TIER_2("SubUpperTier2")
;
	private static Map<String, CreditSeniorityEnum> values;
	static {
        Map<String, CreditSeniorityEnum> map = new ConcurrentHashMap<>();
		for (CreditSeniorityEnum instance : CreditSeniorityEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditSeniorityEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CreditSeniorityEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditSeniorityEnum fromDisplayName(String name) {
		CreditSeniorityEnum value = values.get(name);
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
