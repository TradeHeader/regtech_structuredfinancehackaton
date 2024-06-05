package cdm.product.common.schedule;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify how to deal with a non standard calculation period within a swap stream.
 * @version ${project.version}
 */
@RosettaEnum("StubPeriodTypeEnum")
public enum StubPeriodTypeEnum {

	/**
	 * If there is a non regular period remaining it is left shorter than the streams calculation period frequency and placed at the start of the stream.
	 */
	@RosettaEnumValue(value = "ShortInitial") SHORT_INITIAL("ShortInitial"),
	
	/**
	 * If there is a non regular period remaining it is left shorter than the streams calculation period frequency and placed at the end of the stream.
	 */
	@RosettaEnumValue(value = "ShortFinal") SHORT_FINAL("ShortFinal"),
	
	/**
	 * If there is a non regular period remaining it is placed at the start of the stream and combined with the adjacent calculation period to give a long first calculation period.
	 */
	@RosettaEnumValue(value = "LongInitial") LONG_INITIAL("LongInitial"),
	
	/**
	 * If there is a non regular period remaining it is placed at the end of the stream and combined with the adjacent calculation period to give a long last calculation period.
	 */
	@RosettaEnumValue(value = "LongFinal") LONG_FINAL("LongFinal")
;
	private static Map<String, StubPeriodTypeEnum> values;
	static {
        Map<String, StubPeriodTypeEnum> map = new ConcurrentHashMap<>();
		for (StubPeriodTypeEnum instance : StubPeriodTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	StubPeriodTypeEnum(String rosettaName) {
		this(rosettaName, null);
	}

	StubPeriodTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static StubPeriodTypeEnum fromDisplayName(String name) {
		StubPeriodTypeEnum value = values.get(name);
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
