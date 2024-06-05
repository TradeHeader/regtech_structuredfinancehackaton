package cdm.legaldocumentation.common;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the Event of Default or Termination event for which Specified Entities terms are being defined.
 * @version ${project.version}
 */
@RosettaEnum("SpecifiedEntityClauseEnum")
public enum SpecifiedEntityClauseEnum {

	@RosettaEnumValue(value = "DefaultUnderSpecifiedTransaction") DEFAULT_UNDER_SPECIFIED_TRANSACTION("DefaultUnderSpecifiedTransaction"),
	
	@RosettaEnumValue(value = "CrossDefault") CROSS_DEFAULT("CrossDefault"),
	
	@RosettaEnumValue(value = "Bankruptcy") BANKRUPTCY("Bankruptcy"),
	
	@RosettaEnumValue(value = "CreditEventUponMerger") CREDIT_EVENT_UPON_MERGER("CreditEventUponMerger")
;
	private static Map<String, SpecifiedEntityClauseEnum> values;
	static {
        Map<String, SpecifiedEntityClauseEnum> map = new ConcurrentHashMap<>();
		for (SpecifiedEntityClauseEnum instance : SpecifiedEntityClauseEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	SpecifiedEntityClauseEnum(String rosettaName) {
		this(rosettaName, null);
	}

	SpecifiedEntityClauseEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static SpecifiedEntityClauseEnum fromDisplayName(String name) {
		SpecifiedEntityClauseEnum value = values.get(name);
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
