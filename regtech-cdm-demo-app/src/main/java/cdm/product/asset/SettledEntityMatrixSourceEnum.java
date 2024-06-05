package cdm.product.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the relevant settled entity matrix source.
 * @version ${project.version}
 */
@RosettaEnum("SettledEntityMatrixSourceEnum")
public enum SettledEntityMatrixSourceEnum {

	/**
	 * The Relevant Settled Entity Matrix shall be the list agreed for this purpose by the parties. The list is not included as part of the electronic confirmation.
	 */
	@RosettaEnumValue(value = "ConfirmationAnnex") CONFIRMATION_ANNEX("ConfirmationAnnex"),
	
	/**
	 * The term is not applicable.
	 */
	@RosettaEnumValue(value = "NotApplicable") NOT_APPLICABLE("NotApplicable"),
	
	/**
	 * The Settled Entity Matrix published by the Index Publisher.
	 */
	@RosettaEnumValue(value = "Publisher") PUBLISHER("Publisher")
;
	private static Map<String, SettledEntityMatrixSourceEnum> values;
	static {
        Map<String, SettledEntityMatrixSourceEnum> map = new ConcurrentHashMap<>();
		for (SettledEntityMatrixSourceEnum instance : SettledEntityMatrixSourceEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SettledEntityMatrixSourceEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SettledEntityMatrixSourceEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SettledEntityMatrixSourceEnum fromDisplayName(String name) {
		SettledEntityMatrixSourceEnum value = values.get(name);
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
