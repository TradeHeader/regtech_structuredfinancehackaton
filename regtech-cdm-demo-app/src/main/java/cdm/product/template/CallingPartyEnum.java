package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Identifies a party to the on-demand repo transaction that has a right to demand for termination of the Security Finance transaction.
 * @version ${project.version}
 */
@RosettaEnum("CallingPartyEnum")
public enum CallingPartyEnum {

	/**
	 * Initial buyer to the repo transaction.
	 */
	@RosettaEnumValue(value = "InitialBuyer") INITIAL_BUYER("InitialBuyer"),
	
	/**
	 * Initial seller to the repo transaction.
	 */
	@RosettaEnumValue(value = "InitialSeller") INITIAL_SELLER("InitialSeller"),
	
	/**
	 * Either, Buyer or Seller to the repo transaction.
	 */
	@RosettaEnumValue(value = "Either") EITHER("Either"),
	
	/**
	 * As defined in Master Agreement.
	 */
	@RosettaEnumValue(value = "AsDefinedInMasterAgreement") AS_DEFINED_IN_MASTER_AGREEMENT("AsDefinedInMasterAgreement")
;
	private static Map<String, CallingPartyEnum> values;
	static {
        Map<String, CallingPartyEnum> map = new ConcurrentHashMap<>();
		for (CallingPartyEnum instance : CallingPartyEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CallingPartyEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CallingPartyEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CallingPartyEnum fromDisplayName(String name) {
		CallingPartyEnum value = values.get(name);
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
