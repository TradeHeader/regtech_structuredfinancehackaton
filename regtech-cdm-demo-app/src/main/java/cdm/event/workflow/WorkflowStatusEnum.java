package cdm.event.workflow;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @version ${project.version}
 */
@RosettaEnum("WorkflowStatusEnum")
public enum WorkflowStatusEnum {

	@RosettaEnumValue(value = "Accepted") ACCEPTED("Accepted"),
	
	@RosettaEnumValue(value = "Alleged") ALLEGED("Alleged"),
	
	@RosettaEnumValue(value = "Amended") AMENDED("Amended"),
	
	@RosettaEnumValue(value = "Cancelled") CANCELLED("Cancelled"),
	
	@RosettaEnumValue(value = "Certain") CERTAIN("Certain"),
	
	@RosettaEnumValue(value = "Cleared") CLEARED("Cleared"),
	
	@RosettaEnumValue(value = "Pending") PENDING("Pending"),
	
	@RosettaEnumValue(value = "Rejected") REJECTED("Rejected"),
	
	@RosettaEnumValue(value = "Submitted") SUBMITTED("Submitted"),
	
	@RosettaEnumValue(value = "Terminated") TERMINATED("Terminated"),
	
	@RosettaEnumValue(value = "Uncertain") UNCERTAIN("Uncertain"),
	
	@RosettaEnumValue(value = "Unconfirmed") UNCONFIRMED("Unconfirmed"),
	
	@RosettaEnumValue(value = "Affirmed") AFFIRMED("Affirmed"),
	
	@RosettaEnumValue(value = "Confirmed") CONFIRMED("Confirmed")
;
	private static Map<String, WorkflowStatusEnum> values;
	static {
        Map<String, WorkflowStatusEnum> map = new ConcurrentHashMap<>();
		for (WorkflowStatusEnum instance : WorkflowStatusEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	WorkflowStatusEnum(String rosettaName) {
		this(rosettaName, null);
	}

	WorkflowStatusEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static WorkflowStatusEnum fromDisplayName(String name) {
		WorkflowStatusEnum value = values.get(name);
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
