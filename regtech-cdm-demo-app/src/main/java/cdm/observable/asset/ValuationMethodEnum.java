package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the ISDA defined methodology for determining the final price of the reference obligation for purposes of cash settlement.
 * @version ${project.version}
 */
@RosettaEnum("ValuationMethodEnum")
public enum ValuationMethodEnum {

	@RosettaEnumValue(value = "Market") MARKET("Market"),
	
	@RosettaEnumValue(value = "Highest") HIGHEST("Highest"),
	
	@RosettaEnumValue(value = "AverageMarket") AVERAGE_MARKET("AverageMarket"),
	
	@RosettaEnumValue(value = "AverageHighest") AVERAGE_HIGHEST("AverageHighest"),
	
	@RosettaEnumValue(value = "BlendedMarket") BLENDED_MARKET("BlendedMarket"),
	
	@RosettaEnumValue(value = "BlendedHighest") BLENDED_HIGHEST("BlendedHighest"),
	
	@RosettaEnumValue(value = "AverageBlendedMarket") AVERAGE_BLENDED_MARKET("AverageBlendedMarket"),
	
	@RosettaEnumValue(value = "AverageBlendedHighest") AVERAGE_BLENDED_HIGHEST("AverageBlendedHighest")
;
	private static Map<String, ValuationMethodEnum> values;
	static {
        Map<String, ValuationMethodEnum> map = new ConcurrentHashMap<>();
		for (ValuationMethodEnum instance : ValuationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	ValuationMethodEnum(String rosettaName) {
		this(rosettaName, null);
	}

	ValuationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static ValuationMethodEnum fromDisplayName(String name) {
		ValuationMethodEnum value = values.get(name);
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
