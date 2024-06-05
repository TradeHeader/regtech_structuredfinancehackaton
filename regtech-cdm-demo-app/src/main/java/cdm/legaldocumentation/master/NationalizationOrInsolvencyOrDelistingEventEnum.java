package cdm.legaldocumentation.master;

import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Defines the consequences of nationalization, insolvency and delisting events relating to the underlying.
 * @version ${project.version}
 */
@RosettaEnum("NationalizationOrInsolvencyOrDelistingEventEnum")
public enum NationalizationOrInsolvencyOrDelistingEventEnum {

	/**
	 * The parties may, but are not obliged, to terminate the transaction on mutually acceptable terms and if the terms are not agreed then the transaction continues.
	 */
	@RosettaEnumValue(value = "NegotiatedCloseout") NEGOTIATED_CLOSEOUT("NegotiatedCloseout"),
	
	/**
	 * The trade is terminated.
	 */
	@RosettaEnumValue(value = "CancellationAndPayment") CANCELLATION_AND_PAYMENT("CancellationAndPayment")
;
	private static Map<String, NationalizationOrInsolvencyOrDelistingEventEnum> values;
	static {
        Map<String, NationalizationOrInsolvencyOrDelistingEventEnum> map = new ConcurrentHashMap<>();
		for (NationalizationOrInsolvencyOrDelistingEventEnum instance : NationalizationOrInsolvencyOrDelistingEventEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	NationalizationOrInsolvencyOrDelistingEventEnum(String rosettaName) {
		this(rosettaName, null);
	}

	NationalizationOrInsolvencyOrDelistingEventEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static NationalizationOrInsolvencyOrDelistingEventEnum fromDisplayName(String name) {
		NationalizationOrInsolvencyOrDelistingEventEnum value = values.get(name);
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
