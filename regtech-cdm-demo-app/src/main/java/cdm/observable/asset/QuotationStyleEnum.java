package cdm.observable.asset;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the actual quotation style (e.g. PointsUpFront, TradedSpread) used to quote a credit default swap fee leg.
 * @version ${project.version}
 */
@RosettaEnum("QuotationStyleEnum")
public enum QuotationStyleEnum {

	/**
	 * When quotation style is &#39;PointsUpFront&#39;, the initialPoints element of the Credit Default Swap feeLeg should be populated
	 */
	@RosettaEnumValue(value = "PointsUpFront") POINTS_UP_FRONT("PointsUpFront"),
	
	/**
	 * When quotation style is &#39;TradedSpread&#39;, the marketFixedRate element of the Credit Default Swap feeLeg should be populated
	 */
	@RosettaEnumValue(value = "TradedSpread") TRADED_SPREAD("TradedSpread"),
	
	/**
	 * When quotation style is &#39;Price&#39;, the marketPrice element of the Credit Default Swap feeLeg should be populated
	 */
	@RosettaEnumValue(value = "Price") PRICE("Price")
;
	private static Map<String, QuotationStyleEnum> values;
	static {
        Map<String, QuotationStyleEnum> map = new ConcurrentHashMap<>();
		for (QuotationStyleEnum instance : QuotationStyleEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	QuotationStyleEnum(String rosettaName) {
		this(rosettaName, null);
	}

	QuotationStyleEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static QuotationStyleEnum fromDisplayName(String name) {
		QuotationStyleEnum value = values.get(name);
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
