package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * How is the Creadit Support Annex defined for this transaction as defined in the 2021 ISDA Definitions, section 18.2.1 
 * @version ${project.version}
 */
@RosettaEnum("CsaTypeEnum")
public enum CsaTypeEnum {

	/**
	 * There is no CSA applicable
	 */
	@RosettaEnumValue(value = "NoCSA", displayName = "NoCSA") NO_CSA("NoCSA", "NoCSA"),
	
	/**
	 * Thre is an existing Credit Support Annex
	 */
	@RosettaEnumValue(value = "ExistingCSA", displayName = "ExistingCSA") EXISTING_CSA("ExistingCSA", "ExistingCSA"),
	
	/**
	 * There is a bilateral Credit Support Annex specific to the transaction
	 */
	@RosettaEnumValue(value = "ReferenceVMCSA", displayName = "ReferenceVMCSA") REFERENCE_VMCSA("ReferenceVMCSA", "ReferenceVMCSA")
;
	private static Map<String, CsaTypeEnum> values;
	static {
        Map<String, CsaTypeEnum> map = new ConcurrentHashMap<>();
		for (CsaTypeEnum instance : CsaTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CsaTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	CsaTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CsaTypeEnum fromDisplayName(String name) {
		CsaTypeEnum value = values.get(name);
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
