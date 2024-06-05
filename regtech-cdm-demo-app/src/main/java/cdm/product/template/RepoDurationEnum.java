package cdm.product.template;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * A duration code for a Repo (or Securities Lending) transaction. There are many business and market rules that are derived from the duration of the transaction.
 * @version ${project.version}
 */
@RosettaEnum("RepoDurationEnum")
public enum RepoDurationEnum {

	/**
	 * Indicates that a contract is classified as overnight, meaning that there is one business day difference between the start and end date of the contract. Business rule: When the repo is overnight, the number of business days between the spot and forward value dates must be one. Forward leg must be specified.
	 */
	@RosettaEnumValue(value = "Overnight") OVERNIGHT("Overnight"),
	
	/**
	 * Indicates that a contract is a regular term contract, with a start date and an end date. Business rule: When the repo is &#39;Term&#39;, both spot and forward legs must be specified.
	 */
	@RosettaEnumValue(value = "Term") TERM("Term")
;
	private static Map<String, RepoDurationEnum> values;
	static {
        Map<String, RepoDurationEnum> map = new ConcurrentHashMap<>();
		for (RepoDurationEnum instance : RepoDurationEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	RepoDurationEnum(String rosettaName) {
		this(rosettaName, null);
	}

	RepoDurationEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static RepoDurationEnum fromDisplayName(String name) {
		RepoDurationEnum value = values.get(name);
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
